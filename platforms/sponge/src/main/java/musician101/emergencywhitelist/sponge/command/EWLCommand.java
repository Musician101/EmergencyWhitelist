package musician101.emergencywhitelist.sponge.command;

import musician101.common.java.minecraft.sponge.command.AbstractSpongeCommand;
import musician101.common.java.minecraft.sponge.command.SpongeCommandArgument;
import musician101.common.java.minecraft.sponge.command.SpongeHelpCommand;
import musician101.emergencywhitelist.common.Reference;
import musician101.emergencywhitelist.common.Reference.Commands;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;

import javax.annotation.Nonnull;
import java.util.Collections;

public class EWLCommand extends AbstractSpongeCommand
{
    public EWLCommand()
    {
        super(Reference.ID, Commands.EWL_DESC, Collections.singletonList(new SpongeCommandArgument(Commands.EWL_CMD)), 0, "", false, null, null);
    }

    @Nonnull
    @Override
    public CommandResult process(@Nonnull CommandSource source, @Nonnull String arguments) throws CommandException
    {
        String[] args = splitArgs(arguments);
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase(Commands.HELP))
                return new SpongeHelpCommand(this, source).process(source, moveArguments(args));

            for (AbstractSpongeCommand command : getSubCommands())
                if (command.getName().equalsIgnoreCase(args[0]))
                    return command.process(source, moveArguments(args));
        }

        return new SpongeHelpCommand(this, source).process(source, moveArguments(args));
    }
}
