package musician101.emergencywhitelist.forge.command;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import musician101.common.java.minecraft.forge.command.AbstractForgeCommand;
import musician101.common.java.minecraft.forge.command.ForgeCommandArgument;
import musician101.emergencywhitelist.common.Reference.Commands;
import musician101.emergencywhitelist.common.Reference.Config;
import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.forge.ForgeEmergencyWhitelist;
import musician101.emergencywhitelist.forge.util.KickPlayers;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ToggleCommand extends AbstractForgeCommand
{
    public ToggleCommand()
    {
        super(Commands.TOGGLE_NAME, Commands.TOGGLE_DESC, Arrays.asList(new ForgeCommandArgument(Commands.EWL_CMD), new ForgeCommandArgument(Commands.TOGGLE_NAME)), 0, false);
    }

    @Override
    public void execute(ICommandSender sender, String... args)
    {
        if (!canCommandSenderUse(sender))
        {
            //TODO need to change AbstractForgeCommand.canCommandSenderUse() to send messages based on outcome.
            sender.addChatMessage(new ChatComponentText(Messages.NO_PERMISSION));
            sender.addChatMessage(new ChatComponentText(Messages.PLAYER_CMD));
            return;
        }

        boolean enabled = ForgeEmergencyWhitelist.config.isWhitelistEnabled();
        File configFile = new File(ForgeEmergencyWhitelist.config.configDir, Config.EWL_CFG_CONFIG);
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(configFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));
            bw.write(br.readLine().replace(Config.ENABLED + ": " + enabled, Config.ENABLED + ": " + !enabled));
            bw.close();
            br.close();
        }
        catch (IOException exception)
        {
            sender.addChatMessage(new ChatComponentText(Messages.CONFIG_FAIL_LOAD));
            return;
        }

        ForgeEmergencyWhitelist.config.reloadConfiguration();
        KickPlayers.kickPlayers(ForgeEmergencyWhitelist.config.isWhitelistEnabled());
    }
}
