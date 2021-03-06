package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.springframework.stereotype.Repository;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.UserModel;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class UserObjectifyDao extends AbstractDAO<UserModel> {
    // получение из хранилища объекта типа UserModel по глобальному googleId
    public UserModel read(String googleId) throws Exception {
        return (UserModel) ObjectifyService.run(
                (Work) () -> ofy().load().type(UserModel.class)
                        .filter("googleId", googleId) // отобрать только объекты UserModel с заданным именем
                        .first() // получить только первый из найденных объектов
                        .now() // выполнить получение одного найденного объекта немедленно
        );
    }
}
