package musician101.emergencywhitelist.spigot;

import musician101.emergencywhitelist.common.AbstractConfig;
import musician101.emergencywhitelist.spigot.util.KickPlayers;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class SpigotConfig extends AbstractConfig
{
    private final EmergencyWhitelist plugin;
    private boolean updateCheck;

    public SpigotConfig(EmergencyWhitelist plugin)
    {
        super();
        this.plugin = plugin;
        File config = new File(plugin.getDataFolder(), "config.yml");
        if (!config.exists())
            plugin.saveDefaultConfig();

        reloadConfiguration();
    }

    @Override
    public void reloadConfiguration()
    {
        plugin.reloadConfig();
        final FileConfiguration config = plugin.getConfig();
        setWhitelistEnabled(config.getBoolean("enabled", true));
        updateCheck = config.getBoolean("checkForUpdate", true);
        KickPlayers.kickPlayers(plugin, isWhitelistEnabled());
    }

    public boolean isUpdateCheckEnabled()
    {
        return updateCheck;
    }
}
