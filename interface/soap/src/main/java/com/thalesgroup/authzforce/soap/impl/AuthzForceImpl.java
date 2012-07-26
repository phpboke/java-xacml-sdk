package com.thalesgroup.authzforce.soap.impl;

import javax.jws.WebService;

import oasis.names.tc.xacml._2_0.context.schema.os.RequestType;
import oasis.names.tc.xacml._2_0.context.schema.os.ResponseType;

import com.thalesgroup.authzforce.soap.api.AuthzForce;

@SuppressWarnings("restriction")
@WebService(serviceName = "AuthzForce", portName = "AuthzForceSOAP", endpointInterface = "com.thalesgroup.authzforce.soap.api.AuthzForce", targetNamespace = "http://pdp.thalesgroup.com/")
public class AuthzForceImpl implements AuthzForce {

	public AuthzForceImpl() {
		PDPWrapper pdpWrapper = PDPWrapper.getInstance();
		if(pdpWrapper == null) {
			System.out.println("Error initializing PDP instance");
		}
	}

	public ResponseType xacmlAuthzDecision(RequestType xacmlAuthzDecisionRequest) {
		return PDPWrapper.getInstance().xacmlAuthzDecision(xacmlAuthzDecisionRequest);
	}

	
	public String reloadPolicies() {
		return PDPWrapper.getInstance().reloadPolicies();
	}

}
