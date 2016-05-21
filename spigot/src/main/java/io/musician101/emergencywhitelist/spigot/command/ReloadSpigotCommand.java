package io.musician101.emergencywhitelist.spigot.command;

import io.musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandArgument;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandPermissions;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandUsage;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import io.musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ReloadSpigotCommand extends AbstractSpigotCommand
{
    public ReloadSpigotCommand()
    {
        super(Commands.RELOAD_NAME, Commands.RELOAD_DESC, new SpigotCommandUsage(Arrays.asList(new SpigotCommandArgument(Commands.EWL_CMD), new SpigotCommandArgument(Commands.RELOAD_NAME))), new SpigotCommandPermissions(Permissions.RELOAD, false, ChatColor.RED + Messages.NO_PERMISSION, Messages.PLAYER_CMD));
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!canSenderUseCommand(sender))
            return false;

        SpigotEmergencyWhitelist.instance().getPluginConfig().reload();
        sender.sendMessage(ChatColor.GOLD + Messages.CONFIG_RELOADED);
        return true;
    }
}
