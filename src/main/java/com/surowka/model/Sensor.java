package com.surowka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class Sensor {
    @JsonProperty
    private String id;
    @JsonProperty("master-sensor-id")
    private String master_sensor_id;
    @JsonProperty
    private String location;
    @JsonProperty
    private String engine;
    @JsonProperty
    private String type;
    @JsonProperty
    private String name;
    @JsonProperty
    private Integer value;
    @JsonProperty
    private Integer min_value;
    @JsonProperty
    private Integer max_value;

    public Sensor() {}

    private Sensor(SensorBuilder builder) {
        this.id = builder.id;
        this.master_sensor_id = builder.master_sensor_id;
        this.location = builder.location;
        this.engine = builder.engine;
        this.type = builder.type;
        this.name = builder.name;
        this.value = builder.value;
        this.min_value = builder.min_value;
        this.max_value = builder.max_value;
    }

    public static class SensorBuilder {
        private String id;
        private String master_sensor_id;
        private String location;
        private String engine;
        private String type;
        private String name;
        private Integer value;
        private Integer min_value;
        private Integer max_value;

        public SensorBuilder(String id) {
            this.id = id;
        }

        public SensorBuilder masterSensorId(String master_sensor_id) {
            this.master_sensor_id = master_sensor_id;
            return this;
        }

        public SensorBuilder type(String type) {
            this.type = type;
            return this;
        }

        public SensorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SensorBuilder location(String location) {
            this.location = location;
            return this;
        }

        public SensorBuilder engine(String engine) {
            this.engine = engine;
            return this;
        }

        public SensorBuilder value(Integer value) {
            this.value = value;
            return this;
        }

        public SensorBuilder min_value(Integer min_value) {
            this.min_value = min_value;
            return this;
        }

        public SensorBuilder max_value(Integer max_value) {
            this.max_value = max_value;
            return this;
        }

        public Sensor build() {
            return new Sensor(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaster_sensor_id() {
        return master_sensor_id;
    }

    public void setMaster_sensor_id(String master_sensor_id) {
        this.master_sensor_id = master_sensor_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getMin_value() {
        return min_value;
    }

    public void setMin_value(Integer min_value) {
        this.min_value = min_value;
    }

    public Integer getMax_value() {
        return max_value;
    }

    public void setMax_value(Integer max_value) {
        this.max_value = max_value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(getId(), sensor.getId()) &&
                Objects.equals(getMaster_sensor_id(), sensor.getMaster_sensor_id()) &&
                Objects.equals(getLocation(), sensor.getLocation()) &&
                Objects.equals(getEngine(), sensor.getEngine()) &&
                Objects.equals(getType(), sensor.getType()) &&
                Objects.equals(getName(), sensor.getName()) &&
                Objects.equals(getValue(), sensor.getValue()) &&
                Objects.equals(getMin_value(), sensor.getMin_value()) &&
                Objects.equals(getMax_value(), sensor.getMax_value());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getMaster_sensor_id(), getLocation(), getEngine(), getType(), getName(), getValue(), getMin_value(), getMax_value());
    }
}
