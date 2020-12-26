package io.zenbydef.restclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserClientTest {

    @Autowired
    UserClient client;

    @Test
    void getAllUsers() {
        String sessionId = client.getHttpHeaders();
        System.out.println(sessionId);
//        assertNotNull(sessionId);
    }


    @Test
    void get1Code() {
//        String sessionId = client.getAllUsers();
        String result = client.postUser();
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void get2Code() {
//        String sessionId = client.getAllUsers();
//        String firstPart = client.postUser(null);
        String result = client.putUser();
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void getCodeD() {
//        String sessionId = client.getAllUsers();
//        String firstPart = client.postUser(sessionId);
//        String secondPart = client.deleteUser(sessionId);

//        System.out.println(secondPart);
//        assertNotNull(secondPart);
    }
}