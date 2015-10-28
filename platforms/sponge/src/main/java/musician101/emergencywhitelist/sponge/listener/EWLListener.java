package musician101.emergencywhitelist.sponge.listener;

import javax.annotation.Nonnull;
import musician101.emergencywhitelist.sponge.SpongeEmergencyWhitelist;
import org.spongepowered.api.GameProfile;
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
        GameProfile gp = event.getProfile();
        if (SpongeEmergencyWhitelist.config.isWhitelistEnabled() && !SpongeEmergencyWhitelist.config.isWhitelisted(gp))
        {
            event.setCancelled(true);
            event.setMessage(Texts.of("EmergencyWhitelist is currently enabled on this server."));
            SpongeEmergencyWhitelist.logger.info(gp.getName() + " attempted to connect.");
            return;
        }

        SpongeEmergencyWhitelist.config.updatePlayer(gp);
    }
}
