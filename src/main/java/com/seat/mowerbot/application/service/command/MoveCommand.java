package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;

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
        return Cardinal.EAST.equals(location.getDirection()) || Cardinal.WEST.equals(location.getDirection());
    }

    private Location moveInAxisX() {
        int step = Cardinal.EAST.equals(location.getDirection()) ? 1 : -1;
        return new Location(location.getX() + step , location.getY(), location.getDirection());
    }

    private Location moveInAxisY() {
        int step = Cardinal.NORTH.equals(location.getDirection()) ? 1 : -1;
        return new Location(location.getX(), location.getY() + step, location.getDirection());
    }

}
