package com.surowka.enums;

public enum SensorType {
    TEMPERATURE("temperature"),
    PRESSURE("pressure");

    private final String stringValue;
    SensorType(final String s) { stringValue = s; }
    public String toString() { return stringValue; }
}
