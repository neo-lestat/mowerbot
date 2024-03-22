package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;

import java.util.HashMap;
import java.util.Map;

import static com.seat.mowerbot.domain.Cardinal.*;

public class RotateRightCommand implements MowerCommand {

    private final Location location;

    private static final Map<Cardinal, Cardinal> rightMovements;

    static {
        rightMovements = new HashMap<>();
        rightMovements.put(NORTH, EAST);
        rightMovements.put(WEST, NORTH);
        rightMovements.put(EAST, SOUTH);
        rightMovements.put(SOUTH, WEST);
    }

    public RotateRightCommand(Location location) {
        this.location = location;
    }

    @Override
    public Location execute() {
        Cardinal newOrientation = rightMovements.get(location.direction());
        return new Location(location.x(), location.y(), newOrientation);
    }
}
