package net.echo.spigotengine.utils.functions;

/**
 * A function interface used to uncheck exceptions thrown by a supplier.
 * @param <T> the return type
 */
@FunctionalInterface
public interface UncheckedSupplier<T> {

    T get() throws Exception;
}
