package io.musician101.emergencywhitelist.spigot;

import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class EWLListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (SpigotEmergencyWhitelist.instance().getPluginConfig().isWhitelistEnabled() && !player.hasPermission(Permissions.WHITELIST)) {
            event.disallow(Result.KICK_WHITELIST, Messages.WHITELIST_ENABLED);
            SpigotEmergencyWhitelist.instance().getLogger().info(Messages.playerConnectAttempt(player.getName()));
        }
    }
}
