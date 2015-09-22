package musician101.emergencywhitelist.common;

public abstract class Config
{
    private boolean enabled;

    protected Config()
    {

    }

    public abstract void reloadConfiguration();

    public void setWhitelistEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isWhitelistEnabled()
    {
        return enabled;
    }
}
