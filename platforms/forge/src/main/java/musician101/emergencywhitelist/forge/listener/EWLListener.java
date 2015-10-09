package musician101.emergencywhitelist.forge.listener;

import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;

public class EWLListener
{
    public EWLListener()
    {

    }

    @EventHandler
    public void onClientConnect(ServerConnectionFromClientEvent event)
    {
        //((NetHandlerPlayServer) event.handler).kickPlayerFromServer();
        EntityPlayerMP player = (EntityPlayerMP) event.handler;
        if (EmergencyWhitelist.config.isWhitelistEnabled())
        {
            if (!EmergencyWhitelist.config.hasPermission(player))
            {
                player.kickPlayerFromServer("EmergencyWhitelist has been enabled.");
                EmergencyWhitelist.logger.info(((EntityPlayer) player).getName() + " attempted to connect.");
            }
        }
    }
}
