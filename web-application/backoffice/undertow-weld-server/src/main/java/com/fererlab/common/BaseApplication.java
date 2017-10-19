package com.fererlab.common;

import com.fererlab.common.property.PropertyProducer;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseApplication extends Application {

    public BaseApplication() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(PropertyProducer.getValue("app.version"));
        beanConfig.setSchemes(PropertyProducer.getValue("app.schemes").split(","));
        String host = PropertyProducer.getValue("app.host") + ":" + PropertyProducer.getValue("app.port");
        beanConfig.setHost(host);
        beanConfig.setBasePath(PropertyProducer.getValue("app.context.path"));
        beanConfig.setResourcePackage(PropertyProducer.getValue("app.rest.resource.package"));
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

}
