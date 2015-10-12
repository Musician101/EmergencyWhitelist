package musician101.emergencywhitelist.spigot.command.ewl;

import java.util.Arrays;
import musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import musician101.common.java.minecraft.spigot.command.CommandArgument;
import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.lib.Messages;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends AbstractSpigotCommand
{
    EmergencyWhitelist plugin;

    public ReloadCommand(EmergencyWhitelist plugin)
    {
        super("reload", "Reload the plugins configuration.", Arrays.asList(new CommandArgument("/ewl"), new CommandArgument("reload")), 0, "ewl.reload", false, Messages.NO_PERMISSION, Messages.PLAYER_CMD);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!canSenderUseCommand(sender))
            return false;

        plugin.getPluginConfig().reloadConfiguration();
        return true;
    }
}
