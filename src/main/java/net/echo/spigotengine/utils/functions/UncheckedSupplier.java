package net.echo.spigotengine.utils.functions;

/**
 * A function interface used to uncheck exceptions.
 * @param <T> the return type
 */
@FunctionalInterface
public interface UncheckedSupplier<T> {

    T get() throws Exception;
}
