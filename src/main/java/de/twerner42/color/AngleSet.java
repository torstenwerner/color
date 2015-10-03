package de.twerner42.color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class AngleSet {
    private final List<Angle> angles = new ArrayList<>();
    private final TreeSet<Angle> holes = IntStream.range(0, 360).mapToObj(Angle::new)
            .collect(toCollection(TreeSet::new));

    public void add(Angle angle) {
        assert holes.contains(angle) : angle;

        angles.add(angle);
        holes.remove(angle);
    }

    public Stream<Angle> getHoles() {
        return holes.stream();
    }

    private static int distance(Angle angle01, Angle angle02) {
        final int rawDistance = Math.abs(angle01.getValue() - angle02.getValue());
        return rawDistance <= 180 ? rawDistance : 360 - rawDistance;
    }

    private static long squared(int value) {
        return value * value;
    }

    private long squaredWeightedDistanceTo(int index, Angle angle) {
        final int weight = 360 + index - angles.size();
        return weight * squared(distance(angles.get(index), angle));
    }

    public long sum(Angle angle) {
        Objects.requireNonNull(angle);
        return IntStream.range(0, angles.size())
                .mapToLong(i -> squaredWeightedDistanceTo(i, angle))
                .sum();
    }
}
