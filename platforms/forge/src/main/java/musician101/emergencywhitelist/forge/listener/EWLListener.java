package musician101.emergencywhitelist.forge.listener;

import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;

public class EWLListener
{
    @EventHandler
    public void onClientConnect(ServerConnectionFromClientEvent event)
    {
        EntityPlayerMP player = (EntityPlayerMP) event.handler;
        if (EmergencyWhitelist.config.isWhitelistEnabled())
        {
            if (!EmergencyWhitelist.config.hasPermission(player.getUniqueID()))
            {
                player.playerNetServerHandler.kickPlayerFromServer("EmergencyWhitelist has been enabled.");
                EmergencyWhitelist.logger.info(player.getName() + " attempted to connect.");
            }
        }
    }
}
