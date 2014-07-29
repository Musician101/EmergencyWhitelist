package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.Config;
import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Commands;
import musician101.emergencywhitelist.util.RunKickMethod;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EWLCommandExecutor implements CommandExecutor
{
	EmergencyWhitelist plugin;
	Config config;
	
	public EWLCommandExecutor(EmergencyWhitelist plugin, Config config)
	{
		this.plugin = plugin;
		this.config = config;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args.length > 0)
		{
			if (args[0].equalsIgnoreCase(Commands.HELP_CMD))
			{
				if (!sender.hasPermission(Commands.HELP_PERM))
				{
					sender.sendMessage(Commands.NO_PERMISSION);
					return false;
				}
				
				sender.sendMessage(Commands.HELP_TEXT);
				return true;
			}
			else if (args[0].equalsIgnoreCase(Commands.RELOAD_CMD))
			{
				if (!sender.hasPermission(Commands.RELOAD_PERM))
				{
					sender.sendMessage(Commands.NO_PERMISSION);
					return false;
				}
				
				plugin.reloadConfig();
				new RunKickMethod(plugin, config.enabled);
				return true;
			}
			else if (args[0].equalsIgnoreCase(Commands.TOGGLE_CMD))
			{
				if (!sender.hasPermission(Commands.TOGGLE_PERM))
				{
					sender.sendMessage(Commands.NO_PERMISSION);
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
		
		sender.sendMessage(Commands.getEWLText(config.enabled, plugin.getDescription().getVersion()));
		return true;
	}
}
