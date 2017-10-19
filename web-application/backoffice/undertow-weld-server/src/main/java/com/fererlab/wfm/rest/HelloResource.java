package com.fererlab.wfm.rest;

import com.fererlab.wfm.model.HelloModel;
import com.fererlab.wfm.repository.HelloCommandRepository;
import com.fererlab.wfm.repository.HelloQueryRepository;
import com.fererlab.wfm.repository.HelloRepository;
import com.fererlab.wfm.service.HelloService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.Random;


@Path("/hi")
public class HelloResource {

    @Inject
    HelloService service;

    @Inject
    HelloQueryRepository queryRepository;

    @Inject
    HelloCommandRepository commandRepository;

    @Inject
    HelloRepository repository;

    double random = new Random().nextDouble();

    @GET
    @Produces("text/plain")
    public String sayHi(@QueryParam("name") String name) {
        System.out.println("repository: " + repository);
        System.out.println("commandRepository: " + commandRepository);
        System.out.println("queryRepository: " + queryRepository);
        commandRepository.create(new HelloModel("Hi"));
        System.out.println("queryRepository findAll: " + queryRepository.findAll());
        System.out.println("repository findAll: " + repository.findAll());
        return service.sayHi(name) + " REST:" + random;
    }

}
