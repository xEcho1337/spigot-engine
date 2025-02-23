package net.echo.spigotengine.tasks;

import com.google.common.base.Preconditions;
import net.echo.spigotengine.boot.SpigotPlugin;
import net.echo.spigotengine.utils.Initializer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

/**
 * Class used for handling tasks.
 */
public class TaskHandler<P extends SpigotPlugin<?>> {

    private static final BukkitScheduler executor = Bukkit.getScheduler();
    private final P plugin;

    protected TaskHandler(P plugin) {
        this.plugin = plugin;
    }

    /**
     * Creates an instance of the task handler.
     *
     * @param plugin the parent plugin
     */
    public static <T extends SpigotPlugin<?>> TaskHandler<T> create(T plugin) {
        return new TaskHandler<>(plugin);
    }

    /**
     * Registers all the tasks in the specified path.
     */
    public void registerAll(String path) {
        Initializer.consumeAll(Runnable.class, plugin, path, this::register);
    }

    /**
     * Submits a task to run it later.
     */
    public <T extends Runnable> void submit(@NotNull T task, long delay) {
        Preconditions.checkNotNull(task);
        executor.runTaskLater(plugin.getLoader(), task, delay);
    }

    /**
     * Submits a task to run it later asynchronously.
     */
    public <T extends Runnable> void submitAsync(@NotNull T task, long delay) {
        Preconditions.checkNotNull(task);
        executor.runTaskLaterAsynchronously(plugin.getLoader(), task, delay);
    }

    /**
     * Registers a task to be executed.
     *
     * @param task the task
     */
    public <T extends Runnable> void register(@NotNull T task) {
        Preconditions.checkNotNull(task);

        if (!task.getClass().isAnnotationPresent(Task.class)) {
            throw new IllegalStateException(task.getClass().getSimpleName() + " does not have the @Task annotation");
        }

        Task data = task.getClass().getAnnotation(Task.class);

        if (data.async()) {
            if (data.repeating()) {
                executor.runTaskTimerAsynchronously(plugin.getLoader(), task, data.delay(), data.period());
            } else {
                executor.runTaskAsynchronously(plugin.getLoader(), task);
            }
        } else {
            if (data.repeating()) {
                executor.runTaskTimer(plugin.getLoader(), task, data.delay(), data.period());
            } else {
                executor.runTask(plugin.getLoader(), task);
            }
        }
    }
}
