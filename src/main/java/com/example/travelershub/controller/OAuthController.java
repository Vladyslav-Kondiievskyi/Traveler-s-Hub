package com.example.travelershub.controller;

import com.example.travelershub.model.User;
import com.example.travelershub.repository.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class OAuthController {

    private final UserRepository userRepository;

    @Autowired
    public OAuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/login/oauth2/code/google")
    public String oauthLogin(@RequestParam("code") String code, @RequestParam("state") String state) {
        // Отправляем запрос на получение токена доступа
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", "your_client_id");
        body.add("client_secret", "your_client_secret");
        body.add("redirect_uri", "http://localhost:8080/login/oauth2/code/google");
        body.add("grant_type", "authorization_code");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleTokenResponse> response = restTemplate.exchange(
                "https://oauth2.googleapis.com/token",
                HttpMethod.POST,
                request,
                GoogleTokenResponse.class
        );

        // Сохраняем токен доступа в базу данных
        GoogleTokenResponse tokenResponse = response.getBody();
        User user = new User();
        user.setPassword(tokenResponse.getAccessToken());
        userRepository.save(user);

        // Возвращаем имя представления (view name)
        return "oauth_login";
    }
}
