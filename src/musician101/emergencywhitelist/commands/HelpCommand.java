package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;

import org.bukkit.command.CommandSender;


public class HelpCommand
{
	
	public HelpCommand(EmergencyWhitelist plugin, CommandSender sender)
	{
		if (!sender.hasPermission(Constants.PERMISSION_HELP))
			sender.sendMessage(Constants.NO_PERMISSION);
		else
			sender.sendMessage(Constants.HELP_TEXT);
	}
}
