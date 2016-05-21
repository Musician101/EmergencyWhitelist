package io.musician101.emergencywhitelist.sponge;

import io.musician101.common.java.minecraft.sponge.AbstractSpongePlugin;
import io.musician101.emergencywhitelist.common.Reference;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.sponge.command.EWLSpongeCommand;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent.Login;
import org.spongepowered.api.plugin.Plugin;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Plugin(id = Reference.ID, name = Reference.NAME, version = Reference.VERSION, description = Commands.EWL_DESC, authors = {"Musician101"})
public class SpongeEmergencyWhitelist extends AbstractSpongePlugin<SpongeConfig>
{
    @Listener
    @Override
    public void onServerStart(GameStartedServerEvent event)
    {
        config = new SpongeConfig();
        Sponge.getEventManager().registerListeners(this, new EWLListener());
        Sponge.getCommandManager().register(this, new EWLSpongeCommand(), "ewl");
    }

    public static SpongeEmergencyWhitelist instance()
    {
        return (SpongeEmergencyWhitelist) Sponge.getPluginManager().getPlugin(Reference.ID).get();
    }

    public static Logger getLogger()
    {
        return Sponge.getPluginManager().getPlugin(Reference.ID).get().getLogger();
    }
}
