package musician101.emergencywhitelist.forge.util;

import musician101.emergencywhitelist.forge.EmergencyWhitelist;
import musician101.emergencywhitelist.forge.lib.Messages;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class KickPlayers implements Runnable
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
            EntityPlayer player = (EntityPlayer) object;
            player.addChatMessage(new ChatComponentText(Messages.PREFIX + "Whitelist " + isEnabled + "."));
        }
    }

    @Override
    public void run()
    {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (!player.hasPermission(Messages.WHITELIST_PERM))
                player.kickPlayer("Server whitelist has been enabled.");
        });
    }
}
