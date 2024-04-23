package com.seat.mowerbot.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;

public record MowerResponseDto(@NotNull LocationDto location, @NotNull String direction) {

}
