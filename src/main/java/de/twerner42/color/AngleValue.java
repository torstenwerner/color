package de.twerner42.color;

import java.util.Objects;

import static java.lang.String.format;

public class AngleValue {
    private final Angle angle;
    private final long value;

    public AngleValue(Angle angle, long value) {
        this.angle = Objects.requireNonNull(angle);
        this.value = value;
    }

    public Angle getAngle() {
        return angle;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return format("AngleValue{angle=%s, value=%d}", angle, value);
    }
}
