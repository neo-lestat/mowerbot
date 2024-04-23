package com.seat.mowerbot.domain.model;

public record Mower(Plateau plateau, Location location, Cardinal direction) {

    public static final class Builder {
        Mower mower;
        Plateau plateau;
        Location location;
        Cardinal direction;

        public Builder() {
        }

        public Builder plateau(Plateau plateau) {
            this.plateau = plateau;
            return this;
        }

        public Builder from(Mower mower) {
            this.mower = mower;
            return this;
        }

        public Mower withPlateau(Plateau plateau) {
            return new Mower(plateau, mower.location, mower.direction);
        }

        public Mower withLocation(Location location) {
            return new Mower(mower.plateau, location, mower.direction);
        }

        public Mower withDirection(Cardinal direction) {
            return new Mower(mower.plateau, mower.location, direction);
        }

        public Mower build() {
            return new Mower(plateau, location, direction);
        }
    }
}
