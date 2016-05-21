package io.musician101.emergencywhitelist.spigot;

import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class KickPlayers implements Runnable
{
    private KickPlayers()
    {

    }

    public static void kickPlayers(boolean enabled)
    {
        if (enabled)
            SpigotEmergencyWhitelist.instance().getServer().getScheduler().scheduleSyncDelayedTask(SpigotEmergencyWhitelist.instance(), new KickPlayers(), 100L);

        SpigotEmergencyWhitelist.instance().getLogger().info(Messages.whitelistLog(enabled));
        SpigotEmergencyWhitelist.instance().getLogger().info(Messages.WHITELIST_LOG);
        Bukkit.broadcastMessage(ChatColor.GOLD + Messages.whitelistBroadcast(enabled));
    }

    @Override
    public void run()
    {
        Bukkit.getOnlinePlayers().stream().filter(player -> !player.hasPermission(Permissions.WHITELIST)).forEach(player -> player.kickPlayer(Messages.WHITELIST_ENABLED));
    }
}
