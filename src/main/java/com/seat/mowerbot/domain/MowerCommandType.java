package com.seat.mowerbot.domain;

public enum MowerCommandType {
    MOVE("M"),
    RIGHT("R"),
    LEFT("L"),
    UNKNOWN(" ");

    private final String shortLetter;

    MowerCommandType(String shortLetter){
        this.shortLetter = shortLetter;
    }

    public String getShortLetter() {
        return shortLetter;
    }

    public static MowerCommandType getValue(String shortLetter) {
        for(MowerCommandType mowerCommandType : values()){
            if (mowerCommandType.getShortLetter().equals(shortLetter.toUpperCase())){
                return mowerCommandType;
            }
        }
        return UNKNOWN;
    }
}
