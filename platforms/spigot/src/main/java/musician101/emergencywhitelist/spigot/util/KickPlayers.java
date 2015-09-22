package musician101.emergencywhitelist.spigot.util;

import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.lib.Messages;
import org.bukkit.Bukkit;

public class KickPlayers implements Runnable
{
    private KickPlayers()
    {

    }

    public static void kickPlayers(EmergencyWhitelist plugin, boolean enabled)
    {
        if (enabled)
        {
            plugin.getLogger().info("The whitelist is currently enabled.");
            plugin.getLogger().info("Use /ewl toggle to enable/disable the whitelist.");
            Bukkit.broadcastMessage(getWhitelistAnnounce(true));
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new KickPlayers(), 100L);
        }
        else
        {
            plugin.getLogger().info("The whitelist is currently disabled.");
            plugin.getLogger().info("Use /ewl toggle to enable/disable the whitelist.");
            Bukkit.broadcastMessage(getWhitelistAnnounce(false));
        }
    }

    private static String getWhitelistAnnounce(boolean enabled)
    {
        String isEnabled;
        if (enabled)
            isEnabled = "enabled. Kicking non-whitelist players";
        else
            isEnabled = "disabled";

        return Messages.PREFIX + "Whitelist " + isEnabled + ".";
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
