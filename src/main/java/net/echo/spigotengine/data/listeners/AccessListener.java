package net.echo.spigotengine.data.listeners;

import net.echo.spigotengine.boot.SpigotPlugin;
import net.echo.spigotengine.data.LoadResult;
import net.echo.spigotengine.data.UserData;
import net.echo.spigotengine.listener.impl.BetterListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AccessListener extends BetterListener<SpigotPlugin<?>> {

    public AccessListener(SpigotPlugin<?> plugin) {
        super(plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() != AsyncPlayerPreLoginEvent.Result.ALLOWED) return;

        LoadResult result = plugin.getContainer().load(event.getUniqueId(), event.getName());

        if (result == LoadResult.FAIL) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Failed to load data");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreLogin(PlayerLoginEvent event) {
        UserData data = plugin.getContainer().get(event.getPlayer());

        if (data == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Data not found during login event.");
        } else if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
            plugin.getContainer().unload(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getContainer().unload(event.getPlayer());
    }
}
