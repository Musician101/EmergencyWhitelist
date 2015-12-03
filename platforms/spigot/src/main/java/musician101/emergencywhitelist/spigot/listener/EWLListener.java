package musician101.emergencywhitelist.spigot.listener;

import musician101.emergencywhitelist.common.Reference.Messages;
import musician101.emergencywhitelist.common.Reference.Permissions;
import musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class EWLListener implements Listener
{
    private final SpigotEmergencyWhitelist plugin;

    public EWLListener(SpigotEmergencyWhitelist plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        if (plugin.getPluginConfig().isWhitelistEnabled())
        {
            if (!player.hasPermission(Permissions.WHITELIST))
            {
                event.disallow(Result.KICK_WHITELIST, Messages.WHITELIST_ENABLED);
                plugin.getLogger().info(Messages.playerConnectAttempt(player.getName()));
            }
        }
    }
}
