package musician101.emergencywhitelist.util;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.MessageFormatting;
import musician101.emergencywhitelist.runnables.KickPlayers;

import org.bukkit.Bukkit;

public class EWLUtil 
{
	public static void kickPlayers(EmergencyWhitelist plugin, boolean enabled)
	{
		if (enabled)
		{
			plugin.getLogger().info("The whitelist is currently enabled.");
			plugin.getLogger().info("Use /ewl toggle to enable/disable the whitelist.");
			Bukkit.broadcastMessage(getWhitelistAnnounce(enabled));
			plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new KickPlayers(plugin), 100L);
		}
		else
		{
			plugin.getLogger().info("The whitelist is currently disabled.");
			plugin.getLogger().info("Use /ewl toggle to enable/disable the whitelist.");
			Bukkit.broadcastMessage(getWhitelistAnnounce(enabled));
		}
	}
	
	private static String getWhitelistAnnounce(boolean enabled)
	{
		String isEnabled = "";
		if (enabled)
			isEnabled = "enabled. Kicking non-whitelist players";
		else
			isEnabled = "disabled";
		
		return MessageFormatting.PREFIX + "Whitelist " + isEnabled + ".";
	}
}
