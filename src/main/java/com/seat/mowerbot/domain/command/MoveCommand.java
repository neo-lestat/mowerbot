package com.seat.mowerbot.domain.command;

import com.seat.mowerbot.domain.model.Cardinal;
import com.seat.mowerbot.domain.model.Location;
import com.seat.mowerbot.domain.model.Mower;

public class MoveCommand implements MowerCommand {

    private final Mower mower;

    public MoveCommand(Mower mower) {
        this.mower = mower;
    }

    @Override
    public Mower execute() {
        Location newLocation = isMoveInAxisX() ? moveInAxisX() : moveInAxisY();
        validateNewLocation();
        return new Mower(mower.plateau(), newLocation);
    }

    private boolean isMoveInAxisX() {
        return Cardinal.EAST.equals(mower.location().direction()) || Cardinal.WEST.equals(mower.location().direction());
    }

    private Location moveInAxisX() {
        int step = Cardinal.EAST.equals(mower.location().direction()) ? 1 : -1;
        return new Location(mower.location().x() + step, mower.location().y(), mower.location().direction());
    }

    private Location moveInAxisY() {
        int step = Cardinal.NORTH.equals(mower.location().direction()) ? 1 : -1;
        return new Location(mower.location().x(), mower.location().y() + step, mower.location().direction());
    }

    private void validateNewLocation() {
        LocationValidation locationValidation = new LocationValidation(mower.plateau(), mower.location());
        if (locationValidation.isNotValid()) {
            String message = String.format("Error invalid %s", mower.location());
            throw new MowerCommandException(message);
        }
    }

}
