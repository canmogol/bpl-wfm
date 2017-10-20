package com.fererlab.wfm;

import com.fererlab.common.BaseApplication;
import com.fererlab.wfm.rest.HelloResource;
import com.fererlab.wfm.rest.LoginResource;
import com.fererlab.wfm.rest.LocationResource;

import java.util.Set;

public class WFMApplication extends BaseApplication {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = super.getClasses();
        resources.add(HelloResource.class);
        resources.add(LoginResource.class);
        resources.add(LocationResource.class);
        return resources;
    }

}
