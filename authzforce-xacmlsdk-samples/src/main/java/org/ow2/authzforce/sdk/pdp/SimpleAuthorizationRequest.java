package org.ow2.authzforce.sdk.pdp;

import org.ow2.authzforce.sdk.core.schema.Action;
import org.ow2.authzforce.sdk.core.schema.Resource;
import org.ow2.authzforce.sdk.core.schema.Response;
import org.ow2.authzforce.sdk.core.schema.Responses;
import org.ow2.authzforce.sdk.core.schema.Subject;
import org.ow2.authzforce.sdk.core.schema.category.ActionCategory;
import org.ow2.authzforce.sdk.core.schema.category.EnvironmentCategory;
import org.ow2.authzforce.sdk.core.schema.category.ResourceCategory;
import org.ow2.authzforce.sdk.core.schema.category.SubjectCategory;
import org.ow2.authzforce.sdk.exceptions.XacmlSdkException;
import org.ow2.authzforce.sdk.impl.XacmlSdkImpl;
import org.ow2.authzforce.sdk.utils.ServerSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class SimpleAuthorizationRequest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAuthorizationRequest.class);


//	private static final String SUBJECT = "ThalesId";
//	private static final String RESOURCE = "http://www.opencloudware.org12222";
//	private static final String ACTION = "HEAD";
	private static final String SUBJECT = "liudehua3";
	private static final String RESOURCE = "http://localhost:8080/user/create";
	private static final String ACTION = "delete";

	public static void main(String[] args) throws XacmlSdkException {
		SubjectCategory subjectCat = new SubjectCategory();
		ResourceCategory resourceCat = new ResourceCategory();
		ActionCategory actionCategory = new ActionCategory();
		EnvironmentCategory environmentCategory = new EnvironmentCategory();

		subjectCat.addAttribute(new Subject(SUBJECT));
		resourceCat.addAttribute(new Resource(RESOURCE));
		actionCategory.addAttribute(new Action(ACTION));
		environmentCategory.addAttribute(null);

		URI PDP_ENDPOINT = ServerSetup.getRootURL(ServerSetup.getServer());
//		URI PDP_ENDPOINT = "http://localhost:8080/authzforce-ce/domains/bPr0KRhiEeytmuKSc33S1g/pap/policies/c9e0e12d-8f96-4306-9e9b-1b67522e0a73/1.0.0";
		String externalID = "testDomain1";
		String DOMAIN_ID = "bPr0KRhiEeytmuKSc33S1g";
//		String DOMAIN_ID = PapService.setupBasicDomain(PDP_ENDPOINT,externalID);//setupBasicDomain方法会自动创建domain、policy
		XacmlSdkImpl myXacml = new XacmlSdkImpl(PDP_ENDPOINT, DOMAIN_ID, null);
		Responses responses = null;
		try {
			responses = myXacml.getAuthZ(subjectCat, resourceCat, actionCategory, environmentCategory);
		} catch (XacmlSdkException e) {
			LOGGER.error(e.getLocalizedMessage());
			LOGGER.error(e.getCause().getLocalizedMessage());
		}
		if (null != responses) {
			for (Response response : responses.getResponses()) {
				LOGGER.info(response.getActionId() + " on " + response.getResourceId() + ": "
						+ response.getDecision().value() + " for " + response.getSubjectId());
			}
		}
	}

}
