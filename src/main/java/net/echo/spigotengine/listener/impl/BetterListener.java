package net.echo.spigotengine.listener.impl;

import net.echo.spigotengine.boot.SpigotPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

/**
 * A shortcut to easily implements listeners.
 */
public class BetterListener<P extends SpigotPlugin<?>> implements Listener {

    protected P plugin;

    public BetterListener(P plugin) {
        this.plugin = plugin;
    }

    /**
     * This method is not called usually, should instead be used to notify the listener about config or other changes.
     */
    public void reload() {
    }

    /**
     * Called once when the listener gets registered, with the pure intention to register all the events in the class.
     */
    public void load() {
        Bukkit.getPluginManager().registerEvents(this, plugin.getLoader());
    }
}

