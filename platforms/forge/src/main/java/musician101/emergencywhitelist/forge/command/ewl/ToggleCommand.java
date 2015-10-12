package musician101.emergencywhitelist.forge.command.ewl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import musician101.common.java.minecraft.forge.AbstractForgeCommand;
import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import musician101.emergencywhitelist.forge.lib.ModInfo;
import musician101.emergencywhitelist.forge.util.KickPlayers;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class ToggleCommand extends AbstractForgeCommand
{
    public ToggleCommand()
    {
        super("toggle", "Toggle the whitelist.", "/ewl reload", 0, false);
    }

    @Override
    public void execute(ICommandSender sender, String... args)
    {
        if (!canCommandSenderUse(sender))
            return;

        boolean enabled = EmergencyWhitelist.config.isWhitelistEnabled();
        File configFile = new File(EmergencyWhitelist.config.configDir, ModInfo.ID + ".cfg");
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(configFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));
            bw.write(br.readLine().replace("whitelist: " + enabled, "whitelist: " + !enabled));
            br.close();
            bw.close();
        }
        catch (IOException exception)
        {
            sender.addChatMessage(new ChatComponentText("[EWL] An error occurred while reloading the config."));
            return;
        }

        EmergencyWhitelist.config.reloadConfiguration();
        KickPlayers.kickPlayers(EmergencyWhitelist.config.isWhitelistEnabled());
    }
}
