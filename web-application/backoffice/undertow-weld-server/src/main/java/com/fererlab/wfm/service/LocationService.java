package com.fererlab.wfm.service;


import com.fererlab.common.property.Property;
import com.fererlab.wfm.model.LocationModel;
import com.fererlab.wfm.repository.LocationCRUDRepository;
import com.fererlab.wfm.service.model.AddLocationRequestModel;
import com.fererlab.wfm.service.model.AddLocationResponseModel;
import com.fererlab.wfm.service.model.LocationResponseModel;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

public class LocationService {

    private final static Logger logger = LoggerFactory.getLogger(LocationService.class);

    @Inject
    LocationCRUDRepository repository;

    @Inject
    @Named("LocationModelEntityMapper")
    MapperFacade mapperFacade;

    @Inject
    @Property("app.name")
    private String applicationName;


    public AddLocationResponseModel addLocation(AddLocationRequestModel addLocationRequestModel) {
        logger.debug("LocationService addLocation started");
        LocationModel locationModel = mapperFacade.map(addLocationRequestModel, LocationModel.class);
        repository.create(locationModel);
        AddLocationResponseModel addLocationResponseModel = mapperFacade.map(locationModel, AddLocationResponseModel.class);
        logger.debug("LocationService addLocation finished");
        return addLocationResponseModel;
    }

    public List<LocationResponseModel> getAllLocationResponseModel() {
        logger.debug("LocationService getAllLocationResponseModel started");
        List<LocationModel> locationModels = repository.findAll();
        List<LocationResponseModel> locationResponseModels = locationModels.stream().map(locationModel -> mapperFacade.map(locationModel, LocationResponseModel.class)).collect(Collectors.toList());
        logger.debug("LocationService getAllLocationResponseModel finished");
        return locationResponseModels;
    }

    public void deleteLocation(Integer id) {
        logger.debug("LocationService deleteLocation started");
        repository.delete(id);
        logger.debug("LocationService getAllLocationResponseModel finished");
    }
}
