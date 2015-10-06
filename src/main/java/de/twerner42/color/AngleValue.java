package de.twerner42.color;

public interface AngleValue {
    int getAngle();

    long getValue();

    void updateValue(AngleValue otherAngle);
}
