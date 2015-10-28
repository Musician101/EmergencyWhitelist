package musician101.emergencywhitelist.common;

public class CommonReference
{
    public static final String ID = "ewl";
    public static final String NAME = "${project.name}";
    public static final String VERSION = "${project.version}";

    public static class CommonCommands
    {
        public static final String EWL_CMD = "/" + ID;
        public static final String EWL_DESC = "${project.description}";

        public static final String RELOAD_NAME = "reload";
        public static final String RELOAD_DESC = "Reload the plugins configuration.";

        public static final String TOGGLE_NAME = "toggle";
        public static final String TOGGLE_DESC = "Toggle the whitelist.";
    }

    public static class Permissions
    {
        public static final String RELOAD_PERM = ID + ".reload";
        public static final String TOGGLE_PERM = ID + ".toggle";
    }

    public static class CommonConfig
    {
        public static final String ENABLED = "enabled";
    }
}
