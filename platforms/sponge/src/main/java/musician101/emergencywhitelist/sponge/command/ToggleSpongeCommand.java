package musician101.emergencywhitelist.sponge.command;

import musician101.common.java.minecraft.sponge.TextUtils;
import musician101.common.java.minecraft.sponge.command.AbstractSpongeCommand;
import musician101.common.java.minecraft.sponge.command.SpongeCommandArgument;
import musician101.emergencywhitelist.common.Reference.Commands;
import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.common.Reference.Permissions;
import musician101.emergencywhitelist.sponge.SpongeEmergencyWhitelist;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class ToggleSpongeCommand extends AbstractSpongeCommand
{
    public ToggleSpongeCommand()
    {
        super(Commands.TOGGLE_NAME, Commands.TOGGLE_DESC, Arrays.asList(new SpongeCommandArgument(Commands.EWL_CMD), new SpongeCommandArgument(Commands.TOGGLE_NAME)), 0, Permissions.TOGGLE, false, TextUtils.redText(Messages.NO_PERMISSION), TextUtils.redText(Messages.PLAYER_CMD));
    }

    @Nonnull
    @Override
    public CommandResult process(@Nonnull CommandSource source, @Nonnull String arguments)
    {
        if (!testPermission(source))
            return CommandResult.empty();

        SpongeEmergencyWhitelist.config.setWhitelistEnabled(!SpongeEmergencyWhitelist.config.isWhitelistEnabled());
        return CommandResult.success();
    }
}
