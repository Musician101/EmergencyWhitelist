package io.musician101.emergencywhitelist.common;

import java.io.File;

public class Reference
{
    public static final String ID = "io.musician101.emergencywhitelist";
    public static final String NAME = "Emergency Whitelist";
    public static final String PREFIX = "[" + ID.toUpperCase() + "] ";
    public static final String VERSION = "2.0";

    private Reference()
    {

    }

    public static class Commands
    {
        public static final String EWL_CMD = "/ewl";
        public static final String EWL_DESC = "Secondary whitelisting for server admins.";

        public static final String HELP = "help";

        public static final String RELOAD_NAME = "reload";
        public static final String RELOAD_DESC = "Reload the plugins configuration.";

        public static final String TOGGLE_NAME = "toggle";
        public static final String TOGGLE_DESC = "Toggle the whitelist.";

        private Commands()
        {

        }
    }

    public static class Config
    {
        public static final String CHECK_FOR_UPDATE = "checkForUpdate";
        public static final String ENABLED = "enabled";

        private Config()
        {

        }
    }

    public static class Messages
    {
        public static final String CONFIG_RELOADED = PREFIX + "Config reloaded.";
        public static final String NO_PERMISSION = PREFIX + "You do not have permission for that.";
        public static final String PLAYER_CMD = PREFIX + "This is a player command only.";
        public static final String WHITELIST_ENABLED = NAME + " has been enabled.";
        public static final String WHITELIST_LOG = "Use /ewl toggle to enable/disable the whitelist.";

        private Messages()
        {

        }

        public static String fileCreateFailed(File file)
        {
            return "Failed to create " + file.getName();
        }

        public static String fileLoadFailed(File file)
        {
            return "Failed to load " + file.getName();
        }

        public static String playerConnectAttempt(String playerName)
        {
            return playerName + " attempted to connect.";
        }

        public static String whitelistBroadcast(boolean enabled)
        {
            return PREFIX + "Whitelist " + (enabled ? "enabled. Kicking non-whitelisted players" : "disabled") + ".";
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
        public static final String WHITELIST = ID + ".whitelist";

        private Permissions()
        {

        }
    }
}
