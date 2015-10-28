package musician101.emergencywhitelist.spigot.command.ewl;

import java.util.Arrays;
import java.util.Collections;
import musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import musician101.common.java.minecraft.spigot.command.CommandArgument;
import musician101.emergencywhitelist.common.CommonReference;
import musician101.emergencywhitelist.common.CommonReference.CommonCommands;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import musician101.emergencywhitelist.spigot.command.HelpCommand;
import musician101.emergencywhitelist.spigot.lib.Messages;
import org.bukkit.command.CommandSender;

public class EWLCommand extends AbstractSpigotCommand
{
    private final SpigotEmergencyWhitelist plugin;

    public EWLCommand(SpigotEmergencyWhitelist plugin)
    {
        super(CommonReference.ID, CommonCommands.EWL_DESC, Collections.singletonList(new CommandArgument("/ewl")), 0, "ewl", false, Messages.NO_PERMISSION, Messages.PLAYER_CMD, Arrays.asList(new ReloadCommand(plugin), new ToggleCommand(plugin)));
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase("help"))
                return new SpigotHelpCommand(plugin, this).onCommand(sender, moveArguments(args));

            for (AbstractSpigotCommand command : getSubCommands())
                if (command.getName().equalsIgnoreCase(args[0]))
                    return command.onCommand(sender, args);
        }

        plugin.getCommands().forEach(command -> {
            if (command.getName().equalsIgnoreCase(args[0]))
                sender.sendMessage(new HelpCommand(plugin, command).getCommandHelpInfo());
        });

        return true;
    }
}
