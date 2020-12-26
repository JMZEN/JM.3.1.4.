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
    private final String apihost;
    private final RestTemplate restTemplate;
    private HttpHeaders headers = setHttpHeaders();
    static User userForEdit;


    public UserClient(RestTemplateBuilder templateBuilder) {
        userForEdit = new User(3L, "Thomas", "Shelby", (byte) 1);
        restTemplate = templateBuilder.build();
        apihost = "http://91.241.64.178:7081/api/users";
    }

    String getHttpHeaders() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(apihost, User[].class);
        return response.getHeaders()
                .getFirst("Set-Cookie");
    }

    private HttpHeaders setHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String cookieHeader = getHttpHeaders();
        httpHeaders.set("Cookie", cookieHeader);
        return httpHeaders;
    }


//    public String getCode() {
//        String partOneOfId = postUser();
//        String partTwoOfId = putUser();
//        String partThreeOfId = deleteUser();
//        return partOneOfId + partTwoOfId + partThreeOfId;
//    }
//
//
//    String postUser() {
//        HttpEntity<?> restEntity = new HttpEntity<>(userForEdit, headers);
//
//        return restTemplate.exchange(apihost,
//                HttpMethod.POST,
//                restEntity,
//                String.class).getBody();
//    }
//
//    String putUser() {
//        userForEdit.setName("Thomas");
//        userForEdit.setName("shelby");
//
//        HttpEntity<User> restEntity = new HttpEntity<>(userForEdit, headers);
//
//        return restTemplate.exchange(apihost,
//                HttpMethod.PUT,
//                restEntity,
//                String.class).getBody();
//    }
//
//    String deleteUser() {
//        HttpEntity<?> restEntity = new HttpEntity<>(userForEdit, headers);
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("id", 3);
//
//        return restTemplate.exchange(apihost + "/{id}",
//                HttpMethod.DELETE,
//                restEntity,
//                String.class,
//                map)
//                .getBody();
//    }
}