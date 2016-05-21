package io.musician101.emergencywhitelist.spigot.command;

import io.musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandArgument;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandPermissions;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandUsage;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Config;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import io.musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ToggleSpigotCommand extends AbstractSpigotCommand
{
    public ToggleSpigotCommand()
    {
        super(Commands.TOGGLE_NAME, Commands.TOGGLE_DESC, new SpigotCommandUsage(Arrays.asList(new SpigotCommandArgument(Commands.EWL_CMD), new SpigotCommandArgument(Commands.TOGGLE_NAME))), new SpigotCommandPermissions(Permissions.TOGGLE, false, Messages.NO_PERMISSION, Messages.PLAYER_CMD));
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!canSenderUseCommand(sender))
            return false;

        SpigotEmergencyWhitelist instance = SpigotEmergencyWhitelist.instance();
        instance.getConfig().set(Config.ENABLED, !instance.getPluginConfig().isWhitelistEnabled());
        instance.saveConfig();
        instance.getPluginConfig().setWhitelistEnabled(instance.getPluginConfig().isWhitelistEnabled());
        return true;
    }
}
