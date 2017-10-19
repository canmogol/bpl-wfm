package com.fererlab.wssd.rest;

import com.fererlab.wssd.rest.model.LoginRequestModel;
import com.fererlab.wssd.rest.model.LoginResponseModel;
import io.swagger.annotations.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(value = "/login", tags = "login")
@Path("/login")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class LoginResource {

    @ApiOperation(
        value = "logs in a user",
        notes = "returns a model with user's name",
        response = LoginResponseModel.class
    )
    @ApiResponses(
        value = {
            @ApiResponse(code = 400, message = "Invalid username or password"),
            @ApiResponse(code = 404, message = "Username not found")
        }
    )
    @POST
    public LoginResponseModel sayHi(LoginRequestModel model) {
        System.out.println(model);
        return new LoginResponseModel("success", "Can MOGOL");
    }

}
