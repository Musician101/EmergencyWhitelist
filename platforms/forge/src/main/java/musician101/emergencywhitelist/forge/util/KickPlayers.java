package musician101.emergencywhitelist.forge.util;

import musician101.emergencywhitelist.forge.ForgeEmergencyWhitelist;
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
            ForgeEmergencyWhitelist.logger.info("The whitelist is currently enabled.");
            ForgeEmergencyWhitelist.logger.info("Use /ewl toggle to enable/disable the whitelist.");
            isEnabled = "enabled. Kicking non-whitelist players";
        }
        else
        {
            ForgeEmergencyWhitelist.logger.info("The whitelist is currently disabled.");
            ForgeEmergencyWhitelist.logger.info("Use /ewl toggle to enable/disable the whitelist.");
            isEnabled = "disabled";
        }

        for (Object object : MinecraftServer.getServer().getConfigurationManager().playerEntityList)
        {
            EntityPlayerMP player = (EntityPlayerMP) object;
            if (enabled && !ForgeEmergencyWhitelist.config.hasPermission(player.getUniqueID()))
                player.playerNetServerHandler.kickPlayerFromServer("Server whitelist has been enabled.");
            else
                player.addChatMessage(new ChatComponentText(Messages.PREFIX + "Whitelist " + isEnabled + "."));
        }
    }
}
