package de.twerner42.color;

import static de.twerner42.color.AngleSet.DEGREES;
import static de.twerner42.color.AngleSet.ROOT;
import static java.lang.String.format;

public class AngleValueGeometric implements AngleValue {
    private final static double DAMPING = 0.9;

    private final int angle;
    private long value = 0;

    public AngleValueGeometric(int angle) {
        if (angle < 0 || angle >= DEGREES) {
            throw new AssertionError(value);
        }
        this.angle = angle;
    }

    static int distance(AngleValue angle01, AngleValue angle02) {
        final int rawDistance = Math.abs(angle01.getAngle() - angle02.getAngle());
        return rawDistance <= DEGREES / 2 ? rawDistance : DEGREES - rawDistance;
    }

    @Override
    public int getAngle() {
        return angle;
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public void updateValue(AngleValue otherAngle) {
        final int distance2other = distance(this, otherAngle);
        final int rawValue = distance2other * (ROOT - distance2other);
        value = (long) (DAMPING * value + rawValue);
    }

    @Override
    public String toString() {
        return format("AngleValue{angle=%d, value=%d}", angle, value);
    }
}
