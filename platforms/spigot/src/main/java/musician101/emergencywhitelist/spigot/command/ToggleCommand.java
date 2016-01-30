package musician101.emergencywhitelist.spigot.command;

import java.util.Arrays;
import musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import musician101.common.java.minecraft.spigot.command.SpigotCommandArgument;
import musician101.emergencywhitelist.common.Reference.Commands;
import musician101.emergencywhitelist.common.Reference.Config;
import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.common.Reference.Permissions;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.command.CommandSender;

public class ToggleCommand extends AbstractSpigotCommand
{
    private final SpigotEmergencyWhitelist plugin;

    public ToggleCommand(SpigotEmergencyWhitelist plugin)
    {
        super(Commands.TOGGLE_NAME, Commands.TOGGLE_DESC, Arrays.asList(new SpigotCommandArgument(Commands.EWL_CMD), new SpigotCommandArgument(Commands.TOGGLE_NAME)), 0, Permissions.TOGGLE, false, Messages.NO_PERMISSION, Messages.PLAYER_CMD);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!canSenderUseCommand(sender))
            return false;

        plugin.getConfig().set(Config.ENABLED, !plugin.getPluginConfig().isWhitelistEnabled());
        plugin.saveConfig();
        plugin.getPluginConfig().setWhitelistEnabled(plugin.getPluginConfig().isWhitelistEnabled());
        return true;
    }
}
