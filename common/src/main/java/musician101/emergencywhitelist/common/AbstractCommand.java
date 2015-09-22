package musician101.emergencywhitelist.common;

public abstract class AbstractCommand
{
    private final boolean isPlayerOnly;
    private final int minArgs;
    private final String description;
    private final String name;
    private final String usage;

    protected AbstractCommand(String name, String description, String usage, int minArgs, boolean isPlayerOnly)
    {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.minArgs = minArgs;
        this.isPlayerOnly = isPlayerOnly;
    }

    protected int getMinArgs()
    {
        return minArgs;
    }

    public String getName()
    {
        return name;
    }

    protected String getDescription()
    {
        return description;
    }

    public String getUsage()
    {
        return usage;
    }

    protected boolean isPlayerOnly()
    {
        return isPlayerOnly;
    }

    public abstract String getCommandHelpInfo();
}
