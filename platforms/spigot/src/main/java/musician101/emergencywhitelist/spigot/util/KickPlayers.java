package musician101.emergencywhitelist.spigot.util;

import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.common.Reference.Permissions;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class KickPlayers implements Runnable
{
    private KickPlayers()
    {

    }

    public static void kickPlayers(SpigotEmergencyWhitelist plugin, boolean enabled)
    {
        if (enabled)
            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new KickPlayers(), 100L);

        plugin.getLogger().info(Messages.whitelistLog(enabled));
        plugin.getLogger().info(Messages.WHITELIST_LOG);
        Bukkit.broadcastMessage(ChatColor.GOLD + Messages.whitelistBroadcast(enabled));
    }

    @Override
    public void run()
    {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (!player.hasPermission(Permissions.WHITELIST))
                player.kickPlayer(Messages.WHITELIST_ENABLED);
        });
    }
}
