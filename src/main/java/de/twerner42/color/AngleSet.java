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

    private long weightedDistanceTo(int index, Angle angle) {
        final int weight = 360 + index - angles.size();
        final int distance = Angle.distance(angles.get(index), angle);
        return weight * distance * (720 - distance);
    }

    public AngleValue sum(Angle angle) {
        Objects.requireNonNull(angle);
        final long sum = IntStream.range(0, angles.size())
                .mapToLong(i -> weightedDistanceTo(i, angle))
                .sum();
        return new AngleValue(angle, sum);
    }
}
