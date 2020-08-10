package com.seat.mowerbot.infrastructure.rest.request;

import com.seat.mowerbot.domain.Location;

import javax.validation.constraints.NotNull;

public class MowerData {

    @NotNull
    private Location location;
    @NotNull
    private String commands;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}
