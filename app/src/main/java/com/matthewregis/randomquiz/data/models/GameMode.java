package com.matthewregis.randomquiz.data.models;

/**
 * Created by reg on 28/11/2016.
 */

public enum GameMode {
    RANDOM_10("Random10", 0),
    SUDDEN_DEATH("SuddenDeath", 1);

    private String stringValue;
    private int intValue;
    GameMode(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
