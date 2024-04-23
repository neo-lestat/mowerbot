package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;

import java.util.HashMap;
import java.util.Map;

import static com.seat.mowerbot.domain.model.Cardinal.*;

public class RotateLeftCommand implements MowerCommand {

    private static final Map<Cardinal, Cardinal> leftMovements;

    static {
        leftMovements = new HashMap<>();
        leftMovements.put(NORTH, WEST);
        leftMovements.put(WEST, SOUTH);
        leftMovements.put(EAST, NORTH);
        leftMovements.put(SOUTH, EAST);
    }

    @Override
    public Mower execute(Mower mower) {
        Cardinal newOrientation = leftMovements.get(mower.direction());
        return new Mower.Builder().from(mower).withDirection(newOrientation);
    }

}
