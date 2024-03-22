package com.seat.mowerbot.infrastructure.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.infrastructure.rest.request.MowerData;
import com.seat.mowerbot.infrastructure.rest.request.MowerRequest;
import com.seat.mowerbot.infrastructure.rest.request.PlateauRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.URI;
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
    public void testEvaluateCommands() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(buildMowerRequest(), headers);
        URI uri = new URI("http://localhost:" + port + "/v1/mower/commands");
        String response = restTemplate.postForObject(
                uri.toString(),
                request,
                String.class);
        assertEquals("[{\"x\":1,\"y\":3,\"direction\":\"N\"}]", response);
    }

    private String buildMowerRequest() {
        PlateauRequest plateauRequest = new PlateauRequest(5, 5);
        LocationDto initLocation = new LocationDto(1, 2, Cardinal.NORTH.getShortLetter());
        String movements = "LMLMLMLMM";
        MowerData mowerData = new MowerData();
        mowerData.setLocation(initLocation);
        mowerData.setCommands(movements);
        MowerRequest mowerRequest = new MowerRequest();
        mowerRequest.setPlateauRequest(plateauRequest);
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
