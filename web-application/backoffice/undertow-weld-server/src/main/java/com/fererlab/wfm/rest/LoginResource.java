package com.fererlab.wfm.rest;

import com.fererlab.wfm.rest.dto.LoginRequestDTO;
import com.fererlab.wfm.rest.dto.LoginResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
        response = LoginResponseDTO.class
    )
    @ApiResponses(
        value = {
            @ApiResponse(code = 400, message = "Invalid username or password"),
            @ApiResponse(code = 404, message = "Username not found")
        }
    )
    @POST
    public LoginResponseDTO sayHi(LoginRequestDTO model) {
        System.out.println(model);
        return new LoginResponseDTO("success", "Can MOGOL");
    }

}
