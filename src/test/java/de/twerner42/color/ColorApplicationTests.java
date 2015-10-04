package de.twerner42.color;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ColorApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ThreadMXBean bean = ManagementFactory.getThreadMXBean();

    private void runTask(int id) {
        final String taskName = format("task%03d", id);
        final long startNs = bean.getCurrentThreadUserTime();
        AngleSet.generate().toArray();
        final long stopNs = bean.getCurrentThreadUserTime();
        final long ms = TimeUnit.NANOSECONDS.toMillis(stopNs - startNs);
        logger.info("{}: {} ms", taskName, ms);
    }

    @Test
    public void performance() {
        IntStream.range(0, 100).forEach(this::runTask);
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
