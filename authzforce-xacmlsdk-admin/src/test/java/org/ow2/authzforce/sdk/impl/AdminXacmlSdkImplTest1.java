package org.ow2.authzforce.sdk.impl;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicySet;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.ow2.authzforce.rest.api.xmlns.DomainProperties;
import org.ow2.authzforce.sdk.AdminXacmlSdk;
import org.ow2.authzforce.sdk.exceptions.XacmlSdkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Collections;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.ow2.authzforce.sdk.test.ScenarioRecorder.ENDPOINT_ADDRESS;
import static org.ow2.authzforce.sdk.test.ScenarioRecorder.PORT;

public class AdminXacmlSdkImplTest1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminXacmlSdkImplTest1.class);
    private static final String DOMAIN = "myTestDomain5";
    @Rule
    public WireMockRule server = new WireMockRule(options().port(PORT), false);
    private AdminXacmlSdk client;
    private String newDomainID = "test_domain_id_5";

    @Before
    public void setup() {
        client = new AdminXacmlSdkImpl(URI.create(ENDPOINT_ADDRESS));
    }

    @Test
    public void domainCrud() throws XacmlSdkException {
//        List<Link> domains = client.getDomains();
//        assertFalse(domains.isEmpty());
        newDomainID = client.addDomain("myTestDomain5 desc", DOMAIN);
        LOGGER.info("new domain {}", newDomainID);
        DomainProperties domainProperties = client.getDomain(newDomainID);
    System.out.println(domainProperties);
//        assertEquals(domainProperties.getExternalId(), DOMAIN);
//        client.deleteDomain(newDomainID);
    }

    @Test
    public void policyCrud() throws XacmlSdkException {
        PolicySet set = client.createSimplePolicy(newDomainID, "test domain5", "Test policy", Collections.emptyList());
        client.addPolicy(newDomainID, set);
        PolicySet policySet = client.getPolicy(newDomainID, null, "test domain5");
    System.out.println(policySet);
//        client.deletePolicy(newDomainID, null, "myTestPolicy");
    }
}
