package musician101.emergencywhitelist.spigot.command.ewl;

import java.util.Arrays;
import musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import musician101.common.java.minecraft.spigot.command.CommandArgument;
import musician101.emergencywhitelist.common.CommonReference.CommonCommands;
import musician101.emergencywhitelist.common.CommonReference.CommonConfig;
import musician101.emergencywhitelist.common.CommonReference.Permissions;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import musician101.emergencywhitelist.spigot.lib.Messages;
import org.bukkit.command.CommandSender;

@SuppressWarnings("WeakerAccess")
public class ToggleCommand extends AbstractSpigotCommand
{
    private final SpigotEmergencyWhitelist plugin;

    public ToggleCommand(SpigotEmergencyWhitelist plugin)
    {
        super(CommonCommands.TOGGLE_NAME, CommonCommands.TOGGLE_DESC, Arrays.asList(new CommandArgument(CommonCommands.EWL_CMD), new CommandArgument(CommonCommands.TOGGLE_NAME)), 0, Permissions.TOGGLE_PERM, false, Messages.NO_PERMISSION, Messages.PLAYER_CMD);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (!canSenderUseCommand(sender))
            return false;

        plugin.getConfig().set(CommonConfig.ENABLED, !plugin.getPluginConfig().isWhitelistEnabled());
        plugin.saveConfig();
        plugin.getPluginConfig().reloadConfiguration();
        return true;
    }
}
