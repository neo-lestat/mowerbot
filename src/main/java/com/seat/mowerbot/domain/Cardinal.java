package com.seat.mowerbot.domain;

public enum Cardinal {

    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private final String shortLetter;

    Cardinal(String shortLetter){
        this.shortLetter = shortLetter;
    }

    public String getShortLetter() {
        return shortLetter;
    }

    public static Cardinal getValueFromShortLetter(String shortLetter) {
        for(Cardinal cardinal : values()){
            if( cardinal.getShortLetter().equals(shortLetter.toUpperCase())){
                return cardinal;
            }
        }
        return null;
    }

}
