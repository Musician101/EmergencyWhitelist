package musician101.emergencywhitelist.lib;

public class Commands
{
	public static final String EWL_CMD = "ewl";
	public static final String HELP_CMD = "help";
	public static final String RELOAD_CMD = "reload";
	public static final String TOGGLE_CMD = "toggle";
	
	public static final String EWL_PERM = "ewl.";
	public static final String HELP_PERM = EWL_PERM + "help";
	public static final String RELOAD_PERM = EWL_PERM + "reload";
	public static final String TOGGLE_PERM = EWL_PERM + "toggle";
	public static final String WHITELIST_PERM = EWL_PERM + "whitelist";
	
	public static final String NO_PERMISSION = MessageFormatting.PREFIX + "You do not have permission for this command.";
	public static final String[] HELP_TEXT = {MessageFormatting.WHITE + "---------" + MessageFormatting.GOLD + "EmergencyWhitelist" + MessageFormatting.WHITE + "---------",
		MessageFormatting.GOLD + "/ewl reload: " + MessageFormatting.WHITE + "Reload the plugin's config.",
		MessageFormatting.GOLD + "/ewl toggle: " + MessageFormatting.WHITE + "Toggles the server whitelist."};
	
	public static String[] getEWLText(boolean enabled, String version)
	{
		String isEnabled = "";
		if (enabled)
			isEnabled = "Enabled";
		else
			isEnabled = "Disabled";
		
		return new String[]{MessageFormatting.PREFIX + "Version " + version + " compiled with Bukkit 1.7.2-R0.2.",
				MessageFormatting.PREFIX + "Whitelist: " + isEnabled + ".",
				MessageFormatting.PREFIX + "Type /ewl help for a list of commands."};
	}
}
