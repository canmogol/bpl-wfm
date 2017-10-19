package com.fererlab.wfm.service;


import com.fererlab.wfm.model.UserModel;
import com.fererlab.wfm.repository.UserCommandRepository;
import com.fererlab.wfm.repository.UserQueryRepository;
import com.fererlab.wfm.service.model.LoginRequestModel;
import com.fererlab.wfm.service.model.LoginResponseModel;

import javax.inject.Inject;
import java.util.Optional;

public class LoginService {

    @Inject
    UserQueryRepository queryRepository;

    @Inject
    UserCommandRepository commandRepository;

    public LoginResponseModel login(LoginRequestModel requestModel) {
        final UserModel userModel = new UserModel();
        userModel.setUsername("1");
        userModel.setPassword("1");
        userModel.setName("acm");
        userModel.setSite("USA");
        userModel.setAge(20);
        commandRepository.create(userModel);

        Optional<UserModel> userModelOptional = queryRepository.findByUsernameAndPassword(requestModel.getUsername(), requestModel.getPassword());
        if (userModelOptional.isPresent()) {
            return new LoginResponseModel("success", userModelOptional.get().getName());
        } else {
            return new LoginResponseModel("fail", "");
        }
    }

}
