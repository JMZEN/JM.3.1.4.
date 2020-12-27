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

    @Test
    void getSAllElements() {
        HttpHeaders httpHeaders = client.getHttpHeaders();
        String result = client.getCode();

        System.out.println(result);
    }

    @Test
    void notRightUsageTest() {
        HttpHeaders httpHeaders = client.getHttpHeaders();
        String result = client.postUser() + client.putUser() + client.deleteUser();
        System.out.println(result);
    }
}