package net.echo.spigotengine.commands;

import net.echo.spigotengine.boot.SpigotPlugin;
import net.echo.spigotengine.commands.annotations.CommandData;
import net.echo.spigotengine.commands.annotations.Default;
import net.echo.spigotengine.commands.annotations.SubCommand;
import net.echo.spigotengine.commands.executor.BukkitCommand;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * The base class for all commands.
 */
public class BaseCommand<P extends SpigotPlugin<?>> {

    protected final P plugin;
    protected final CommandData commandData;

    // Faster look up
    private final Map<String, Method> subCommands = new HashMap<>();
    private Method defaultMethod;

    public BaseCommand(P plugin) {
        this.plugin = plugin;
        this.commandData = this.getClass().getAnnotation(CommandData.class);

        loadSubCommands();
    }

    private void loadSubCommands() {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Default.class)) {
                if (defaultMethod != null) {
                    throw new IllegalStateException("Multiple default methods are not allowed in " + this.getClass().getSimpleName());
                }

                defaultMethod = method;
                break;
            }

            if (!method.isAnnotationPresent(SubCommand.class)) continue;

            SubCommand data = method.getAnnotation(SubCommand.class);

            for (String name : data.aliases()) {
                subCommands.put(name.toLowerCase(), method);
            }
        }

        new BukkitCommand(this.commandData, plugin);
    }

    public Method getSubCommand(String name) {
        return subCommands.get(name.toLowerCase());
    }

    public Method getDefaultMethod() {
        return defaultMethod;
    }

    /**
     * Returns the plugin associated with this command
     */
    public P getPlugin() {
        return plugin;
    }

    /**
     * Returns the information about this command
     */
    public CommandData getCommandData() {
        return commandData;
    }
}
