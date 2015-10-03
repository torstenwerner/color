package de.twerner42.color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

public class AngleSet {
    private final List<Angle> angles = new ArrayList<>();
    private final TreeSet<Angle> holes = IntStream.range(0, 360).mapToObj(Angle::new)
            .collect(toCollection(TreeSet::new));

    private long weightedDistanceTo(int index, Angle angle) {
        final int weight = 360 + index - angles.size();
        final int distance = Angle.distance(angles.get(index), angle);
        return weight * distance * (720 - distance);
    }

    private AngleValue sum(Angle angle) {
        final long sum = IntStream.range(0, angles.size())
                .mapToLong(i -> weightedDistanceTo(i, angle))
                .sum();
        return new AngleValue(angle, sum);
    }

    public Angle getNext() {
        final Angle nextAngle = holes.stream()
                .map(this::sum)
                .max(Comparator.<AngleValue>naturalOrder())
                .get().getAngle();
        angles.add(nextAngle);
        holes.remove(nextAngle);
        return nextAngle;
    }
}
