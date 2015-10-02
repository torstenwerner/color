package de.twerner42.color;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

public class AngleSet {
    private final List<Integer> angles = new ArrayList<>();
    private final TreeSet<Integer> holes = IntStream.range(0, 360).boxed()
            .collect(toCollection(TreeSet::new));

    public void add(int angle) {
        assert holes.contains(angle) : angle;

        angles.add(angle);
        holes.remove(angle);
    }

    public Stream<Integer> getHoles() {
        return holes.stream();
    }

    private static int distance(int angle01, int angle02) {
        final int rawDistance = Math.abs(angle01 - angle02);
        return rawDistance <= 180 ? rawDistance : 360 - rawDistance;
    }

    private static long squared(int value) {
        return value * value;
    }

    private long squaredWeightedDistanceTo(int index, int angle) {
        final int weight = 360 + index - angles.size();
        return weight * squared(distance(angles.get(index), angle));
    }

    public long sum(int angle) {
        assert angle >= 0 && angle < 360;

        return IntStream.range(0, angles.size())
                .mapToLong(i -> squaredWeightedDistanceTo(i, angle))
                .sum();
    }
}
