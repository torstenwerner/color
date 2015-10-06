package de.twerner42.color;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ColorApplicationTests {
    @Test
    public void performance() {
        final Performance performance = new Performance();
        performance.test();
    }

    @Test
    public void verify() {
        final int[] angles = AngleSet.generate().toArray();
        assertThat(angles.length, is(360));
        assertThat(angles[0], is(0));
        assertThat(angles[1], is(180));
        assertThat(angles[4], is(45));
        assertThat(angles[47], is(231));
        assertThat(angles[359], is(151));
    }
}
