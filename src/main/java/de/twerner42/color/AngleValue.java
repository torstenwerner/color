package de.twerner42.color;

import static de.twerner42.color.AngleSet.DEGREES;
import static java.lang.String.format;

public class AngleValue {
    private final int angle;
    private long value = 0;

    public AngleValue(int angle) {
        if (angle < 0 || angle >= DEGREES) {
            throw new AssertionError(value);
        }
        this.angle = angle;
    }

    static int distance(AngleValue angle01, AngleValue angle02) {
        final int rawDistance = Math.abs(angle01.getAngle() - angle02.getAngle());
        return rawDistance <= DEGREES / 2 ? rawDistance : DEGREES - rawDistance;
    }

    public int getAngle() {
        return angle;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return format("AngleValue{angle=%d, value=%d}", angle, value);
    }
}
