package musician101.emergencywhitelist.sponge.listener;

import javax.annotation.Nonnull;
import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.common.Reference.Permissions;
import musician101.emergencywhitelist.sponge.SpongeEmergencyWhitelist;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.network.ClientConnectionEvent.Login;
import org.spongepowered.api.text.Texts;

public class EWLListener implements EventListener<Login>
{
    public EWLListener()
    {

    }

    @Override
    public void handle(@Nonnull Login event)
    {
        Player player = event.getGame().getServer().getPlayer(event.getProfile().getUniqueId()).get();
        if (SpongeEmergencyWhitelist.config.isWhitelistEnabled() && !player.hasPermission(Permissions.WHITELIST))
        {
            event.setCancelled(true);
            event.setMessage(Texts.of(Messages.WHITELIST_ENABLED));
            SpongeEmergencyWhitelist.logger.info(Messages.playerConnectAttempt(player.getName()));
        }
    }
}
