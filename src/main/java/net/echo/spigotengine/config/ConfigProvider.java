package net.echo.spigotengine.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides a simple implementation for a configuration
 * @param <T> the type of nameable
 */
public class ConfigProvider<T extends Enum<T> & Nameable> {

    private final Map<T, FileConfiguration> configurationMap = new HashMap<>();
    private final JavaPlugin plugin;

    public ConfigProvider(JavaPlugin plugin, Class<T> enumType) {
        this.plugin = plugin;

        for (T value : enumType.getEnumConstants()) {
            configurationMap.put(value, load(value));
        }
    }

    /**
     * Gets the cached file configuration associated with the given nameable
     * @param nameable the nameable
     * @return a {@link FileConfiguration} for the given nameable
     */
    public FileConfiguration find(T nameable) {
        return configurationMap.get(nameable);
    }

    /**
     * Loads the config file for the given nameable
     * @param nameable the nameable
     * @return a {@link FileConfiguration} for the given nameable
     */
    public FileConfiguration load(T nameable) {
        File file = new File(plugin.getDataFolder(), nameable.getName() + ".yml");

        if (!file.exists()) {
            plugin.saveResource(nameable.getName() + ".yml", false);
        }

        return YamlConfiguration.loadConfiguration(file);
    }
}
