package musician101.emergencywhitelist.forge.command;

import musician101.common.java.minecraft.forge.AbstractForgeCommand;
import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import musician101.emergencywhitelist.forge.lib.Messages;
import musician101.emergencywhitelist.forge.lib.ModInfo;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class HelpCommand extends AbstractForgeCommand
{
    private final AbstractForgeCommand mainCommand;

    public HelpCommand(ICommandSender sender, AbstractForgeCommand mainCommand)
    {
        super("help", "Display help info for " + EnumChatFormatting.getTextWithoutFormattingCodes(mainCommand.getCommandUsage(sender)), "/" + EnumChatFormatting.getTextWithoutFormattingCodes(mainCommand.getCommandUsage(sender)) + "help", 1, false);
        this.mainCommand = mainCommand;
    }

    @Override
    public void execute(ICommandSender sender, String... args)
    {
        String[] ewlMSG = Messages.getEWLText(EmergencyWhitelist.config.isWhitelistEnabled(), ModInfo.VERSION);
        sender.addChatMessage(new ChatComponentText(ewlMSG[0]));
        sender.addChatMessage(new ChatComponentText(ewlMSG[1]));
        sender.addChatMessage(new ChatComponentText(mainCommand.getCommandUsage(sender)));
        mainCommand.getSubCommands().forEach(command -> sender.addChatMessage(command.getCommandHelpInfo()));
    }
}
