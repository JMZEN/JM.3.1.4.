package io.zenbydef.restclient;

import io.zenbydef.models.User;
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
    private final User userForEdit;
    private final RestTemplate restTemplate;
    private final String apihost;
    private final HttpHeaders headers;

    public UserClient() {
        userForEdit = new User(3L, "James", "Brown", (byte) 1);
        restTemplate = new RestTemplate();
        apihost = "http://91.241.64.178:7081/api/users";
        headers = getHttpHeaders();
    }

    public static void main(String[] args) {
        UserClient userClient = new UserClient();
        System.out.println(userClient.getCode());
    }

    private String getCode() {
        return postUser() + putUser() + deleteUser();
    }

    private HttpHeaders getHttpHeaders() {
        ResponseEntity<User[]> userResponse = restTemplate.exchange(
                apihost,
                HttpMethod.GET,
                null,
                User[].class);
        String sessionHeader = userResponse.getHeaders().getFirst("Set-Cookie");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionHeader);
        return httpHeaders;
    }

    private String postUser() {
        HttpEntity<User> restEntity = new HttpEntity<>(userForEdit, headers);

        return restTemplate.exchange(
                apihost,
                HttpMethod.POST,
                restEntity,
                String.class).getBody();
    }

    private String putUser() {
        userForEdit.setName("Thomas");
        userForEdit.setLastName("Shelby");

        HttpEntity<User> restEntity = new HttpEntity<>(userForEdit, headers);

        return restTemplate.exchange(
                apihost,
                HttpMethod.PUT,
                restEntity,
                String.class).getBody();
    }

    private String deleteUser() {
        HttpEntity<Void> restEntity = new HttpEntity<>(null, headers);

        Map<String, Integer> map = new HashMap<>();
        map.put("id", Math.toIntExact(userForEdit.getId()));

        return restTemplate.exchange(
                apihost + "/{id}",
                HttpMethod.DELETE,
                restEntity,
                String.class,
                map).getBody();
    }
}