package io.musician101.emergencywhitelist.spigot;

import io.musician101.emergencywhitelist.common.EWLConfig;
import io.musician101.emergencywhitelist.common.Reference.Config;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class SpigotConfig extends EWLConfig {

    public SpigotConfig() {
        super(SpigotEmergencyWhitelist.instance().getDataFolder());
        reload();
    }

    @Override
    public void reload() {
        SpigotEmergencyWhitelist instance = SpigotEmergencyWhitelist.instance();
        if (!configFile.exists()) {
            instance.reloadConfig();
        }

        FileConfiguration config = instance.getConfig();
        whitelistEnabled = config.getBoolean(Config.ENABLED, true);
        updateCheck = config.getBoolean(Config.CHECK_FOR_UPDATE, true);
        if (whitelistEnabled) {
            SpigotEmergencyWhitelist.instance().getServer().getScheduler().scheduleSyncDelayedTask(SpigotEmergencyWhitelist.instance(), () -> Bukkit.getOnlinePlayers().stream().filter(player -> !player.hasPermission(Permissions.WHITELIST)).forEach(player -> player.kickPlayer(Messages.WHITELIST_ENABLED)), 100L);
        }

        SpigotEmergencyWhitelist.instance().getLogger().info(Messages.whitelistLog(whitelistEnabled));
        SpigotEmergencyWhitelist.instance().getLogger().info(Messages.WHITELIST_LOG);
        Bukkit.broadcastMessage(ChatColor.GOLD + Messages.whitelistBroadcast(whitelistEnabled));
    }
}
