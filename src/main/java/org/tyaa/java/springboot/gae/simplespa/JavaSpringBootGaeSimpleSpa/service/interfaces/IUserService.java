package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.UserModel;

public interface IUserService {
    ResponseModel create(UserModel userModel);
    ResponseModel update(UserModel userModel);
    ResponseModel getAll();
    ResponseModel delete(Long id) throws IllegalAccessException, InstantiationException;
    ResponseModel createOrGetUserByGoogleId(GoogleIdToken.Payload payload) throws Exception;
}
