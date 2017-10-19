package com.fererlab.wssd;

import com.fererlab.wssd.rest.HelloResource;
import com.fererlab.wssd.rest.LoginResource;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class WssdApplication extends Application {

    public WssdApplication() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("0.0.1");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8081");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("com.fererlab.wssd.rest");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> resources = new HashSet<>();
        resources.add(HelloResource.class);
        resources.add(LoginResource.class);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

}
