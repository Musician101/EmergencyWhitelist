package io.musician101.emergencywhitelist.spigot;

import io.musician101.common.java.minecraft.spigot.AbstractSpigotPlugin;
import io.musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import io.musician101.emergencywhitelist.spigot.command.EWLSpigotCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class SpigotEmergencyWhitelist extends AbstractSpigotPlugin<SpigotConfig>
{
    @Override
    public void onEnable()
    {
        config = new SpigotConfig();
        getServer().getPluginManager().registerEvents(new EWLListener(), this);
        commands = Collections.singletonList(new EWLSpigotCommand());
        KickPlayers.kickPlayers(config.isWhitelistEnabled());
        versionCheck(46809);
    }

    @Override
    public void onDisable()
    {
        getLogger().info("Shutting down.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        for (AbstractSpigotCommand cmd : commands)
            if (command.getName().equalsIgnoreCase(cmd.getName()))
                return cmd.onCommand(sender, args);

        return false;
    }

    public static SpigotEmergencyWhitelist instance()
    {
        return JavaPlugin.getPlugin(SpigotEmergencyWhitelist.class);
    }
}
