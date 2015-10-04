package de.twerner42.color;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ColorApplicationTests {
    public static final int THREADS = 2;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ThreadMXBean bean = ManagementFactory.getThreadMXBean();

    final ExecutorService workerPool = Executors.newFixedThreadPool(THREADS);

    private void runTask(int id) {
        final String taskName = format("task%03d", id);
        final long startNs = bean.getCurrentThreadUserTime();
        AngleSet.generate().toArray();
        final long stopNs = bean.getCurrentThreadUserTime();
        final long ms = TimeUnit.NANOSECONDS.toMillis(stopNs - startNs);
        logger.info("{}: {} ms", taskName, ms);
    }

    private Future<?> submitTask(int id) {
        return workerPool.submit(() -> runTask(id));
    }

    private void waitFor(Future<?> future) {
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void performance() {
        final List<Future<?>> futures = IntStream.range(0, 100 * THREADS)
                .mapToObj(this::submitTask)
                .collect(Collectors.toList());
        futures.forEach(this::waitFor);
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
