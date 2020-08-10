package com.seat.mowerbot.infrastructure.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.Plateau;
import com.seat.mowerbot.infrastructure.rest.request.MowerData;
import com.seat.mowerbot.infrastructure.rest.request.MowerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
* Integration test
* */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MowerControllerIntegrationTest {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPing() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/v1/mower/ping").toString(), String.class);
        assertEquals("pong", response.getBody());

    }

    @Test
    public void testEvaluateCommands() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(buildMowerRequest(), headers);
        String response = restTemplate.postForObject(
                new URL("http://localhost:" + port + "/v1/mower/commands").toString(),
                request,
                String.class);
        assertEquals("[{\"x\":1,\"y\":3,\"direction\":\"N\"}]", response);
    }

    private String buildMowerRequest(){
        Plateau plateau = new Plateau(5,5);
        Location initLocation = new Location(1,2, Cardinal.NORTH);
        String movements = "LMLMLMLMM";
        MowerData mowerData = new MowerData();
        mowerData.setLocation(initLocation);
        mowerData.setCommands(movements);
        MowerRequest mowerRequest = new MowerRequest();
        mowerRequest.setPlateau(plateau);
        mowerRequest.setMowerDataList(Collections.singletonList(mowerData));
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(mowerRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}