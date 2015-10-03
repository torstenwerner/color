package de.twerner42.color;

import java.util.Objects;

import static de.twerner42.color.AngleSet.DEGREES;
import static java.lang.String.format;

public class Angle implements Comparable<Angle> {
    private final int value;

    public Angle(int value) {
        if (value < 0 || value >= DEGREES) {
            throw new AssertionError(value);
        }
        this.value = value;
    }

    static int distance(Angle angle01, Angle angle02) {
        final int rawDistance = Math.abs(angle01.getValue() - angle02.getValue());
        return rawDistance <= DEGREES / 2 ? rawDistance : DEGREES - rawDistance;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        final Angle otherAngle = (Angle) other;
        return value == otherAngle.value;

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return format("Angle{value=%d}", value);
    }

    @Override
    public int compareTo(Angle other) {
        Objects.requireNonNull(other);
        return Integer.compare(value, other.value);
    }
}
