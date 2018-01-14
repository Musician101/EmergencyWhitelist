package io.musician101.emergencywhitelist.sponge;

import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import javax.annotation.Nonnull;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.network.ClientConnectionEvent.Login;
import org.spongepowered.api.text.Text;

public class EWLListener {

    @Listener
    public void onPlayerLogin(@Nonnull Login event, @First Player player) {
        SpongeEmergencyWhitelist.instance().ifPresent(plugin -> {
            if (plugin.getConfig().isWhitelistEnabled() && !player.hasPermission(Permissions.WHITELIST)) {
                event.setCancelled(true);
                event.setMessage(Text.of(Messages.WHITELIST_ENABLED));
                plugin.getLogger().info(Messages.playerConnectAttempt(player.getName()));
            }
        });
    }
}
