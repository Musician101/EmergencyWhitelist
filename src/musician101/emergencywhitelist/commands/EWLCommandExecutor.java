package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.Config;
import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Commands;

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
		if (command.getName().equalsIgnoreCase(Commands.EWL_CMD))
		{
			if (args.length == 0)
				sender.sendMessage(Commands.getEWLText(config.enabled, plugin.getDescription().getVersion()));
			else if (args.length > 0)
			{
				if (args[0].equalsIgnoreCase(Commands.HELP_CMD))
					return HelpCommand.execute(plugin, sender);
				else if (args[0].equalsIgnoreCase(Commands.RELOAD_CMD))
					return ReloadCommand.execute(plugin, sender, config.enabled);
				else if (args[0].equalsIgnoreCase(Commands.TOGGLE_CMD))
					return ToggleCommand.execute(plugin, sender, config);
			}
			return true;
		}
		return false;
	}
}
