package io.musician101.emergencywhitelist.spigot;

import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Config;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommand;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandArgument;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandPermissions;
import io.musician101.musicianlibrary.java.minecraft.spigot.command.SpigotCommandUsage;
import org.bukkit.ChatColor;

public class SpigotEWLCommands {

    private SpigotEWLCommands() {

    }

    public static SpigotCommand<SpigotEmergencyWhitelist> ewl() {
        return SpigotCommand.<SpigotEmergencyWhitelist>builder().name(Commands.EWL_CMD.replace("/", "")).description(Commands.EWL_DESC).usage(SpigotCommandUsage.of(SpigotCommandArgument.of(Commands.EWL_CMD))).permissions(SpigotCommandPermissions.blank()).addCommand(reload()).addCommand(toggle()).build(SpigotEmergencyWhitelist.instance());
    }

    private static SpigotCommandPermissions permissions(String permissionNode) {
        return SpigotCommandPermissions.builder().playerOnlyMessage(ChatColor.RED + Messages.PLAYER_CMD).noPermissionMessage(ChatColor.RED + Messages.NO_PERMISSION).isPlayerOnly(false).permissionNode(permissionNode).build();
    }

    public static SpigotCommand<SpigotEmergencyWhitelist> reload() {
        return SpigotCommand.<SpigotEmergencyWhitelist>builder().name(Commands.RELOAD_NAME).description(Commands.RELOAD_DESC).usage(SpigotCommandUsage.of(SpigotCommandArgument.of(Commands.RELOAD_NAME))).permissions(permissions(Permissions.RELOAD)).function((sender, args) -> {
            SpigotEmergencyWhitelist.instance().getPluginConfig().reload();
            sender.sendMessage(ChatColor.GOLD + Messages.CONFIG_RELOADED);
            return true;
        }).build(SpigotEmergencyWhitelist.instance());
    }

    public static SpigotCommand<SpigotEmergencyWhitelist> toggle() {
        return SpigotCommand.<SpigotEmergencyWhitelist>builder().name(Commands.TOGGLE_NAME).description(Commands.TOGGLE_DESC).usage(SpigotCommandUsage.of(SpigotCommandArgument.of(Commands.TOGGLE_NAME))).permissions(permissions(Permissions.TOGGLE)).function((sender, args) -> {
            SpigotEmergencyWhitelist instance = SpigotEmergencyWhitelist.instance();
            instance.getConfig().set(Config.ENABLED, !instance.getPluginConfig().isWhitelistEnabled());
            instance.saveConfig();
            instance.getPluginConfig().reload();
            return true;
        }).build(SpigotEmergencyWhitelist.instance());
    }
}
