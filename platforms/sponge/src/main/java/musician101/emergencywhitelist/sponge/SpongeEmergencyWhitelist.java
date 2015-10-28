package musician101.emergencywhitelist.sponge;

import musician101.emergencywhitelist.common.CommonReference;
import musician101.emergencywhitelist.sponge.lib.Reference;
import musician101.emergencywhitelist.sponge.listener.EWLListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent.Login;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = CommonReference.ID, name = CommonReference.NAME, version = CommonReference.VERSION)
public class SpongeEmergencyWhitelist
{
    public static Game game;
    public static Logger logger;
    public static SpongeConfig config;
    public static String prefix;

    @Listener
    public void onServerStart(GameStartedServerEvent event)
    {
        game = event.getGame();
        logger = LoggerFactory.getLogger(Reference.NAME);
        prefix = "[EWL]";

        config = new SpongeConfig();

        game.getEventManager().registerListener(this, Login.class, new EWLListener());
    }
}
