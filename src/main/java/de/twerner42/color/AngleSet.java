package de.twerner42.color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class AngleSet {
    public static final int DEGREES = 360;
    public static final int ROOT = 2 * DEGREES;

    private final List<AngleValue> angles = new ArrayList<>(DEGREES);
    private final TreeSet<AngleValue> holes = IntStream.range(0, DEGREES).mapToObj(AngleValue::new)
            .collect(toCollection(() -> new TreeSet<>(Comparator.comparingInt(AngleValue::getAngle))));

    private AngleSet() {
    }

    private long weightedDistanceTo(int index, AngleValue angle) {
        final int weight = DEGREES + index - angles.size();
        final int distance = AngleValue.distance(angles.get(index), angle);
        return weight * distance * (ROOT - distance);
    }

    private void sum(AngleValue angle) {
        final long sum = IntStream.range(0, angles.size())
                .mapToLong(i -> weightedDistanceTo(i, angle))
                .sum();
        angle.setValue(sum);
    }

    private AngleValue getNext() {
        final AngleValue nextAngle = holes.stream()
                .peek(this::sum)
                .max(Comparator.comparingLong(AngleValue::getValue))
                .get();
        angles.add(nextAngle);
        holes.remove(nextAngle);
        return nextAngle;
    }

    public static IntStream generate() {
        final AngleSet angleSet = new AngleSet();
        return Stream.generate(angleSet::getNext)
                .limit(DEGREES)
                .mapToInt(AngleValue::getAngle);
    }
}
