package musician101.emergencywhitelist.sponge.command.ewl;

import java.util.Collections;
import javax.annotation.Nonnull;
import musician101.common.java.minecraft.sponge.command.AbstractSpongeCommand;
import musician101.emergencywhitelist.common.CommonReference;
import musician101.emergencywhitelist.common.CommonReference.CommonCommands;
import musician101.emergencywhitelist.sponge.command.HelpCommand;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;

public class EWLCommand extends AbstractSpongeCommand
{
    public EWLCommand()
    {
        super(CommonReference.ID, Texts.builder(CommonCommands.EWL_DESC).color(TextColors.GOLD).build(), Collections.singletonList(Texts.of("/" + CommonReference.ID)), 0, "", false, null, null, null);
    }

    @Nonnull
    @Override
    public CommandResult process(@Nonnull CommandSource source, @Nonnull String arguments) throws CommandException
    {
        String[] args = splitArgs(arguments);
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase("help"))
                return new HelpCommand(this).process(source, moveArguments(args));

            for (AbstractSpongeCommand command : getSubCommands())
                if (command.getName().equalsIgnoreCase(args[0]))
                    return command.process(source, moveArguments(args));
        }

        return CommandResult.success();
    }
}
