package io.musician101.emergencywhitelist.spigot;

import io.musician101.emergencywhitelist.common.EWLConfig;
import io.musician101.emergencywhitelist.common.Reference.Config;
import org.bukkit.configuration.file.FileConfiguration;

public class SpigotConfig extends EWLConfig
{
    public SpigotConfig()
    {
        super(SpigotEmergencyWhitelist.instance().getDataFolder());
        reload();
    }

    @Override
    public void reload()
    {
        SpigotEmergencyWhitelist instance = SpigotEmergencyWhitelist.instance();
        if (!configFile.exists())
            instance.reloadConfig();

        FileConfiguration config = instance.getConfig();
        whitelistEnabled = config.getBoolean(Config.ENABLED, true);
        updateCheck = config.getBoolean(Config.CHECK_FOR_UPDATE, true);
        KickPlayers.kickPlayers(instance, isWhitelistEnabled());
    }
}
