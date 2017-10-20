package com.fererlab.wfm.rest.mapper;

import com.fererlab.wfm.model.UserModel;
import com.fererlab.wfm.rest.dto.LoginRequestDTO;
import com.fererlab.wfm.rest.dto.LoginResponseDTO;
import com.fererlab.wfm.service.model.LoginRequestModel;
import com.fererlab.wfm.service.model.LoginResponseModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

import javax.inject.Named;

@Named("UserMapper")
public class UserMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {
        factory.classMap(LoginRequestDTO.class, LoginRequestModel.class)
            .byDefault()
            .register();
        factory.classMap(LoginResponseModel.class, LoginResponseDTO.class)
            .byDefault()
            .register();
    }

}
