package net.echo.spigotengine.data.loader;

import net.echo.spigotengine.data.UserData;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface for loading player data.
 */
public interface DataLoader<D extends UserData> {

    /**
     * Loads the player data for the given uuid and name.
     *
     * @param uuid The player's uuid
     * @param name The player's name
     *
     * @return The player data
     */
    Optional<D> load(UUID uuid, String name);
}
