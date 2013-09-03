package musician101.emergencywhitelist.lib;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Constants
{
	//Formatting
	public static final String PREFIX = ChatColor.GOLD + "[EWL] ";
	
	//Player messages.
	public static final String[] HELP_TEXT = {ChatColor.WHITE + "---------" + ChatColor.GOLD + "EmergencyWhitelist" + ChatColor.WHITE + "---------",
		ChatColor.GOLD + "/ewl toggle: " + ChatColor.WHITE + "Toggles the server whitelist."};
	
	public static final String NO_PERMISSION = PREFIX + "You do not have permission for this command.";
	public static final String WHITELIST_ANNOUNCE = PREFIX + "Whitelist enabled. Kicking non-whitelisted players.";
	public static final String WHITELIST_ENABLED = "EmergencyWhitelist has been enabled";
	
	public static String getVersionMessage(boolean enabled, String version)
	{
		String isEnabled = "";
		if (enabled)
			isEnabled = "Enabled";
		else
			isEnabled = "Disabled";
		
		return PREFIX + "Version " + version + " compiled with Bukkit 1.5.2-R1.0. Whitlist: " + isEnabled + ".";
	}
	
	//Command names
	public static final String EWL = "ewl";
	public static final String HELP = "help";
	public static final String TOGGLE = "toggle";
	
	//Permissions
	public static final String PERMISSION_EWL = "ewl.";
	public static final String PERMISSION_HELP = PERMISSION_EWL + "help";
	public static final String PERMISSION_TOGGLE = PERMISSION_EWL + "toggle";
	public static final String PERMISSION_WHITELIST = PERMISSION_EWL + "whitelist";
	
	//Console Messages
	public static String getDisconnectedPlayer(Player player)
	{
		return player.getName() + " attempted to connect.";
	}
	
	public static final String getToggleMessage(boolean enabled)
	{
		String isEnabled = "";
		if (enabled)
			isEnabled = "enabled";
		else
			isEnabled = "disabled";
		
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
