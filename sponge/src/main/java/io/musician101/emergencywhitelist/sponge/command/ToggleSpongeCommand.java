package io.musician101.emergencywhitelist.sponge.command;

import io.musician101.common.java.minecraft.sponge.TextUtils;
import io.musician101.common.java.minecraft.sponge.command.AbstractSpongeCommand;
import io.musician101.common.java.minecraft.sponge.command.SpongeCommandArgument;
import io.musician101.common.java.minecraft.sponge.command.SpongeCommandPermissions;
import io.musician101.common.java.minecraft.sponge.command.SpongeCommandUsage;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import io.musician101.emergencywhitelist.sponge.SpongeConfig;
import io.musician101.emergencywhitelist.sponge.SpongeEmergencyWhitelist;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class ToggleSpongeCommand extends AbstractSpongeCommand
{
    public ToggleSpongeCommand()
    {
        super(Commands.TOGGLE_NAME, Text.of(Commands.TOGGLE_DESC), new SpongeCommandUsage(Arrays.asList(new SpongeCommandArgument(Commands.EWL_CMD), new SpongeCommandArgument(Commands.TOGGLE_NAME))), new SpongeCommandPermissions(Permissions.TOGGLE, false, TextUtils.redText(Messages.NO_PERMISSION), TextUtils.redText(Messages.PLAYER_CMD)));
    }

    @Nonnull
    @Override
    public CommandResult process(@Nonnull CommandSource source, @Nonnull String arguments)
    {
        if (!testPermission(source))
            return CommandResult.empty();

        SpongeConfig config = SpongeEmergencyWhitelist.instance().getConfig();
        config.setWhitelistEnabled(!config.isWhitelistEnabled());
        //TODO need autokick method
        return CommandResult.success();
    }
}
