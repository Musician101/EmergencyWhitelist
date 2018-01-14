package io.musician101.emergencywhitelist.sponge;

import com.google.inject.Inject;
import io.musician101.emergencywhitelist.common.Reference;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.musicianlibrary.java.minecraft.sponge.plugin.AbstractSpongePlugin;
import java.io.File;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = Reference.ID, name = Reference.NAME, version = Reference.VERSION, description = Commands.EWL_DESC, authors = {"Musician101"})
public class SpongeEmergencyWhitelist extends AbstractSpongePlugin<SpongeConfig> {

    @Inject
    @DefaultConfig(sharedRoot = true)
    private File configDir;
    @Inject
    private PluginContainer pluginContainer;

    public static Optional<SpongeEmergencyWhitelist> instance() {
        return Sponge.getPluginManager().getPlugin(Reference.ID).flatMap(PluginContainer::getInstance).filter(SpongeEmergencyWhitelist.class::isInstance).map(SpongeEmergencyWhitelist.class::cast);
    }

    @Nonnull
    @Override
    public PluginContainer getPluginContainer() {
        return pluginContainer;
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        config = new SpongeConfig(configDir);
        Sponge.getEventManager().registerListeners(this, new EWLListener());
        Sponge.getCommandManager().register(this, SpongeEWLCommands.ewl(), Commands.EWL_CMD.replace("/", ""));
    }
}
