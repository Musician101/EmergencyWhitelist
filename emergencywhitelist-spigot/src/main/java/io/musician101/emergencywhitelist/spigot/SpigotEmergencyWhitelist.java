package io.musician101.emergencywhitelist.spigot;

import io.musician101.musicianlibrary.java.minecraft.spigot.plugin.AbstractSpigotPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotEmergencyWhitelist extends AbstractSpigotPlugin<SpigotConfig, SpigotEmergencyWhitelist> {

    public static SpigotEmergencyWhitelist instance() {
        return JavaPlugin.getPlugin(SpigotEmergencyWhitelist.class);
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down.");
    }

    @Override
    public void onEnable() {
        config = new SpigotConfig();
        getServer().getPluginManager().registerEvents(new EWLListener(), this);
        commands.add(SpigotEWLCommands.ewl());
    }
}
