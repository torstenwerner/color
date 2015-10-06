package de.twerner42.color;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class AngleSet {
    public static final int DEGREES = 360;
    public static final int ROOT = 2 * DEGREES;

    private final TreeSet<AngleValue> candidates = IntStream.range(0, DEGREES).mapToObj(AngleValue::new)
            .collect(toCollection(() -> new TreeSet<>(Comparator.comparingInt(AngleValue::getAngle))));

    private AngleSet() {
    }

    private AngleValue getNext() {
        final AngleValue nextAngle = candidates.stream()
                .max(Comparator.comparingLong(AngleValue::getValue))
                .get();
        candidates.remove(nextAngle);
        candidates.forEach(candidate -> candidate.updateValue(nextAngle));
        return nextAngle;
    }

    public static IntStream generate() {
        final AngleSet angleSet = new AngleSet();
        return Stream.generate(angleSet::getNext)
                .limit(DEGREES)
                .mapToInt(AngleValue::getAngle);
    }
}
