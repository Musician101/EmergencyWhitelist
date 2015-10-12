package musician101.emergencywhitelist.spigot;

import java.io.File;
import musician101.emergencywhitelist.common.AbstractConfig;
import musician101.emergencywhitelist.spigot.util.KickPlayers;
import org.bukkit.configuration.file.FileConfiguration;

public class SpigotConfig extends AbstractConfig
{
    private final SpigotEmergencyWhitelist plugin;
    private boolean updateCheck;

    public SpigotConfig(SpigotEmergencyWhitelist plugin)
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
