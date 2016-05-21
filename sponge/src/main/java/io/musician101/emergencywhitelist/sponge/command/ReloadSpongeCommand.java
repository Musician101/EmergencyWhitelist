package io.musician101.emergencywhitelist.sponge.command;

import io.musician101.common.java.minecraft.sponge.TextUtils;
import io.musician101.common.java.minecraft.sponge.command.AbstractSpongeCommand;
import io.musician101.common.java.minecraft.sponge.command.SpongeCommandArgument;
import io.musician101.common.java.minecraft.sponge.command.SpongeCommandPermissions;
import io.musician101.common.java.minecraft.sponge.command.SpongeCommandUsage;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import io.musician101.emergencywhitelist.sponge.SpongeEmergencyWhitelist;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class ReloadSpongeCommand extends AbstractSpongeCommand
{
    public ReloadSpongeCommand()
    {
        super(Commands.RELOAD_NAME, Text.of(Commands.RELOAD_DESC), new SpongeCommandUsage(Arrays.asList(new SpongeCommandArgument(Commands.EWL_CMD), new SpongeCommandArgument(Commands.RELOAD_NAME))), new SpongeCommandPermissions(Permissions.RELOAD, false, TextUtils.redText(Messages.NO_PERMISSION), TextUtils.redText(Messages.PLAYER_CMD)));
    }

    @Nonnull
    @Override
    public CommandResult process(@Nonnull CommandSource source, @Nonnull String arguments)
    {
        if (!testPermission(source))
            return CommandResult.empty();

        SpongeEmergencyWhitelist.instance().getConfig().reload();
        source.sendMessage(TextUtils.greenText(Messages.CONFIG_RELOADED));
        return CommandResult.success();
    }
}