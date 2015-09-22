package musician101.emergencywhitelist.spigot.command.ewl;

import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.command.AbstractSpigotCommand;
import musician101.emergencywhitelist.spigot.command.HelpCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;

public class EWLCommand extends AbstractSpigotCommand
{

	public EWLCommand(EmergencyWhitelist plugin)
	{
		super(plugin, "ewl", "Secondary whitelisting for server admins.", Collections.singletonList("/ewl"), 0, "ewl", Arrays.asList(new ReloadCommand(plugin), new ToggleCommand(plugin)));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, String... args)
	{
		if (args.length > 0)
		{
			if (args[0].equalsIgnoreCase("help"))
                return new HelpCommand(plugin, this).onCommand(sender, moveArguments(args));

            for (AbstractSpigotCommand command : getSubCommands())
                if (command.getName().equalsIgnoreCase(args[0]))
                    return command.onCommand(sender, args);
		}

        plugin.getCommands().forEach(command ->
		{
			if (command.getName().equalsIgnoreCase(args[0]))
                sender.sendMessage(new HelpCommand(plugin, command).getCommandHelpInfo());
		});

		return true;
	}
}
