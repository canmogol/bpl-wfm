package com.fererlab.common;

import com.fererlab.common.property.PropertyProducer;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;


public class Server {

    public static final String STATIC_CONTENT_PATH = "STATIC_CONTENT_PATH";

    private static final Logger logger = LoggerFactory.getLogger(Server.class);


    public static void main(final String[] args) throws ClassNotFoundException {

        // initialize property producer, should read properties
        PropertyProducer.init();

        // create undertow JaxRS server
        UndertowJaxrsServer server = new UndertowJaxrsServer();

        // create Resteasy Deployment
        ResteasyDeployment resteasyDeployment = new ResteasyDeployment();
        resteasyDeployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");
        resteasyDeployment.setApplicationClass(PropertyProducer.getValue("app.class.name"));

        // create WebSocket deployment
        WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
        Class<?> annotated = Class.forName(PropertyProducer.getValue("app.websocket.resource"));
        webSocketDeploymentInfo.addEndpoint(annotated);

        // create deployment
        DeploymentInfo deploymentInfo = server.undertowDeployment(resteasyDeployment, PropertyProducer.getValue("app.rest.mapping.prefix"));
        deploymentInfo.setClassLoader(Server.class.getClassLoader());
        deploymentInfo.setContextPath(PropertyProducer.getValue("app.context.path"));
        deploymentInfo.setDeploymentName(PropertyProducer.getValue("app.deployment.name"));
        deploymentInfo.addListeners(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));
        deploymentInfo.addServletContextAttribute(WebSocketDeploymentInfo.ATTRIBUTE_NAME, webSocketDeploymentInfo);

        // deploy to server
        server.deploy(deploymentInfo);

        // add static content to server
        // STATIC_CONTENT_PATH might be a VM parameter or environment variable
        // ex: -DSTATIC_CONTENT_PATH=/home/can/projects/canmogol/bpl-wfm/web-application/backoffice/web/
        // C:\canm\garb\bpl-wfm\web-application\backoffice\web
        server.addResourcePrefixPath(
            "/",
            Handlers.resource(
                new PathResourceManager(
                    Paths.get(System.getProperty(STATIC_CONTENT_PATH)),
                    100
                )
            ).setDirectoryListingEnabled(true)
        );

        // start server
        server.start(Undertow.builder().addHttpListener(
            Integer.valueOf(PropertyProducer.getValue("app.port")),
            PropertyProducer.getValue("app.host")
        ));

    }


}
