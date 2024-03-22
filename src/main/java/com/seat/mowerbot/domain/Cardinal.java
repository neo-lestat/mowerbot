package com.seat.mowerbot.domain;

public enum Cardinal {

    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    private final char shortLetter;

    Cardinal(char shortLetter) {
        this.shortLetter = shortLetter;
    }

    public char getShortLetter() {
        return shortLetter;
    }

}
