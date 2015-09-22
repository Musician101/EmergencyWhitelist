package musician101.emergencywhitelist.spigot.command;

import musician101.emergencywhitelist.common.AbstractCommand;
import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.lib.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSpigotCommand extends AbstractCommand
{
    protected final EmergencyWhitelist plugin;
    private final List<AbstractSpigotCommand> subCommands;
    private final String permission;

    protected AbstractSpigotCommand(EmergencyWhitelist plugin, String name, String desc, List<String> usage, int minArgs, String perm)
    {
        this(plugin, name, desc, usage, minArgs, perm, new ArrayList<>());
    }

    protected AbstractSpigotCommand(EmergencyWhitelist plugin, String name, String desc, List<String> usage, int minArgs, String perm, List<AbstractSpigotCommand> subCommands)
    {
        super(name, desc, parseUsage(usage), minArgs, false);
        this.plugin = plugin;
        this.permission = perm;
        this.subCommands = subCommands;
    }

    protected List<AbstractSpigotCommand> getSubCommands()
    {
        return subCommands;
    }

    @Override
    public String getCommandHelpInfo()
    {
        return getUsage() + ChatColor.RED + " " + getDescription();
    }

    private boolean canSenderUseCommand(CommandSender sender)
    {
        if (isPlayerOnly() && !(sender instanceof Player))
        {
            sender.sendMessage(Messages.PLAYER_CMD);
            return false;
        }

        if (sender instanceof Player && !sender.hasPermission(permission))
        {
            sender.sendMessage(Messages.NO_PERMISSION);
            return false;
        }

        return true;
    }

    protected String[] moveArguments(String[] args)
    {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, args);
        if (list.size() > 0)
            list.remove(0);

        return new String[0];
    }

    public boolean onCommand(CommandSender sender, String... args)
    {
        return canSenderUseCommand(sender) || args.length >= getMinArgs();
    }

    private static String parseUsage(List<String> usageList)
    {
        String usage = ChatColor.GRAY + usageList.get(0);
        if (usageList.size() > 1)
            usage = usage + " " + ChatColor.RESET + usageList.get(1);

        if (usageList.size() > 2)
            usage = usage + " " + ChatColor.DARK_RED + usageList.get(2);

        return usage;
    }
}
