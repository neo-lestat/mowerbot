package com.seat.mowerbot.application.service.command;

import com.seat.mowerbot.domain.Cardinal;
import com.seat.mowerbot.domain.Location;

import java.util.HashMap;
import java.util.Map;

import static com.seat.mowerbot.domain.Cardinal.*;

public class RotateLeftCommand implements MowerCommand {

    private final Location location;

    private static final Map<Cardinal, Cardinal> leftMovements;

    static {
        leftMovements = new HashMap<>();
        leftMovements.put(NORTH, WEST);
        leftMovements.put(WEST, SOUTH);
        leftMovements.put(EAST, NORTH);
        leftMovements.put(SOUTH, EAST);
    }

    public RotateLeftCommand(Location location) {
        this.location = location;
    }

    @Override
    public Location execute() {
        Cardinal newOrientation = leftMovements.get(location.direction());
        return new Location(location.x(), location.y(), newOrientation);
    }

}
