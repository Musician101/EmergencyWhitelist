package musician101.emergencywhitelist.commands;

import musician101.emergencywhitelist.Config;
import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * The code used to run when any command in the plugin is executed.
 * 
 * @author Musician101
 */
public class EWLCommandExecutor implements CommandExecutor
{
	EmergencyWhitelist plugin;
	Config config;
	
	/**
	 * @param plugin References the plugin's main class.
	 */
	public EWLCommandExecutor(EmergencyWhitelist plugin, Config config)
	{
		this.plugin = plugin;
		this.config = config;
	}
	
	/**
	 * @param sender Who sent the command.
	 * @param command Which command was executed
	 * @param label Alias of the command
	 * @param args Command parameters
	 * @return True if the command was successfully executed
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (command.getName().equalsIgnoreCase(Constants.EWL))
		{
			if (args.length == 0)
				sender.sendMessage(Constants.getVersionMessage(config.enabled, plugin.getDescription().getVersion()));
			else if (args.length > 0)
			{
				if (args[0].equalsIgnoreCase(Constants.HELP))
					return HelpCommand.execute(plugin, sender);
				else if (args[0].equalsIgnoreCase(Constants.RELOAD))
					return ReloadCommand.execute(plugin, sender, config.enabled);
				else if (args[0].equalsIgnoreCase(Constants.TOGGLE))
					return ToggleCommand.execute(plugin, sender, config);
			}
			return true;
		}
		return false;
	}
}
