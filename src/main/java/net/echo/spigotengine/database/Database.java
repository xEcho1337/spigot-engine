package net.echo.spigotengine.database;

import net.echo.spigotengine.utils.functions.UncheckedSupplier;

public interface Database {

    default <T> T unchecked(UncheckedSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
