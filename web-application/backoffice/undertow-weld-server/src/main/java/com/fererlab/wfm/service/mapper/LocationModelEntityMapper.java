package com.fererlab.wfm.service.mapper;

import com.fererlab.wfm.model.LocationModel;
import com.fererlab.wfm.service.model.AddLocationRequestModel;
import com.fererlab.wfm.service.model.AddLocationResponseModel;
import com.fererlab.wfm.service.model.LocationResponseModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

import javax.inject.Named;

@Named("LocationModelEntityMapper")
public class LocationModelEntityMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(AddLocationRequestModel.class, LocationModel.class)
            .byDefault()
            .register();
        factory.classMap(LocationModel.class, AddLocationResponseModel.class)
            .byDefault()
            .register();
        factory.classMap(LocationModel.class, LocationResponseModel.class)
            .byDefault()
            .register();
    }

}
