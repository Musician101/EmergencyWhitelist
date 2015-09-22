package musician101.emergencywhitelist.spigot.command;

import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.lib.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class HelpCommand extends AbstractSpigotCommand
{
    private final AbstractSpigotCommand mainCommand;

    public HelpCommand(EmergencyWhitelist plugin, AbstractSpigotCommand mainCommand)
    {
        super(plugin, "help", "Display help info for " + ChatColor.stripColor(mainCommand.getUsage()), Arrays.asList("/" + ChatColor.stripColor(mainCommand.getUsage()), "help"), 1, "ewl.help");
        this.mainCommand = mainCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        sender.sendMessage(Messages.getEWLText(plugin.getPluginConfig().isWhitelistEnabled(), plugin.getDescription().getVersion()));
        sender.sendMessage(mainCommand.getUsage());
        mainCommand.getSubCommands().forEach(command -> sender.sendMessage(command.getCommandHelpInfo()));

        return true;
    }
}
