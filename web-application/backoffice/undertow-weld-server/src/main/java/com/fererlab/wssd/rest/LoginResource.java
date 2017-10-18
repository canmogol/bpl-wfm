package com.fererlab.wssd.rest;

import com.fererlab.wssd.rest.model.LoginRequestModel;
import com.fererlab.wssd.rest.model.LoginResponseModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class LoginResource {

    @POST
    public LoginResponseModel sayHi(LoginRequestModel model) {
        System.out.println(model);
        return new LoginResponseModel("success", "Can MOGOL");
    }

}
