package net.echo.spigotengine.boot;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides the entry point for Bukkit plugins. Should not be confused with {@link SpigotPlugin}.
 */
public abstract class PluginLoader<P extends SpigotPlugin<?>> extends JavaPlugin {

    /**
     * Returns the plugin instance associated with this loader.
     *
     * @return the plugin instance
     */
    public abstract P getPlugin();

    @Override
    public void onLoad() {
        getPlugin().load();
    }

    @Override
    public void onEnable() {
        getPlugin().startup();
        getPlugin().enable();
    }
    
    @Override
    public void onDisable() {
        getPlugin().disable();
    }
}
