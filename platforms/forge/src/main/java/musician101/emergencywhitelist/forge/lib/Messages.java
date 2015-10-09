package musician101.emergencywhitelist.forge.lib;

import net.minecraft.util.EnumChatFormatting;

public class Messages
{
    public static final String WHITELIST_PERM = "ewl.whitelist";
    public static final String PREFIX = EnumChatFormatting.GOLD + "[EWL] ";
    public static final String NO_PERMISSION = PREFIX + "You do not have permission for this command.";
    public static final String PLAYER_CMD = PREFIX + "This is a player command only.";

    private Messages()
    {

    }

    public static String[] getEWLText(boolean enabled, String version)
    {
        String isEnabled;
        if (enabled)
            isEnabled = "Enabled";
        else
            isEnabled = "Disabled";

        return new String[]{PREFIX + "Version " + version + " compiled with Spigot 1.8.8-R0.1-SNAPSHOT.", PREFIX + "Whitelist: " + isEnabled + ".", PREFIX + "Type /ewl help for a list of commands."};
    }
}
