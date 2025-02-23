package net.echo.spigotengine.data;

import java.util.UUID;

/**
 * A simple class containing a uuid and the name of the player.
 * This class should be extended by your player data implementation.
 */
public class UserData {

    protected final UUID uuid;
    protected final String name;

    public UserData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
