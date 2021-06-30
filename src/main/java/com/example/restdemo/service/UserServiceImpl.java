package com.example.restdemo.service;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.example.restdemo.model.User;
import lombok.ToString;

@Service
@ToString
public class UserServiceImpl implements UserService {

    private static final String URL_USER = "http://91.241.64.178:7081/api/users";
    private static final RestOperations rest = new RestTemplate();
    private static final CookieHandler cm = new CookieManager();

    static {
        CookieHandler.setDefault(cm);
    }

//    ResponseEntity<String> response = restTemplate.getForEntity(URL_USER + "/1", String.class);
//    assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
//
//    User user = restTemplate.getForObject(URL_USER + "/1", User.class);
//    assertThat(user.getName(), notNullValue());
//    assertThat(user.getId(), is(1L));

    private static <T> ResponseEntity<String> getResponse(HttpMethod httpMethod,
                                                          HttpEntity<T> entity) {
        return rest.exchange(URL_USER,
                             httpMethod,
                             entity,
                             String.class);
    }

    @Override
    public String getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> result = getResponse(HttpMethod.GET, entity);

        return result.getHeaders().getFirst("set-cookie");
    }

    @Override
    public String newUser(String cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, cookies);

        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 42);

        return getResponse(HttpMethod.POST, new HttpEntity<>(newUser, headers)).getBody();
    }

    @Override
    public String updateUser(String cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, cookies);

        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        newUser.setAge((byte) 42);

        return getResponse(HttpMethod.PUT, new HttpEntity<>(newUser, headers)).getBody();
    }

    @Override
    public String delUser(String cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, cookies);

        return rest.exchange(URL_USER + "/3",
                             HttpMethod.DELETE,
                             new HttpEntity<>(headers),
                             String.class)
                   .getBody();
    }
}
