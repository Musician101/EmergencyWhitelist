package musician101.emergencywhitelist.spigot;

import musician101.common.java.minecraft.spigot.config.AbstractSpigotConfig;
import musician101.emergencywhitelist.common.IEWLConfig;
import musician101.emergencywhitelist.common.Reference.Config;
import musician101.emergencywhitelist.spigot.util.KickPlayers;
import org.bukkit.configuration.file.FileConfiguration;

public class SpigotConfig extends AbstractSpigotConfig<SpigotEmergencyWhitelist> implements IEWLConfig
{
    private boolean updateCheck;
    private boolean whitelistEnabled;

    public SpigotConfig(SpigotEmergencyWhitelist plugin)
    {
        super(plugin);
        reload();
    }

    @Override
    public boolean isWhitelistEnabled()
    {
        return whitelistEnabled;
    }

    @Override
    public void setWhitelistEnabled(boolean whitelistEnabled)
    {
        this.whitelistEnabled = whitelistEnabled;
    }

    @Override
    public void reload()
    {
        if (!configFile.exists())
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        whitelistEnabled = config.getBoolean(Config.ENABLED, true);
        updateCheck = config.getBoolean(Config.CHECK_FOR_UPDATE, true);
        KickPlayers.kickPlayers(plugin, isWhitelistEnabled());
    }

    public boolean isUpdateCheckEnabled()
    {
        return updateCheck;
    }
}
