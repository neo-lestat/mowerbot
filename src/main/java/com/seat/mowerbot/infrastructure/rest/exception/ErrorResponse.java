package com.seat.mowerbot.infrastructure.rest.exception;

public record ErrorResponse(int statusCode, String message) { }
