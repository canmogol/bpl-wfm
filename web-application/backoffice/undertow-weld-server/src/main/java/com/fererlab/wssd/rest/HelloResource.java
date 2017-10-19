package com.fererlab.wssd.rest;

import com.fererlab.wssd.model.HelloModel;
import com.fererlab.wssd.repository.HelloCommandRepository;
import com.fererlab.wssd.repository.HelloQueryRepository;
import com.fererlab.wssd.repository.HelloRepository;
import com.fererlab.wssd.service.HelloService;

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
    HelloRepository repository;

    @Inject
    HelloQueryRepository queryRepository;

    @Inject
    HelloCommandRepository commandRepository;

    double random = new Random().nextDouble();

    @GET
    @Produces("text/plain")
    public String sayHi(@QueryParam("name") String name) {
        System.out.println("repository: " + repository);
        System.out.println("commandRepository: " + commandRepository);
        System.out.println("queryRepository: " + queryRepository);
        commandRepository.create(new HelloModel("Hi"));
        System.out.println("queryRepository findAll: " + queryRepository.findAll());
        return service.sayHi(name) + " REST:" + random;
    }

}
