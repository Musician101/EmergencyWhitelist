package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.Config;
import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;
import musician101.emergencywhitelist.util.RunKickMethod;

import org.bukkit.command.CommandSender;

/**
 * Runs the necessary code for the '/ewl toggle' command.
 * 
 * @author Musician101
 */
public class ToggleCommand 
{
	/**
	 * @param plugin References the plugin's main class.
	 * @param sender The one who sent the command.
	 * @param enabled Represents the 'enabled' config option.
	 */
	public static boolean execute(EmergencyWhitelist plugin, CommandSender sender, Config config)
	{
		if (!sender.hasPermission(Constants.PERMISSION_TOGGLE))
		{
			sender.sendMessage(Constants.NO_PERMISSION);
			return false;
		}
		
		config.enabled = !config.enabled;
		new RunKickMethod(plugin, config.enabled);
		plugin.getConfig().set("enabled", config.enabled);
		plugin.saveConfig();
		config.reloadConfiguration();
		return true;
	}
}
