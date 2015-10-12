package musician101.emergencywhitelist.forge.command.ewl;

import musician101.common.java.minecraft.forge.AbstractForgeCommand;
import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ReloadCommand extends AbstractForgeCommand
{
    public ReloadCommand()
    {
        super("reload", "Reload the plugins configuration.", "/ewl reload", 0, false);
    }

    @Override
    public void execute(ICommandSender sender, String... args)
    {
        if (!canCommandSenderUse(sender))
            return;

        EmergencyWhitelist.config.reloadConfiguration();
        sender.addChatMessage(new ChatComponentText("[EWL] Config reloaded."));
    }
}
