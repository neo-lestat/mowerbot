package com.seat.mowerbot.domain;

public enum MowerCommandType {
    MOVE('M'),
    RIGHT('R'),
    LEFT('L'),
    UNKNOWN('X');

    private final char shortLetter;

    MowerCommandType(char shortLetter) {
        this.shortLetter = shortLetter;
    }

    public char getShortLetter() {
        return shortLetter;
    }

}
