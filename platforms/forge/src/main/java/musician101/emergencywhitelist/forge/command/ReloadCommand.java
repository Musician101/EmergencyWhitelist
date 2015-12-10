package musician101.emergencywhitelist.forge.command;

import java.util.Arrays;
import musician101.common.java.minecraft.forge.command.AbstractForgeCommand;
import musician101.common.java.minecraft.forge.command.ForgeCommandArgument;
import musician101.emergencywhitelist.common.Reference.Commands;
import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.forge.ForgeEmergencyWhitelist;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ReloadCommand extends AbstractForgeCommand
{
    public ReloadCommand()
    {
        super(Commands.RELOAD_NAME, Commands.RELOAD_DESC, Arrays.asList(new ForgeCommandArgument(Commands.EWL_CMD), new ForgeCommandArgument(Commands.RELOAD_NAME)), 0, false);
    }

    @Override
    public void execute(ICommandSender sender, String... args)
    {
        if (!canCommandSenderUse(sender))
            return;

        ForgeEmergencyWhitelist.config.reloadConfiguration();
        sender.addChatMessage(new ChatComponentText(Messages.CONFIG_RELOADED));
    }
}
