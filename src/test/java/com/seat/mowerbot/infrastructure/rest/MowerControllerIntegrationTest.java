package com.seat.mowerbot.infrastructure.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.infrastructure.rest.dto.LocationDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowerDto;
import com.seat.mowerbot.infrastructure.rest.dto.MowersDto;
import com.seat.mowerbot.infrastructure.rest.dto.PlateauDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.URISyntaxException;
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
    public void testCommandsOk() throws JsonProcessingException, URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(buildMowerRequest("LMLMLMLMM"), headers);
        URI uri = new URI("http://localhost:" + port + "/v1/mower/commands");
        String response = restTemplate.postForObject(
                uri.toString(),
                request,
                String.class);
        assertEquals("[{\"x\":1,\"y\":3,\"direction\":\"N\"}]", response);
    }

    @Test
    public void testCommandsWithInvalidCommand() throws JsonProcessingException, URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(buildMowerRequest("j"), headers);
        URI uri = new URI("http://localhost:" + port + "/v1/mower/commands");
        String response = restTemplate.postForObject(
                uri.toString(),
                request,
                String.class);
        assertEquals("{\"statusCode\":400,\"message\":\"Wrong command type: J\"}", response);
    }

    @Test
    public void testCommandsLeadingToWrongLocation() throws JsonProcessingException, URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(buildMowerRequest("j"), headers);
        URI uri = new URI("http://localhost:" + port + "/v1/mower/commands");
        String response = restTemplate.postForObject(
                uri.toString(),
                request,
                String.class);
        assertEquals("{\"statusCode\":400,\"message\":\"Wrong command type: J\"}", response);
    }

    private String buildMowerRequest(String commands) throws JsonProcessingException {
        PlateauDto plateauDto = new PlateauDto(5, 5);
        LocationDto initLocation = new LocationDto(1, 2, Cardinal.NORTH.getShortLetter());
        MowerDto mowerDto = new MowerDto();
        mowerDto.setLocation(initLocation);
        mowerDto.setCommands(commands);
        MowersDto mowersDto = new MowersDto();
        mowersDto.setPlateauRequest(plateauDto);
        mowersDto.setMowers(Collections.singletonList(mowerDto));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(mowersDto);
    }

}
