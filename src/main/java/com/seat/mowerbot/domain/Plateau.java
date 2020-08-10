package com.seat.mowerbot.domain;

public class Plateau {

    private final int width;
    private final int height;

    public Plateau(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
