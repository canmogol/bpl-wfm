package com.fererlab.wfm.repository;

import com.fererlab.common.repository.CRUDRepository;
import com.fererlab.wfm.model.HelloModel;

import javax.inject.Inject;

public class HelloCRUDRepository extends CRUDRepository<HelloModel, Integer> {

    @Inject
    public HelloCRUDRepository(HelloCommandRepository commandRepository, HelloQueryRepository queryRepository) {
        super(commandRepository, queryRepository);
    }

}
