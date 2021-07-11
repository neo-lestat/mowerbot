package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.infrastructure.rest.LocationDto;

import javax.validation.constraints.NotNull;

public class MowerData {

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
