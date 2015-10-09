package musician101.emergencywhitelist.common;

public abstract class AbstractConfig
{
    private boolean enabled;

    protected AbstractConfig()
    {

    }

    public abstract void reloadConfiguration();

    public boolean isWhitelistEnabled()
    {
        return enabled;
    }

    public void setWhitelistEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
