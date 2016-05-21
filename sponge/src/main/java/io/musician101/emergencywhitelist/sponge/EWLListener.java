package io.musician101.emergencywhitelist.sponge;

import io.musician101.emergencywhitelist.common.AbstractListener;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent.Login;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Optional;

public class EWLListener implements AbstractListener<Login>
{
    public EWLListener()
    {
        super();
    }

    @Listener
    @Override
    public void onPlayerLogin(@Nonnull Login event)
    {
        Optional<Player> playerOptional = Sponge.getServer().getPlayer(event.getProfile().getUniqueId());
        if (playerOptional.isPresent())
        {
            Player player = playerOptional.get();
            if (SpongeEmergencyWhitelist.instance().getConfig().isWhitelistEnabled() && !player.hasPermission(Permissions.WHITELIST))
            {
                event.setCancelled(true);
                event.setMessage(Text.of(Messages.WHITELIST_ENABLED));
                SpongeEmergencyWhitelist.getLogger().info(Messages.playerConnectAttempt(player.getName()));
            }
        }
    }
}
