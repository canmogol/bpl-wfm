package com.fererlab.wfm.service;


import com.fererlab.common.property.Property;
import com.fererlab.wfm.model.HelloModel;
import com.fererlab.wfm.repository.HelloCRUDRepository;
import com.fererlab.wfm.repository.HelloCommandRepository;
import com.fererlab.wfm.repository.HelloQueryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Random;

public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @Inject
    HelloCRUDRepository repository;

    @Inject
    HelloQueryRepository queryRepository;

    @Inject
    HelloCommandRepository commandRepository;

    @Inject
    @Property("app.name")
    private String applicationName;

    private double random = new Random().nextDouble();

    public String sayHi(String name) {

        commandRepository.create(new HelloModel("Hi"));
        logger.debug("queryRepository findAll: " + queryRepository.findAll());
        logger.debug("repository findAll: " + repository.findAll());
        logger.debug("queryRepository count: " + queryRepository.count());
        logger.debug("repository count: " + repository.count());

        return String.format("App name '%1s' Hi %2s SERVICE: %3s", applicationName, name, random);
    }

}
