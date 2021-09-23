package org.ow2.authzforce.sdk.pap;


import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicySet;
import org.ow2.authzforce.sdk.AdminXacmlSdk;
import org.ow2.authzforce.sdk.impl.AdminXacmlSdkImpl;
import org.ow2.authzforce.sdk.utils.ServerSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3._2005.atom.Link;

import java.net.URI;
import java.util.Collections;
import java.util.List;

public class SimplePolicyCRUDTest1 {
    private static final Logger log = LoggerFactory.getLogger(SimplePolicyCRUDTest1.class);

    //domain、externalId=properties.xml的externalId="testDomain1"
    private static final String DOMAIN = "myTestDomain10";

    public static void main(String[] args) throws Exception {
        URI baseURL = ServerSetup.getRootURL(ServerSetup.getServer());
        AdminXacmlSdk client = new AdminXacmlSdkImpl(baseURL);

        //Domain CURD
        String domain = "testDomain2";
        String domainDesc = "testDomain2 desc1";

        //查询所有domain. Link::href=domainHash
        List<Link> domains = client.getDomains();
        log.info("{} domains found : {}", domains.size(), domains.stream().map(Link::getHref).toArray());

        //创建domain. 创建成功后返回生成的 domainHash(href)
        String domainHash = client.addDomain(domainDesc, domain);

        //删除domain
//        String domainHash = "bPr0KRhiEeytmuKSc33S1g";
//        String domainHash = "-4VpQBuEEeytmuKSc33S1g";
//        client.deleteDomain(domainHash);


        //Policy CURD
        String policyVersion = "v1.0";

        //获取domain下所有policy
        List<PolicySet> policySetList = client.getPolicies(domainHash, null);

        //获取domain下单个policy
        String policyId = "root";
        PolicySet policySet = client.getPolicy(domainHash, "0.1.0", null);
        PolicySet policySet1 = client.getPolicy(domainHash, null, "root");

        //创建policy， 先创建PolicySet, 再添加到policy中
        policyId = "root3";
        String policyDesc = "policyDesc1";
        PolicySet policySet2 = client.createSimplePolicy(domainHash, policyId, policyDesc + "", Collections.emptyList());
        Link link = client.addPolicy(domainHash, policySet2);
//
        String a = client.getPolicy(domain, policyVersion, policyId).getDescription();
//        System.out.println(a);

        //查询单个policy: 通过domain、policyId获取PolicySet
//        log.info("The description of this policy is {}", client.getPolicy(newDomainID, null, "myTestPolicy").getDescription());
//        log.error("(R)ead");
//        log.info("policies in {} are {}", newDomainID, client.getPoliciesNames(newDomainID).stream().map(Link::getHref).toArray());
//
//        //仅仅获取policy domain
//        log.info("getPolicy domain only");
//        log.debug(client.getPolicy(newDomainID, null, null).toString());
//
//        //获取policy domain + policyId
//        log.info("getPolicy domain + policyID");
//        log.debug(client.getPolicy(newDomainID, null, "myTestPolicy").toString());
//
//        //获取policy domain + policyId + version
//        log.info("getPolicy domain + policyID + version");
//        log.debug(client.getPolicy(newDomainID, "1.0.0", "myTestPolicy").toString());
//
//        //获取所有policies domain
//        log.info("getPolicies domain only");
//        log.debug(Arrays.toString(client.getPolicies(newDomainID, null).toArray()));
//
//        //获取所有policies domain + policyId
//        log.info("getPolicies domain + policy ID");
//        log.debug(Arrays.toString(client.getPolicies(newDomainID, "myTestPolicy").toArray()));
//
//        //创建policy
//        log.error("(U)pdate");
//        set = client.createSimplePolicy(newDomainID, "myTestPolicy", "Test policy", Collections.emptyList());
//        client.addPolicy(newDomainID, set);

    }
}
