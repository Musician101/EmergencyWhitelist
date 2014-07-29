package musician101.emergencywhitelist.listeners;

import musician101.emergencywhitelist.Config;
import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Commands;
import musician101.emergencywhitelist.lib.Messages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class EWLListener implements Listener
{
	EmergencyWhitelist plugin;
	Config config;
	
	public EWLListener(EmergencyWhitelist plugin, Config config)
	{
		this.plugin = plugin;
		this.config = config;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event)
	{
		Player player = event.getPlayer();
		if (config.enabled)
		{
			if (!player.hasPermission(Commands.WHITELIST_PERM))
			{
				event.disallow(Result.KICK_WHITELIST, Messages.WHITELIST_ENABLED);
				plugin.getLogger().info(Messages.getDisconnectedPlayer(player));
			}
		}
	}
}
