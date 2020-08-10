package com.seat.mowerbot.application.service;

import com.seat.mowerbot.domain.Location;
import com.seat.mowerbot.domain.Plateau;

public class LocationValidation {

    private final Plateau plateau;
    private final Location location;

    public LocationValidation(Plateau plateau, Location location) {
        this.plateau = plateau;
        this.location = location;
    }

    public boolean isValid() {
        return isInsideRange(location.getX(), plateau.getWidth())
                && isInsideRange(location.getY(), plateau.getHeight());
    }

    public boolean isNotValid() {
        return !isValid();
    }

    private boolean isInsideRange(int position, int range) {
        return position >= 0 && position <= range;
    }

}
