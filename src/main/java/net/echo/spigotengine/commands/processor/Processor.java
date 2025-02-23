package net.echo.spigotengine.commands.processor;

import org.bukkit.command.CommandSender;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Processes an input string to the expected {@code T} output.
 */
public abstract class Processor<T> {

    private Class<?> type;

    public Processor() {
        try {
            Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            this.type = Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
    }

    public abstract T process(CommandSender sender, String supplied);

    public Class<?> getType() {
        return type;
    }
}
