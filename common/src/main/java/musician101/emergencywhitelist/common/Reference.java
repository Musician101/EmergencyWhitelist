package musician101.emergencywhitelist.common;

public class Reference
{
    public static final String ID = "ewl";
    public static final String NAME = "${project.name}";
    public static final String PREFIX = "[" + ID.toUpperCase() + "] ";
    public static final String VERSION = "${project.version}";

    public static class Commands
    {
        public static final String EWL_CMD = "/" + ID;
        public static final String EWL_DESC = "${project.description}";

        public static final String HELP = "help";

        public static final String RELOAD_NAME = "reload";
        public static final String RELOAD_DESC = "Reload the plugins configuration.";

        public static final String TOGGLE_NAME = "toggle";
        public static final String TOGGLE_DESC = "Toggle the whitelist.";
    }

    public static class Config
    {
        public static final String EWL_FILE = ID + ".json";
        public static final String ENABLED = "enabled";
    }

    public static class Messages
    {
        public static final String CONFIG_FAIL_CREATE = "Could not create " + Config.EWL_FILE;
        public static final String CONFIG_FAIL_LOAD = "Could not load emergency_whitelist.json";
        public static final String CONFIG_FAIL_SAVE = "Could not write to " + Config.EWL_FILE + ".";
        public static final String NO_PERMISSION = PREFIX + "You do not have permission for that.";
        public static final String PLAYER_CMD = PREFIX + "This is a player command only.";
        public static final String WHITELIST_ENABLED = NAME + " has been enabled.";
        public static final String WHITELIST_LOG = "Use /ewl toggle to enable/disable the whitelist.";
        public static final String WHITELIST_MANUAL_UPDATE = "The 'enabled' option in the config file must be updated manually.";

        public static String whitelistBroadcast(boolean enabled)
        {
            return PREFIX + "Whitelist " + (enabled ? "enabled. Kicking non-whitelisted players" : "disabled") + ".";
        }

        public static String playerConnectAttempt(String playerName)
        {
            return playerName + " attempted to connect.";
        }

        public static String whitelistLog(boolean enabled)
        {
            return "The whitelist is currently " + (enabled ? "enable" : "disable") + "d.";
        }
    }

    public static class Permissions
    {
        public static final String RELOAD = ID + ".reload";
        public static final String TOGGLE = ID + ".toggle";
        public static final String WHITELIST = ID  + ".whitelist";
    }
}
