package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.TokenModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.UserModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.AuthObjectifyService;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.UserObjectifyService;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthObjectifyService authService;

    @Autowired
    UserObjectifyService userService;

    // your_app_id.apps.googleusercontent.com
    private static final String CLIENT_ID = "249200472631-e7phm7ng0toufdea9lv38avkusrkvutg.apps.googleusercontent.com";

    /* вызывается фронтендом после успешного получения им токена аутентификации
    * от гугл сервера аутентификации */
    /**
     * @param tokenModel - модель с одним полем - токеном аутентификации
     * @param httpSession - доступ к http-сеансу, получаемый автоматически внедрением зависимости
     * через аргумент метода
     * */
    @PostMapping(value = "/user/login")
    public ResponseEntity<ResponseModel> login(@RequestBody TokenModel tokenModel, HttpSession httpSession) throws Exception {
        System.out.println(tokenModel.idToken);
        // получение объекта управления аутентификацией гугл
        // с указанием CLIENT_ID
        JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID)).build();
        String idTokenString = tokenModel.idToken;
        // запрос на срвер гугл аутентификации: проверить валидность токена,
        // ранее полученного фронтендом
        // и получение в овет токена, расшифровкого которого
        // можно получить данные учетной записи
        GoogleIdToken idToken = verifier.verify(idTokenString);
        // если проверка токена прошла успешно
        if (idToken != null && !idToken.equals("")) {
            // получение данных учетной записи
            GoogleIdToken.Payload payload = idToken.getPayload();
            // использовальзование и/или сохранение данных учетной записи
            ResponseModel responseModel =
                    userService.createOrGetUserByGoogleId(payload);
            //  в http-сеанс сохраняем DataStore идентификатор пользователя
            httpSession.setAttribute("user_id", ((UserModel)(responseModel.getData())).getId());
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /* вызывается фронтендом после получения отклика сервера гугл-аутентификации
    * об успешном выходе из аккаунта */
    @GetMapping("/user/signedout")
    public ResponseEntity<ResponseModel> signedOut(HttpSession httpSession) {
        // после выхода пользователя из аккаунта
        // пытаемся получить объект его корзины покупок из объекта сеанса,
        // и если получили - удаляем корзину из сеанса
        /* Cart cart = (Cart) httpSession.getAttribute("CART");
        if (cart != null) {
            httpSession.removeAttribute("CART");
        } */
        // стирание идентификатора пользователя из http-сеанса
        httpSession.removeAttribute("user_id");
        return new ResponseEntity<>(authService.onSignOut(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseModel> getAllUsers(HttpSession httpSession) {
        /* if (httpSession.getAttribute("user_id") != null) {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } */
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
