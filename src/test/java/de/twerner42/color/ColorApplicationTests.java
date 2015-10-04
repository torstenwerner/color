package de.twerner42.color;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ColorApplicationTests {
    final Logger logger = LoggerFactory.getLogger(getClass());

    private void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        logger.info("Test {} {}, spent {} milliseconds",
                testName, status, TimeUnit.NANOSECONDS.toMillis(nanos));
    }

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void succeeded(long nanos, Description description) {
            logInfo(description, "success", nanos);
        }
    };

    @Test
    public void performance01() {
        IntStream.range(0, 100).forEach(ignored -> AngleSet.generate().toArray());
    }

    @Test
    public void performance02() {
        IntStream.range(0, 100).forEach(ignored -> AngleSet.generate().toArray());
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
