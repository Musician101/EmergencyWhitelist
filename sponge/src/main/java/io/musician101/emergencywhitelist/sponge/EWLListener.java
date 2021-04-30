package io.musician101.emergencywhitelist.sponge;

import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.network.ServerSideConnectionEvent.Login;

public class EWLListener {

    @Listener
    public void onPlayerLogin(Login event, @First ServerPlayer player) {
        SpongeEmergencyWhitelist plugin = SpongeEmergencyWhitelist.instance();
        if (plugin.getConfig().isWhitelistEnabled() && !player.hasPermission(Permissions.WHITELIST)) {
            event.setCancelled(true);
            event.setMessage(Component.text(Messages.WHITELIST_ENABLED));
            plugin.getLogger().info(Messages.playerConnectAttempt(player.name()));
        }
    }
}
