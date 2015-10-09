package musician101.emergencywhitelist.forge.command.ewl;

import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.command.AbstractSpigotCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ToggleCommand extends AbstractSpigotCommand
{
    public ToggleCommand(EmergencyWhitelist plugin)
    {
        super(plugin, "toggle", "Toggle the whitelist.", Arrays.asList("/ewl", "reload"), 0, "ewl.reload");
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!super.onCommand(sender, args))
            return false;

        plugin.getConfig().set("enabled", !plugin.getPluginConfig().isWhitelistEnabled());
        plugin.saveConfig();
        plugin.getPluginConfig().reloadConfiguration();
        return true;
    }
}
