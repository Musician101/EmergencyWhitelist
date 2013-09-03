package musician101.emergencywhitelist.util;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;
import musician101.emergencywhitelist.runnables.KickPlayers;

import org.bukkit.Bukkit;


public class RunKickMethod 
{
	
	public RunKickMethod(EmergencyWhitelist plugin, boolean enabled)
	{
		plugin.logger().info(Constants.getWhitelistEnabled(plugin.getConfig().getBoolean("enabled")));
		plugin.logger().info(Constants.getToggleMessage(plugin.getConfig().getBoolean("enabled")));
		if (enabled)
		{
			Bukkit.broadcastMessage(Constants.WHITELIST_ANNOUNCE);
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new KickPlayers(plugin), 100L);
		}
	}
}
