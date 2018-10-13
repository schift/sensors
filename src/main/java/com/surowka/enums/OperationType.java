package com.surowka.enums;

public enum OperationType {
    INCREMENT("increment"),
    DECREMENT("decrement");

    private final String stringValue;
    OperationType(final String s) { stringValue = s; }
    public String toString() { return stringValue; }
}
