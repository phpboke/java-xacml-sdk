package org.ow2.authzforce.sdk.utils;

import org.testcontainers.containers.GenericContainer;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ServerSetup {
    private static final String IMAGE_NAME = "authzforce/server:release-8.1.0";
    private static final int PORT = 8080;

    public static GenericContainer getServer() {
        return null;
//        GenericContainer server = new GenericContainer(IMAGE_NAME)
//                .withExposedPorts(PORT)
//                .waitingFor(Wait.forLogMessage(".*Server startup.*",1));
//
//        server.start();
//        return server;
    }

    public static URI getRootURL(GenericContainer server) {
//        http://localhost:8080/authzforce-ce/domains
        String host = System.getProperty( "host" , "localhost" );
        String port = System.getProperty( "port" , "8080" );
        URI uri = UriBuilder.fromUri( "http://" + host + ":" + port+ "/authzforce-ce" )
//                .path( "123" )
//                .queryParam( "sort" , "name" )
                .build(host, port);

//        URI uri = UriBuilder.fromUri("http://localhost:8080/authzforce-ce/domains/bPr0KRhiEeytmuKSc33S1g/pap/policies/c9e0e12d-8f96-4306-9e9b-1b67522e0a73/1.0.0")
//                .build(host, port);
        return uri;
//        return UriBuilder.fromUri("authzforce-ce")
//                .scheme("http")
//                .host(server.getContainerIpAddress())
//                .port(server.getMappedPort(PORT))
//                .build();
    }
}
