package com.surowka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class OperationPayload {
    @JsonProperty
    private String operation;
    @JsonProperty
    private Integer value;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationPayload that = (OperationPayload) o;
        return getOperation() == that.getOperation() &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOperation(), value);
    }
}
