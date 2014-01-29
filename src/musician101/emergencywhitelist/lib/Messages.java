package musician101.emergencywhitelist.lib;

import org.bukkit.entity.Player;

public class Messages
{
	public static final String WHITELIST_ENABLED = "EmergencyWhitelist has been enabled.";
	
	public static String getWhitelistAnnounce(boolean enabled)
	{
		String isEnabled = "";
		if (enabled)
			isEnabled = "enabled. Kicking non-whitelist players";
		else
			isEnabled = "disabled";
		
		return StringFormatting.PREFIX + "Whitelist " + isEnabled + ".";
	}
	
	public static String getDisconnectedPlayer(Player player)
	{
		return player.getName() + " attempted to connect.";
	}
	
	public static final String getToggleMessage(boolean enabled)
	{
		String isEnabled = "";
		if (enabled)
			isEnabled = "enable";
		else
			isEnabled = "disable";
		
		return "Use /ewl toggle to " + isEnabled + " the whitelist.";
	}
	
	public static String getWhitelistEnabled(boolean enabled)
	{
		String isEnabled = "";
		if (enabled)
			isEnabled = "enabled";
		else
			isEnabled = "disabled";
		
		return "Whitelist is currently " + isEnabled + ".";
	}
}
