package musician101.emergencywhitelist.forge.util;

import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.forge.ForgeEmergencyWhitelist;
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
        ForgeEmergencyWhitelist.logger.info(Messages.whitelistLog(enabled));
        ForgeEmergencyWhitelist.logger.info(Messages.WHITELIST_LOG);
        for (Object object : MinecraftServer.getServer().getConfigurationManager().playerEntityList)
        {
            EntityPlayerMP player = (EntityPlayerMP) object;
            if (enabled && !ForgeEmergencyWhitelist.config.hasPermission(player.getUniqueID()))
                player.playerNetServerHandler.kickPlayerFromServer(Messages.WHITELIST_ENABLED);
            else
                player.addChatMessage(new ChatComponentText(Messages.whitelistBroadcast(enabled)));
        }
    }
}
