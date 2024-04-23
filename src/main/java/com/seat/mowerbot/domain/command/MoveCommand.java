package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;

public class MoveCommand implements MowerCommand {


    @Override
    public Mower execute(Mower mower) {
        Location newLocation = isMoveInAxisX(mower) ? moveInAxisX(mower) : moveInAxisY(mower);
        Mower newMower = new Mower.Builder().from(mower).withLocation(newLocation);
        validateNewLocation(newMower);
        return newMower;
    }

    private boolean isMoveInAxisX(Mower mower) {
        return Cardinal.EAST.equals(mower.direction()) || Cardinal.WEST.equals(mower.direction());
    }

    private Location moveInAxisX(Mower mower) {
        int step = Cardinal.EAST.equals(mower.direction()) ? 1 : -1;
        return new Location(mower.location().x() + step, mower.location().y());
    }

    private Location moveInAxisY(Mower mower) {
        int step = Cardinal.NORTH.equals(mower.direction()) ? 1 : -1;
        return new Location(mower.location().x(), mower.location().y() + step);
    }

    private void validateNewLocation(Mower mower) {
        LocationValidation locationValidation = new LocationValidation(mower.plateau(), mower.location());
        if (locationValidation.isNotValid()) {
            String message = String.format("Error invalid %s", mower.location());
            throw new MowerCommandException(message);
        }
    }

}
