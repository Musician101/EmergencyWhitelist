package musician101.emergencywhitelist.spigot.command;

import java.util.Arrays;
import musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import musician101.common.java.minecraft.spigot.command.CommandArgument;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import musician101.emergencywhitelist.spigot.lib.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand extends AbstractSpigotCommand
{
    private final SpigotEmergencyWhitelist plugin;
    private final AbstractSpigotCommand mainCommand;

    public HelpCommand(SpigotEmergencyWhitelist plugin, AbstractSpigotCommand mainCommand)
    {
        super("help", "Display help info for " + ChatColor.stripColor(mainCommand.getUsage()), Arrays.asList(new CommandArgument("/" + ChatColor.stripColor(mainCommand.getUsage())), new CommandArgument("help")), 1, "ewl.help", false, Messages.NO_PERMISSION, Messages.PLAYER_CMD);
        this.plugin = plugin;
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
