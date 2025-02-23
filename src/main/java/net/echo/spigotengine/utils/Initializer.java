package net.echo.spigotengine.utils;

import com.google.common.reflect.ClassPath;
import net.echo.spigotengine.boot.SpigotPlugin;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.function.Consumer;

/**
 * Simple class to consume all the classes in a package.
 * Useful to register all commands or all listeners all at once.
 */
public class Initializer {

    public static <T, P extends SpigotPlugin<?>> void consumeAll(Class<T> type, P plugin, String path, Consumer<T> consumer) {
        try {
            ClassPath.from(plugin.getClass().getClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(info -> info.getPackageName().startsWith(path))
                    .forEach(info -> consumeClassInfo(type, plugin, info, consumer));
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    @SuppressWarnings("all")
    private static <T, P extends SpigotPlugin<?>> void consumeClassInfo(Class<T> type, P plugin, ClassPath.ClassInfo info, Consumer<T> consumer) {
        try {
            Class<?> clazz = info.load();

            if (!type.isAssignableFrom(clazz)) return;

            try {
                // Try to find the no-arg constructor
                Constructor<?> noArgConstructor = clazz.getDeclaredConstructor();
                noArgConstructor.setAccessible(true);

                T instance = (T) noArgConstructor.newInstance();
                consumer.accept(instance);
            } catch (NoSuchMethodException ignored) {
                // OK, this class has a constructor, it's surely an instance
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor(plugin.getClass());
                    constructor.setAccessible(true);

                    T instance = (T) constructor.newInstance(plugin);
                    consumer.accept(instance);
                } catch (Exception ignored1) {
                }
            }
        } catch (Exception ignored) {
        }
    }
}
