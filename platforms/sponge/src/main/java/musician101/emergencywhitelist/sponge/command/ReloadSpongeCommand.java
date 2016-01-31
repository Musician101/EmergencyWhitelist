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

public class ReloadSpongeCommand extends AbstractSpongeCommand
{
    public ReloadSpongeCommand()
    {
        super(Commands.RELOAD_NAME, Commands.RELOAD_DESC, Arrays.asList(new SpongeCommandArgument(Commands.EWL_CMD), new SpongeCommandArgument(Commands.RELOAD_NAME)), 0, Permissions.RELOAD, false, TextUtils.redText(Messages.NO_PERMISSION), TextUtils.redText(Messages.PLAYER_CMD));
    }

    @Nonnull
    @Override
    public CommandResult process(@Nonnull CommandSource source, @Nonnull String arguments)
    {
        if (!testPermission(source))
            return CommandResult.empty();

        SpongeEmergencyWhitelist.config.reload();
        source.sendMessage(TextUtils.greenText(Messages.CONFIG_RELOADED));
        return CommandResult.success();
    }
}
