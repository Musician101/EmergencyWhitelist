package musician101.emergencywhitelist.forge.command.ewl;

import java.util.Arrays;
import musician101.common.java.minecraft.forge.AbstractForgeCommand;
import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import musician101.emergencywhitelist.forge.command.HelpCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class EWLCommand extends AbstractForgeCommand
{
    public EWLCommand()
    {
        super("ewl", "Secondary whitelisting for server admins.", "/ewl", 0, false, Arrays.asList(new ReloadCommand(), new ToggleCommand()));
    }

    @Override
    public void execute(ICommandSender sender, String... args)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase("help"))
            {
                new HelpCommand(sender, this).execute(sender, moveArguments(args));
                return;
            }

            for (AbstractForgeCommand command : getSubCommands())
            {
                if (command.getName().equalsIgnoreCase(args[0]))
                {
                    try
                    {
                        command.execute(sender, moveArguments(args));
                    }
                    catch (CommandException e)
                    {
                        sender.addChatMessage(new ChatComponentText(e.getMessage()));
                    }

                    return;
                }
            }
        }

        EmergencyWhitelist.commands.forEach(command -> {
            if (command.getName().equalsIgnoreCase(args[0]))
                sender.addChatMessage(new HelpCommand(sender, command).getCommandHelpInfo());
        });
    }
}
