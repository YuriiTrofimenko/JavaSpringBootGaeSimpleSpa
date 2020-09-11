package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.UserObjectifyDao;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.UserModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces.IAuthService;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces.IUserService;

@Service
public class AuthObjectifyService implements IAuthService {

    @Override
    public ResponseModel onSignOut() {
        return ResponseModel.builder()
            .status(ResponseModel.SUCCESS_STATUS)
            .message("Signed out")
            .build();
    }
}
