package musician101.emergencywhitelist.spigot.command;

import java.util.Arrays;
import java.util.Collections;
import musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import musician101.common.java.minecraft.spigot.command.SpigotCommandArgument;
import musician101.common.java.minecraft.spigot.command.SpigotHelpCommand;
import musician101.emergencywhitelist.common.Reference;
import musician101.emergencywhitelist.common.Reference.Commands;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.command.CommandSender;

public class EWLCommand extends AbstractSpigotCommand
{
    private final SpigotEmergencyWhitelist plugin;

    public EWLCommand(SpigotEmergencyWhitelist plugin)
    {
        super(Reference.ID, Commands.EWL_DESC, Collections.singletonList(new SpigotCommandArgument(Commands.EWL_CMD)), 0, "", false, "", "", Arrays.asList(new ReloadCommand(plugin), new ToggleCommand(plugin)));
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase(Commands.HELP))
                return new SpigotHelpCommand(this).onCommand(sender, moveArguments(args));

            for (AbstractSpigotCommand command : getSubCommands())
                if (command.getName().equalsIgnoreCase(args[0]))
                    return command.onCommand(sender, args);
        }

        plugin.getCommands().forEach(command -> {
            if (command.getName().equalsIgnoreCase(args[0]))
                sender.sendMessage(new SpigotHelpCommand(command).getCommandHelpInfo());
        });

        return true;
    }
}
