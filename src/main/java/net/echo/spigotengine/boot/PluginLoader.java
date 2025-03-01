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

    private P plugin;

    @Override
    public void onLoad() {
        this.plugin = getPlugin();
        this.plugin.load();
    }

    @Override
    public void onEnable() {
        if (this.plugin == null) {
            this.plugin = getPlugin();
        }

        this.plugin.startup();
        this.plugin.enable();
    }
    
    @Override
    public void onDisable() {
        this.plugin.disable();
    }
}
