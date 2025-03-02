package net.echo.spigotengine.listener;

import net.echo.spigotengine.boot.SpigotPlugin;
import net.echo.spigotengine.listener.impl.BetterListener;
import net.echo.spigotengine.utils.Initializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for handling listeners.
 */
public class ListenerHandler<P extends SpigotPlugin<?>> {

    private final P plugin;
    private final List<BetterListener<?>> listeners = new ArrayList<>();

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
        Initializer.consumeAll(BetterListener.class, plugin, path, listener -> {
            listeners.add(listener);
            listener.load();
        });
    }

    /**
     * Gets a list of all the registered listeners
     * @return the listeners
     */
    public List<BetterListener<?>> getListeners() {
        return listeners;
    }
}
