package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;


public class EWLCommandExecutor implements CommandExecutor
{
	EmergencyWhitelist plugin;
	public EWLCommandExecutor(EmergencyWhitelist plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		boolean enabled = plugin.getConfig().getBoolean("enabled");
		if (command.getName().equalsIgnoreCase(Constants.EWL))
		{
			if (args.length == 0)
			{
				sender.sendMessage(Constants.getVersionMessage(enabled, plugin.getDescription().getVersion()));
				sender.sendMessage(ChatColor.GOLD + "[EmergencyWhitelist] Type /ewl help for a list of commands.");
			}
			else if (args.length > 0)
			{
				if (args[0].equalsIgnoreCase(Constants.HELP))
				{
					new HelpCommand(plugin, sender);
				}
				else if (args[0].equalsIgnoreCase(Constants.TOGGLE))
				{
					new ToggleCommand(plugin, sender, enabled);
				}
			}
			return true;
		}
		return false;
	}
}
