package com.fererlab.wfm.repository;

import com.fererlab.common.repository.CRUDRepository;
import com.fererlab.wfm.model.LocationModel;

import javax.inject.Inject;

public class LocationCRUDRepository extends CRUDRepository<LocationModel, Integer> {

    @Inject
    public LocationCRUDRepository(LocationCommandRepository locationCommandRepository, LocationQueryRepository locationQueryRepository) {
        super(locationCommandRepository, locationQueryRepository);
    }

}
