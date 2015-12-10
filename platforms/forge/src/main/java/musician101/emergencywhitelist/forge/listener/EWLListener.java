package musician101.emergencywhitelist.forge.listener;

import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.forge.ForgeEmergencyWhitelist;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;

public class EWLListener
{
    @EventHandler
    public void onClientConnect(ServerConnectionFromClientEvent event)
    {
        EntityPlayerMP player = (EntityPlayerMP) event.handler;
        if (ForgeEmergencyWhitelist.config.isWhitelistEnabled())
        {
            if (!ForgeEmergencyWhitelist.config.hasPermission(player.getUniqueID()))
            {
                player.playerNetServerHandler.kickPlayerFromServer(Messages.WHITELIST_ENABLED);
                ForgeEmergencyWhitelist.logger.info(Messages.playerConnectAttempt(player.getName()));
            }
        }
    }
}
