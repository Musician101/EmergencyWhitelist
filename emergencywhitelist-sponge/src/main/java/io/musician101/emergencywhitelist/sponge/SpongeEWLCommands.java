package io.musician101.emergencywhitelist.sponge;

import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SpongeEWLCommands {

    private SpongeEWLCommands() {

    }

    public static CommandSpec ewl() {
        return CommandSpec.builder().description(Text.of(Commands.EWL_DESC)).child(reload(), Commands.RELOAD_NAME).child(toggle(), Commands.TOGGLE_NAME).build();
    }

    private static CommandSpec reload() {
        return CommandSpec.builder().description(Text.of(Commands.RELOAD_DESC)).permission(Permissions.RELOAD).executor((source, args) -> SpongeEmergencyWhitelist.instance().map(plugin -> {
            plugin.getConfig().reload();
            source.sendMessage(Text.builder(Messages.CONFIG_RELOADED).color(TextColors.GREEN).build());
            return CommandResult.success();
        }).orElseGet(() -> {
            source.sendMessage(Text.builder("A fatal error occurred. You're some how running this command when the plugin is loaded!").color(TextColors.RED).build());
            return CommandResult.empty();
        })).build();
    }

    private static CommandSpec toggle() {
        return CommandSpec.builder().description(Text.of(Commands.TOGGLE_DESC)).permission(Permissions.TOGGLE).executor((source, args) -> SpongeEmergencyWhitelist.instance().map(plugin -> {
            SpongeConfig config = plugin.getConfig();
            config.setWhitelistEnabled(!config.isWhitelistEnabled());
            config.save();
            config.reload();
            source.sendMessage(Text.builder(Messages.CONFIG_RELOADED).color(TextColors.GREEN).build());
            return CommandResult.success();
        }).orElseGet(() -> {
            source.sendMessage(Text.builder("A fatal error occurred. You're some how running this command when the plugin is loaded!").color(TextColors.RED).build());
            return CommandResult.empty();
        })).build();
    }
}
