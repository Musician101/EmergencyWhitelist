package musician101.emergencywhitelist.forge.command;

import java.util.Arrays;
import java.util.Collections;
import musician101.common.java.minecraft.forge.command.AbstractForgeCommand;
import musician101.common.java.minecraft.forge.command.ForgeCommandArgument;
import musician101.common.java.minecraft.forge.command.ForgeHelpCommand;
import musician101.emergencywhitelist.common.Reference;
import musician101.emergencywhitelist.common.Reference.Commands;
import musician101.emergencywhitelist.forge.ForgeEmergencyWhitelist;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class EWLCommand extends AbstractForgeCommand
{
    public EWLCommand()
    {
        super(Reference.ID, Commands.EWL_DESC, Collections.singletonList(new ForgeCommandArgument(Commands.EWL_CMD)), 0, false, Arrays.asList(new ReloadCommand(), new ToggleCommand()));
    }

    @Override
    public void execute(ICommandSender sender, String... args)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase(Commands.HELP))
            {
                new ForgeHelpCommand(this).execute(sender, moveArguments(args));
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

        ForgeEmergencyWhitelist.commands.forEach(command -> {
            if (command.getName().equalsIgnoreCase(args[0]))
                sender.addChatMessage(new ForgeHelpCommand(command).getCommandHelpInfo());
        });
    }
}
