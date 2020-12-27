package io.zenbydef.restclient;

import io.zenbydef.models.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserClient {
    private static final String apihost = "http://91.241.64.178:7081/api/users";
    private static RestTemplate restTemplate;
    private HttpHeaders headers;
    static User userForEdit;


    public UserClient(RestTemplateBuilder templateBuilder) {
        userForEdit = new User(3L, "Thomas", "Shelby", (byte) 1);
        restTemplate = templateBuilder.build();
        headers = getHttpHeaders();
    }

    public HttpHeaders getHttpHeaders() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(apihost, User[].class);
        String header = response.getHeaders().getFirst("Set-Cookie");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cookie", header);
        return httpHeaders;
    }

    public String getCode() {
        String result = postUser() + " " + putUser() + " " + deleteUser();
        return result;
    }

    String postUser() {
        HttpEntity<?> restEntity = new HttpEntity<>(userForEdit, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apihost,
                HttpMethod.POST,
                restEntity,
                String.class);

        String header = responseEntity.getHeaders().getFirst("Set-Cookie");
        headers.add("Cookie", header);
        return responseEntity.getBody();
    }

    String putUser() {
        userForEdit.setName("Thomas");
        userForEdit.setName("shelby");

        HttpEntity<User> restEntity = new HttpEntity<>(userForEdit, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apihost,
                HttpMethod.POST,
                restEntity,
                String.class);

        String header = responseEntity.getHeaders().getFirst("Set-Cookie");
        headers.add("Cookie", header);

        return responseEntity.getBody();
    }

    String deleteUser() {
        HttpEntity<?> restEntity = new HttpEntity<>(userForEdit, headers);

        Map<String, Integer> map = new HashMap<>();
        map.put("id", 3);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apihost + "/{id}",
                HttpMethod.DELETE,
                restEntity,
                String.class,
                map);

        String header = responseEntity.getHeaders().getFirst("Set-Cookie");
        headers.add("Cookie", header);

        return responseEntity.getBody();
    }
}