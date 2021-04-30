package io.musician101.emergencywhitelist.common;

public interface Reference {

    String ID = "emergencywhitelist";
    String NAME = "Emergency Whitelist";
    String VERSION = "@VERSION@";

    interface Commands {

        String EWL_CMD = "ewl";
        String RELOAD_NAME = "reload";
        String TOGGLE_NAME = "toggle";

    }

    interface Config {

        String ENABLED = "enabled";

    }

    interface Messages {

        String PREFIX = "[EWL] ";
        String CONFIG_ERROR = PREFIX + "Config failed to load: %s";
        String CONFIG_RELOADED = PREFIX + "Config reloaded.";
        String WHITELIST_ENABLED = NAME + " has been enabled.";
        String WHITELIST_LOG = "Use /ewl toggle to enable/disable the whitelist.";

        static String playerConnectAttempt(String playerName) {
            return playerName + " attempted to connect.";
        }

        static String whitelistBroadcast(boolean enabled) {
            return PREFIX + "Whitelist " + (enabled ? "enabled. Kicking non-whitelisted players" : "disabled") + ".";
        }

    }

    interface Permissions {

        String RELOAD = ID + ".reload";
        String TOGGLE = ID + ".toggle";
        String WHITELIST = ID + ".whitelist";

    }
}
