package com.seat.mowerbot.domain.command;

public enum MowerCommandType {
    MOVE('M'),
    RIGHT('R'),
    LEFT('L');

    private final char shortLetter;

    MowerCommandType(char shortLetter) {
        this.shortLetter = shortLetter;
    }

    public char getShortLetter() {
        return shortLetter;
    }

}
