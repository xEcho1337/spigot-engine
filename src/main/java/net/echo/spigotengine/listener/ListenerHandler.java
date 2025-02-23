package net.echo.spigotengine.listener;

import net.echo.spigotengine.boot.SpigotPlugin;
import net.echo.spigotengine.listener.impl.BetterListener;
import net.echo.spigotengine.utils.Initializer;

/**
 * Class used for handling listeners.
 */
public class ListenerHandler<P extends SpigotPlugin<?>> {

    private final P plugin;

    protected ListenerHandler(P plugin) {
        this.plugin = plugin;
    }

    /**
     * Creates an instance of the listener handler.
     *
     * @param plugin the parent plugin
     */
    public static <T extends SpigotPlugin<?>> ListenerHandler<T> create(T plugin) {
        return new ListenerHandler<>(plugin);
    }

    /**
     * Registers all the listeners in the specified path.
     */
    public void registerAll(String path) {
        Initializer.consumeAll(BetterListener.class, plugin, path, BetterListener::load);
    }
}
