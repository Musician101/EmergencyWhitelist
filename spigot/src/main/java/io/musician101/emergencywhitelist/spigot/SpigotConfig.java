package io.musician101.emergencywhitelist.spigot;

import io.musician101.emergencywhitelist.common.EWLConfig;
import io.musician101.emergencywhitelist.common.Reference.Config;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class SpigotConfig extends EWLConfig {

    @Override
    public void load() {
        SpigotEmergencyWhitelist plugin = SpigotEmergencyWhitelist.instance();
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        whitelistEnabled = config.getBoolean(Config.ENABLED, true);
        if (whitelistEnabled) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> Bukkit.getOnlinePlayers().stream().filter(player -> !player.hasPermission(Permissions.WHITELIST)).forEach(player -> player.kickPlayer(Messages.WHITELIST_ENABLED)), 100L);
        }

        Bukkit.broadcastMessage(ChatColor.GOLD + Messages.whitelistBroadcast(whitelistEnabled));
        plugin.getLogger().info(Messages.WHITELIST_LOG);
    }

    @Override
    public void save() {
        SpigotEmergencyWhitelist plugin = SpigotEmergencyWhitelist.instance();
        plugin.saveDefaultConfig();
        plugin.getConfig().set(Config.ENABLED, whitelistEnabled);
        plugin.saveConfig();
    }
}
