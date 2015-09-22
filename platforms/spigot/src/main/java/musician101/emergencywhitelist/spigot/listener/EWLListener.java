package musician101.emergencywhitelist.spigot.listener;

import musician101.emergencywhitelist.spigot.EmergencyWhitelist;
import musician101.emergencywhitelist.spigot.lib.Messages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class EWLListener implements Listener
{
	private final EmergencyWhitelist plugin;

	public EWLListener(EmergencyWhitelist plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event)
	{
		Player player = event.getPlayer();
		if (plugin.getPluginConfig().isWhitelistEnabled())
		{
			if (!player.hasPermission(Messages.WHITELIST_PERM))
			{
				event.disallow(Result.KICK_WHITELIST, "EmergencyWhitelist has been enabled.");
				plugin.getLogger().info(player.getName() + " attempted to connect.");
			}
		}
	}
}
