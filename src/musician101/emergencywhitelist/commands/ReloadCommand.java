package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;
import musician101.emergencywhitelist.util.RunKickMethod;

import org.bukkit.command.CommandSender;

/**
 * Runs the necessary code for the '/ewl reload' command.
 * 
 * @author Musician101
 */
public class ReloadCommand
{
	/**
	 * @param plugin References the plugin's main class.
	 * @param sender The one who sent the command.
	 */
	public ReloadCommand(EmergencyWhitelist plugin, CommandSender sender)
	{
		if (!sender.hasPermission(Constants.PERMISSION_RELOAD))
			sender.sendMessage(Constants.NO_PERMISSION);
		else
		{
			plugin.reloadConfig();
			boolean enabled = plugin.getConfig().getBoolean("enabled");
			new RunKickMethod(plugin, enabled);
		}
	}
}
