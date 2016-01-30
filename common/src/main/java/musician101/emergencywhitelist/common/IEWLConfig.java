package musician101.emergencywhitelist.common;

public interface IEWLConfig
{
    default boolean isWhitelistEnabled()
    {
        return true;
    }

    void setWhitelistEnabled(boolean enabled);
}
