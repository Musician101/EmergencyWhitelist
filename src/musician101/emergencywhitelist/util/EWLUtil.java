package musician101.emergencywhitelist.util;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Messages;
import musician101.emergencywhitelist.runnables.KickPlayers;

import org.bukkit.Bukkit;

public class EWLUtil 
{
	public static void kickPlayers(EmergencyWhitelist plugin, boolean enabled)
	{
		plugin.getLogger().info(Messages.getWhitelistEnabled(enabled));
		plugin.getLogger().info(Messages.getToggleMessage(enabled));
		if (enabled)
		{
			Bukkit.broadcastMessage(Messages.getWhitelistAnnounce(enabled));
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new KickPlayers(plugin), 100L);
		}
		else
			Bukkit.broadcastMessage(Messages.getWhitelistAnnounce(enabled));
	}
}
