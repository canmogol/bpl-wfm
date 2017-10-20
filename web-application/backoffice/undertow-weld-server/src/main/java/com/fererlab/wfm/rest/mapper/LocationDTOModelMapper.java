package com.fererlab.wfm.rest.mapper;

import com.fererlab.wfm.rest.dto.AddLocationRequestDTO;
import com.fererlab.wfm.rest.dto.AddLocationResponseDTO;
import com.fererlab.wfm.rest.dto.LocationResponseDTO;
import com.fererlab.wfm.service.model.AddLocationRequestModel;
import com.fererlab.wfm.service.model.AddLocationResponseModel;
import com.fererlab.wfm.service.model.LocationResponseModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

import javax.inject.Named;

@Named("LocationDTOModelMapper")
public class LocationDTOModelMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(AddLocationRequestDTO.class, AddLocationRequestModel.class)
            .byDefault()
            .register();
        factory.classMap(AddLocationResponseModel.class, AddLocationResponseDTO.class)
            .byDefault()
            .register();

        factory.classMap(LocationResponseModel.class, LocationResponseDTO.class)
            .byDefault()
            .register();
    }

}
