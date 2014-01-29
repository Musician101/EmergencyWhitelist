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

/**
 * Runs when a player logs onto the server.
 * 
 * @author Musician101
 */
public class EWLListener implements Listener
{
	EmergencyWhitelist plugin;
	Config config;
	
	/**
	 * @param plugin References the plugin's main class.
	 */
	public EWLListener(EmergencyWhitelist plugin, Config config)
	{
		this.plugin = plugin;
		this.config = config;
	}
	
	/**
	 * Checks the player's permissions and disallows them from joining the server if the whitelist is enabled and they lack the permission to join.
	 * 
	 * @param event All the information for the event.
	 */
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
