package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;

public class MoveCommand implements MowerCommand {

    private final Location location;

    public MoveCommand(Location location) {
        this.location = location;
    }

    @Override
    public Location execute() {
        return isMoveInAxisX() ? moveInAxisX() : moveInAxisY();
    }

    private boolean isMoveInAxisX() {
        return Cardinal.EAST.equals(location.direction()) || Cardinal.WEST.equals(location.direction());
    }

    private Location moveInAxisX() {
        int step = Cardinal.EAST.equals(location.direction()) ? 1 : -1;
        return new Location(location.x() + step, location.y(), location.direction());
    }

    private Location moveInAxisY() {
        int step = Cardinal.NORTH.equals(location.direction()) ? 1 : -1;
        return new Location(location.x(), location.y() + step, location.direction());
    }

}
