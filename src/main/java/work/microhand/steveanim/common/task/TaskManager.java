package work.microhand.steveanim.common.task;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author SanseYooyea
 */
public enum TaskManager {
    INSTANCE;

    private final ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    private final Map<Runnable, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public void runTaskLater(Runnable runnable, long delay) {
        service.schedule(runnable, delay, TimeUnit.SECONDS);
    }

    public void runTaskLater(Runnable runnable, long delay, TimeUnit timeUnit) {
        tasks.put(runnable, service.schedule(runnable, delay, timeUnit));
    }

    public void scheduleTask(Runnable runnable, long delay, long period) {
        tasks.put(runnable, service.scheduleAtFixedRate(runnable, delay, period, TimeUnit.SECONDS));
    }

    public void cancel(Runnable entityFollowPlayerTask) {
        ScheduledFuture<?> scheduledFuture = tasks.get(entityFollowPlayerTask);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            tasks.remove(entityFollowPlayerTask);
        }
    }

    public void runTaskTimer(Runnable entityFollowPlayerTask, int delay, int period) {
        tasks.put(entityFollowPlayerTask, service.scheduleAtFixedRate(entityFollowPlayerTask, delay, period, TimeUnit.SECONDS));
    }
}
