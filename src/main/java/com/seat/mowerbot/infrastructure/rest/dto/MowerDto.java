package com.seat.mowerbot.infrastructure.rest.dto;

import jakarta.validation.constraints.NotNull;

public class MowerDto {

    @NotNull
    private LocationDto location;
    @NotNull
    private String commands;

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}
