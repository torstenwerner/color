package de.twerner42.color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AngleSet {
    public static final int DEGREES = 360;
    public static final int ROOT = 2 * DEGREES;

    //private final IntFunction<AngleValue> factory = AngleValueArithmetic::new;
    private final IntFunction<AngleValue> factory = AngleValueGeometric::new;

    private List<AngleValue> candidates = IntStream.range(0, DEGREES).mapToObj(factory)
            .collect(toList());

    private AngleSet() {
    }

    private AngleValue getNext() {
        final AngleValue nextAngle = candidates.stream()
                .max(Comparator.comparingLong(AngleValue::getValue))
                .get();
        reorderCandidates(nextAngle);
        candidates.forEach(candidate -> candidate.updateValue(nextAngle));
        return nextAngle;
    }

    private void reorderCandidates(AngleValue nextAngle) {
        final int indexOf = candidates.indexOf(nextAngle);
        final List<AngleValue> head = candidates.subList(0, indexOf);
        final List<AngleValue> tail = candidates.subList(indexOf + 1, candidates.size());
        candidates = new ArrayList<>(tail);
        candidates.addAll(head);
    }

    public static IntStream generate() {
        final AngleSet angleSet = new AngleSet();
        return Stream.generate(angleSet::getNext)
                .limit(DEGREES)
                .mapToInt(AngleValue::getAngle);
    }
}
