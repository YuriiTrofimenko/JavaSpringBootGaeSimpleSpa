package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.UserModel;

public interface IAuthService {
    ResponseModel onSignOut();
}
