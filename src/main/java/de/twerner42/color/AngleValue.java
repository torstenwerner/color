package de.twerner42.color;

import java.util.Objects;

import static java.lang.String.format;

public class AngleValue implements Comparable<AngleValue> {
    private final Angle angle;
    private final long value;

    public AngleValue(Angle angle, long value) {
        this.angle = Objects.requireNonNull(angle);
        this.value = value;
    }

    public Angle getAngle() {
        return angle;
    }

    @Override
    public String toString() {
        return format("AngleValue{angle=%s, value=%d}", angle, value);
    }

    @Override
    public int compareTo(AngleValue other) {
        Objects.requireNonNull(other);
        return Long.compare(value, other.value);
    }
}
