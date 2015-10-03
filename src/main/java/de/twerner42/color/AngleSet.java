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

    private final List<Angle> angles = new ArrayList<>();
    private final TreeSet<Angle> holes = IntStream.range(0, DEGREES).mapToObj(Angle::new)
            .collect(toCollection(TreeSet::new));

    private AngleSet() {
    }

    private long weightedDistanceTo(int index, Angle angle) {
        final int weight = DEGREES + index - angles.size();
        final int distance = Angle.distance(angles.get(index), angle);
        return weight * distance * (ROOT - distance);
    }

    private AngleValue sum(Angle angle) {
        final long sum = IntStream.range(0, angles.size())
                .mapToLong(i -> weightedDistanceTo(i, angle))
                .sum();
        return new AngleValue(angle, sum);
    }

    private Angle getNext() {
        final Angle nextAngle = holes.stream()
                .map(this::sum)
                .max(Comparator.<AngleValue>naturalOrder())
                .get().getAngle();
        angles.add(nextAngle);
        holes.remove(nextAngle);
        return nextAngle;
    }

    public static IntStream generate() {
        final AngleSet angleSet = new AngleSet();
        return Stream.generate(angleSet::getNext)
                .limit(DEGREES)
                .mapToInt(Angle::getValue);
    }
}
