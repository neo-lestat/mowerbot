package com.seat.mowerbot.infrastructure.rest.exception;

import com.seat.mowerbot.application.service.command.MowerCommandException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void testHandleMowerCommandException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleMowerCommandException(new MowerCommandException("test"));
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "test");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorResponse.toString(), responseEntity.getBody().toString());
    }
}
