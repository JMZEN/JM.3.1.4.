package io.zenbydef.restclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserClientTest {

    @Autowired
    UserClient client;

    @Test
    void getHttpHeaders() {
        HttpHeaders header = client.getHttpHeaders();
        System.out.println(header);
        assertNotNull(header);
    }

    @Test
    void getFirstElement() {
        HttpHeaders httpHeaders = client.getHttpHeaders();
        String result = client.postUser();
        System.out.println(result);
        assertNotNull(result);
    }
//
//    @Test
//    void getSecondElement() {
//        HttpHeaders httpHeaders = UserClient.setHttpHeaders();
//        String result = client.postUser();
//        String result2 = client.putUser();
//        System.out.println(result);
//        assertNotNull(result);
//    }
//
//    @Test
//    void get2Code() {
////        String sessionId = client.getAllUsers();
////        String firstPart = client.postUser(null);
//        String result = client.putUser();
//        System.out.println(result);
//        assertNotNull(result);
//    }

//    @Test
//    void getCodeD() {
////        String sessionId = client.getAllUsers();
////        String firstPart = client.postUser(sessionId);
////        String secondPart = client.deleteUser(sessionId);
//
////        System.out.println(secondPart);
////        assertNotNull(secondPart);
//    }
}