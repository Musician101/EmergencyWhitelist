package io.musician101.emergencywhitelist.spigot;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.musician101.bukkitier.Bukkitier;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.common.Reference.Config;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotEmergencyWhitelist extends JavaPlugin {

    private final SpigotConfig config = new SpigotConfig();

    public static SpigotEmergencyWhitelist instance() {
        return JavaPlugin.getPlugin(SpigotEmergencyWhitelist.class);
    }

    @Override
    public void onDisable() {
        config.save();
    }

    public SpigotConfig getPluginConfig() {
        return config;
    }

    @Override
    public void onEnable() {
        config.load();
        getServer().getPluginManager().registerEvents(new EWLListener(), this);
        Bukkitier.registerCommand(this, Bukkitier.literal(Commands.EWL_CMD).then(reload()).then(toggle()));
    }

    private LiteralArgumentBuilder<CommandSender> reload() {
        return Bukkitier.literal(Commands.RELOAD_NAME).requires(sender -> sender.hasPermission(Permissions.RELOAD)).executes(context -> {
            SpigotEmergencyWhitelist.instance().getPluginConfig().load();
            context.getSource().sendMessage(ChatColor.GOLD + Messages.CONFIG_RELOADED);
            return Command.SINGLE_SUCCESS;
        });
    }

    private LiteralArgumentBuilder<CommandSender> toggle() {
        return Bukkitier.literal(Commands.TOGGLE_NAME).requires(sender -> sender.hasPermission(Permissions.TOGGLE)).executes(context -> {
            SpigotEmergencyWhitelist instance = SpigotEmergencyWhitelist.instance();
            instance.getConfig().set(Config.ENABLED, !instance.getPluginConfig().isWhitelistEnabled());
            instance.saveConfig();
            instance.getPluginConfig().load();
            return Command.SINGLE_SUCCESS;
        });
    }
}
