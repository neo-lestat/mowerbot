package com.seat.mowerbot.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Cardinal {

    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private final String shortLetter;

    Cardinal(String shortLetter){
        this.shortLetter = shortLetter;
    }

    @JsonValue
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

    @JsonCreator
    public static Cardinal fromString(String value) {
        return getValueFromShortLetter(value.toUpperCase());
    }
}
