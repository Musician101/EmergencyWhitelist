package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;

import org.bukkit.command.CommandSender;

/**
 * Runs the necessary code for the '/ewl help' command.
 * 
 * @author Musician101
 */
public class HelpCommand
{
	/**
	 * @param plugin References the plugin's main class.
	 * @param sender The one who sent the command.
	 */
	public static boolean execute(EmergencyWhitelist plugin, CommandSender sender)
	{
		if (!sender.hasPermission(Constants.PERMISSION_HELP))
		{
			sender.sendMessage(Constants.NO_PERMISSION);
			return false;
		}
		
		sender.sendMessage(Constants.HELP_TEXT);
		return true;
	}
}
