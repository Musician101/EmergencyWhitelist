package io.musician101.emergencywhitelist.sponge;

import com.google.inject.Inject;
import io.musician101.emergencywhitelist.common.EWLConfig;
import io.musician101.emergencywhitelist.common.Reference;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import java.io.IOException;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.Command.Parameterized;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.api.event.lifecycle.StartingEngineEvent;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.jvm.Plugin;

@Plugin(Reference.ID)
public class SpongeEmergencyWhitelist {

    @NonNull
    private final EWLConfig config;
    private final PluginContainer pluginContainer;

    public static SpongeEmergencyWhitelist instance() {
        return Sponge.pluginManager().plugin(Reference.ID).map(PluginContainer::getInstance).filter(SpongeEmergencyWhitelist.class::isInstance).map(SpongeEmergencyWhitelist.class::cast).orElseThrow(() -> new IllegalStateException(Reference.NAME + " is not initialized."));
    }

    @Inject
    public SpongeEmergencyWhitelist(PluginContainer pluginContainer, @DefaultConfig(sharedRoot = true) ConfigurationReference<ConfigurationNode> configReference) {
        this.pluginContainer = pluginContainer;
        this.config = new SpongeConfig(configReference);
    }

    public @NonNull EWLConfig getConfig() {
        return config;
    }

    public Logger getLogger() {
        return pluginContainer.getLogger();
    }

    @Listener
    public void onPluginConstruct(ConstructPluginEvent event) {
        try {
            config.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Listener
    public void onServerStart(StartingEngineEvent<Server> event) {
        Sponge.eventManager().registerListeners(pluginContainer, new EWLListener());
    }

    @Listener
    public void onCommandRegister(RegisterCommandEvent<Parameterized> event) {
        event.register(pluginContainer, ewl(), Commands.EWL_CMD);
    }

    private Parameterized ewl() {
        return Command.builder().addChild(reload(), Commands.RELOAD_NAME).addChild(toggle(), Commands.TOGGLE_NAME).build();
    }

    private Parameterized reload() {
        return Command.builder().permission(Permissions.RELOAD).executor(context -> {
            try {
                config.load();
                context.sendMessage(Identity.nil(), Component.text(Messages.CONFIG_RELOADED).color(NamedTextColor.GREEN));
            }
            catch (IOException e) {
                context.sendMessage(Identity.nil(), Component.text(String.format(Messages.CONFIG_ERROR, e.getMessage())).color(NamedTextColor.RED));
            }

            return CommandResult.success();
        }).build();
    }

    private Parameterized toggle() {
        return Command.builder().permission(Permissions.TOGGLE).executor(context -> {
            config.setWhitelistEnabled(!config.isWhitelistEnabled());
            config.save();
            context.sendMessage(Identity.nil(), Component.text(Messages.CONFIG_RELOADED).color(NamedTextColor.GREEN));
            return CommandResult.success();
        }).build();
    }
}
