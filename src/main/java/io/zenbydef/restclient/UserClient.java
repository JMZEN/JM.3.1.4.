package io.zenbydef.restclient;

import io.zenbydef.models.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserClient {
    static final String apihost = "http://91.241.64.178:7081/api/users";
    private static RestTemplate restTemplate = new RestTemplate();
    private static HttpHeaders headers;
    private static User userForEdit;

    public static void main(String[] args) {
        userForEdit = new User(3L, "James", "Brown", (byte) 1);
        headers = getHttpHeaders();

        UserClient userClient = new UserClient();

        System.out.println(userClient.postUser() + userClient.putUser() + userClient.deleteUser());
    }

    public static HttpHeaders getHttpHeaders() {
        ResponseEntity<List<User>> userResponse = restTemplate
                .exchange(
                        apihost,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        String header = userResponse.getHeaders().getFirst("Set-Cookie");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", header);
        return httpHeaders;
    }

    String postUser() {
        HttpEntity<?> restEntity = new HttpEntity<>(userForEdit, headers);

        return restTemplate.exchange(apihost,
                HttpMethod.POST,
                restEntity,
                String.class).getBody();
    }

    String putUser() {
        userForEdit.setName("Thomas");
        userForEdit.setLastName("Shelby");

        HttpEntity<User> restEntity = new HttpEntity<>(userForEdit, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(apihost,
                HttpMethod.PUT,
                restEntity,
                String.class);

        return responseEntity.getBody();
    }

    String deleteUser() {
        HttpEntity<?> restEntity = new HttpEntity<>(null, headers);

        Map<String, Integer> map = new HashMap<>();
        map.put("id", Math.toIntExact(userForEdit.getId()));

        ResponseEntity<String> responseEntity = restTemplate.exchange(apihost + "/{id}",
                HttpMethod.DELETE,
                restEntity,
                String.class,
                map);

        return responseEntity.getBody();
    }
}