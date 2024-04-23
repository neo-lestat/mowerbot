package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;

import java.util.HashMap;
import java.util.Map;

import static com.seat.mowerbot.domain.model.Cardinal.*;

public class RotateRightCommand implements MowerCommand {

    private static final Map<Cardinal, Cardinal> rightMovements;

    static {
        rightMovements = new HashMap<>();
        rightMovements.put(NORTH, EAST);
        rightMovements.put(WEST, NORTH);
        rightMovements.put(EAST, SOUTH);
        rightMovements.put(SOUTH, WEST);
    }

    @Override
    public Mower execute(Mower mower) {
        Cardinal newOrientation = rightMovements.get(mower.direction());
        return new Mower.Builder().from(mower).withDirection(newOrientation);
    }

}
