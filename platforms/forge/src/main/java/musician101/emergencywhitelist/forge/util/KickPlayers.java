package musician101.emergencywhitelist.forge.util;

import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import musician101.emergencywhitelist.forge.lib.Messages;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class KickPlayers
{
    private KickPlayers()
    {

    }

    public static void kickPlayers(boolean enabled)
    {
        String isEnabled;
        if (enabled)
        {
            EmergencyWhitelist.logger.info("The whitelist is currently enabled.");
            EmergencyWhitelist.logger.info("Use /ewl toggle to enable/disable the whitelist.");
            isEnabled = "enabled. Kicking non-whitelist players";
        }
        else
        {
            EmergencyWhitelist.logger.info("The whitelist is currently disabled.");
            EmergencyWhitelist.logger.info("Use /ewl toggle to enable/disable the whitelist.");
            isEnabled = "disabled";
        }

        for (Object object : MinecraftServer.getServer().getConfigurationManager().playerEntityList)
        {
            EntityPlayerMP player = (EntityPlayerMP) object;
            if (enabled && !EmergencyWhitelist.config.hasPermission(player.getUniqueID()))
                    player.playerNetServerHandler.kickPlayerFromServer("Server whitelist has been enabled.");
            else
                player.addChatMessage(new ChatComponentText(Messages.PREFIX + "Whitelist " + isEnabled + "."));
        }
    }
}
