package musician101.emergencywhitelist.spigot.command;

import java.util.Arrays;
import musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import musician101.common.java.minecraft.spigot.command.SpigotCommandArgument;
import musician101.emergencywhitelist.common.Reference.Commands;
import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.common.Reference.Permissions;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends AbstractSpigotCommand
{
    private final SpigotEmergencyWhitelist plugin;

    public ReloadCommand(SpigotEmergencyWhitelist plugin)
    {
        super(Commands.RELOAD_NAME, Commands.RELOAD_DESC, Arrays.asList(new SpigotCommandArgument(Commands.EWL_CMD), new SpigotCommandArgument(Commands.RELOAD_NAME)), 0, Permissions.RELOAD, false, ChatColor.RED + Messages.NO_PERMISSION, Messages.PLAYER_CMD);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!canSenderUseCommand(sender))
            return false;

        plugin.getPluginConfig().reload();
        sender.sendMessage(ChatColor.GOLD + Messages.CONFIG_RELOADED);
        return true;
    }
}
