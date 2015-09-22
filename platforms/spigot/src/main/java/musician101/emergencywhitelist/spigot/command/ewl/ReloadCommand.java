package musician101.emergencywhitelist.spigot.command.ewl;

import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.command.AbstractSpigotCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ReloadCommand extends AbstractSpigotCommand
{
    public ReloadCommand(EmergencyWhitelist plugin)
    {
        super(plugin, "reload", "Reload the plugins configuration.", Arrays.asList("/ewl", "reload"), 0, "ewl.reload");
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!super.onCommand(sender, args))
            return false;

        plugin.getPluginConfig().reloadConfiguration();
        return true;
    }
}
