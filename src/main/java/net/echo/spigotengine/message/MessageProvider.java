package net.echo.spigotengine.message;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides read/write access to messages globally
 */
public class MessageProvider {

    private final Map<String, String> messages = new HashMap<>();

    /**
     * Retrieves the message associated with the given ID. If no message is found for the ID, an empty string is returned.
     *
     * @param  id  the ID of the message to retrieve
     * @return     the message associated with the given ID, or an empty string if no message is found
     */
    public String getMessage(String id) {
        return messages.getOrDefault(id, "");
    }

    /**
     * Registers a message with the given ID and message content.
     *
     * @param  id       the ID of the message to register
     * @param  message  the content of the message to register
     */
    public void registerMessage(String id, String message) {
        messages.put(id, ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Sends a the message associated with the given ID to the given player.
     *
     * @param id      the ID of the message
     * @param player  the player to send the message to
     */
    public void sendMessage(String id, Player player) {
        player.sendMessage(getMessage(id));
    }
}
