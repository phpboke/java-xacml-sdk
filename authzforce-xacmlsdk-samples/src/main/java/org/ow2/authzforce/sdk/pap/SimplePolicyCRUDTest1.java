package org.ow2.authzforce.sdk.pap;


import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicySet;
import org.ow2.authzforce.sdk.AdminXacmlSdk;
import org.ow2.authzforce.sdk.impl.AdminXacmlSdkImpl;
import org.ow2.authzforce.sdk.utils.ServerSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Collections;

public class SimplePolicyCRUDTest1 {
    private static final Logger log = LoggerFactory.getLogger(SimplePolicyCRUDTest1.class);

    private static final String DOMAIN = "myTestDomain1";

    public static void main(String[] args) throws Exception {
        URI baseURL = ServerSetup.getRootURL(ServerSetup.getServer());
        AdminXacmlSdk client = new AdminXacmlSdkImpl(baseURL);

        //创建域
        String newDomainID = client.addDomain("test domain", DOMAIN);

        log.warn("***********policy CRUD operations***********");
        //创建policy: 先通过domain, policyId, description, data创建PolicySet, 再添加到policy中
        PolicySet set = client.createSimplePolicy(newDomainID, "myTestPolicy", "Test policy", Collections.emptyList());
        client.addPolicy(newDomainID, set);

        String a = client.getPolicy(newDomainID, null, "myTestPolicy").getDescription();
        System.out.println(a);

        //查询单个policy: 通过domain、policyId获取PolicySet
//        log.info("The description of this policy is {}", client.getPolicy(newDomainID, null, "myTestPolicy").getDescription());
//        log.error("(R)ead");
//        log.info("policies in {} are {}", newDomainID, client.getPoliciesNames(newDomainID).stream().map(Link::getHref).toArray());

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
//
    }
}
