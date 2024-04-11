package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;

import java.util.HashMap;
import java.util.Map;

import static com.seat.mowerbot.domain.model.Cardinal.*;

public class RotateRightCommand implements MowerCommand {

    private final Mower mower;

    private static final Map<Cardinal, Cardinal> rightMovements;

    static {
        rightMovements = new HashMap<>();
        rightMovements.put(NORTH, EAST);
        rightMovements.put(WEST, NORTH);
        rightMovements.put(EAST, SOUTH);
        rightMovements.put(SOUTH, WEST);
    }

    public RotateRightCommand(Mower mower) {
        this.mower = mower;
    }

    @Override
    public Mower execute() {
        Location location = mower.location();
        Cardinal newOrientation = rightMovements.get(location.direction());
        return new Mower(mower.plateau(), new Location(location.x(), location.y(), newOrientation));
    }

}
