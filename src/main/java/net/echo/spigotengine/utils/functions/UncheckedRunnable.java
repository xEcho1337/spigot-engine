package net.echo.spigotengine.utils.functions;

/**
 * A function interface used to uncheck exceptions thrown by a runnable.
 */
@FunctionalInterface
public interface UncheckedRunnable {

    void run() throws Exception;
}
