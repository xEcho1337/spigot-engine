package net.echo.spigotengine.boot;

import net.echo.spigotengine.commands.CommandHandler;
import net.echo.spigotengine.data.UserData;
import net.echo.spigotengine.data.container.DataContainer;
import net.echo.spigotengine.data.listeners.AccessListener;
import net.echo.spigotengine.data.loader.DataLoader;
import net.echo.spigotengine.listener.ListenerHandler;
import net.echo.spigotengine.tasks.TaskHandler;
import org.bukkit.Bukkit;

/**
 * The main plugin class.
 * <p>
 * This class serves as the entry point for the plugin, providing methods for
 * enabling, disabling, and loading the plugin.
 */
public abstract class SpigotPlugin<D extends UserData> {

    protected final PluginLoader<?> pluginLoader;
    protected DataContainer<D> container;

    protected final CommandHandler<?> commandHandler = CommandHandler.create(this);
    protected final ListenerHandler<?> listenerHandler = ListenerHandler.create(this);
    protected final TaskHandler<?> taskHandler = TaskHandler.create(this);

    public SpigotPlugin(PluginLoader<?> pluginLoader) {
        this.pluginLoader = pluginLoader;
    }

    /**
     * Called when the plugin is enabled.
     * <p>
     * This method is typically used to initialize the plugin's functionality.
     */
    public void enable() {
    }

    /**
     * Called when the plugin is disabled.
     * <p>
     * This method is typically used to clean up or shutdown any resources used by the plugin.
     */
    public void disable() {
    }

    /**
     * Called once the plugin gets loaded.
     * <p>
     * This method is the first method called, even before startup()
     */
    public void load() {
    }

    /**
     * Called when the plugin gets enabled but before enable().
     * <p>
     * This method is used by SE to initialize, if you want to override it, insert a super call.
     */
    public void startup() {
        this.container = new DataContainer<>(getDataLoader());

        Bukkit.getPluginManager().registerEvents(new AccessListener(this), pluginLoader);

        registerListeners();
        registerCommands();
        registerTasks();
    }

    /**
     * Returns the loader associated with this plugin.
     */
    public PluginLoader<?> getLoader() {
        return pluginLoader;
    }

    /**
     * Returns an implementation of the {@link DataLoader} interface
     */
    public abstract DataLoader<D> getDataLoader();

    /**
     * Registers all the listeners of the plugin.
     */
    public abstract void registerListeners();

    /**
     * Registers all the commands of the plugin.
     */
    public abstract void registerCommands();

    /**
     * Registers all the tasks of the plugin.
     */
    public abstract void registerTasks();

    /**
     * Returns the {@link DataContainer} for the plugin
     */
    public DataContainer<D> getContainer() {
        return container;
    }

    /**
     * Returns the default command handler for this plugin.
     */
    public CommandHandler<?> getCommandHandler() {
        return commandHandler;
    }

    /**
     * Returns the default listener handler for this plugin.
     */

    public ListenerHandler<?> getListenerHandler() {
        return listenerHandler;
    }

    /**
     * Returns the default task handler for this plugin.
     */
    public TaskHandler<?> getTaskHandler() {
        return taskHandler;
    }
}
