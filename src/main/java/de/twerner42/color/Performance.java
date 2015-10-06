package de.twerner42.color;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.format;

public class Performance {
    private final int threadCount;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ThreadMXBean bean = ManagementFactory.getThreadMXBean();

    final ExecutorService workerPool;

    public Performance(int threadCount) {
        this.threadCount = threadCount;
        this.workerPool = Executors.newFixedThreadPool(threadCount);
    }

    public Performance() {
        this(2);
    }

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

    public void test() {
        final List<Future<?>> futures = IntStream.range(0, 100 * threadCount)
                .mapToObj(this::submitTask)
                .collect(Collectors.toList());
        futures.forEach(this::waitFor);
    }
}
