package musician101.emergencywhitelist.listeners;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Runs when a player logs onto the server.
 * 
 * @author Musician101
 */
public class EWLListener implements Listener
{
	EmergencyWhitelist plugin;
	/**
	 * @param plugin References the plugin's main class.
	 */
	public EWLListener(EmergencyWhitelist plugin)
	{
		this.plugin = plugin;
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
		if (plugin.getConfig().getBoolean("enabled"))
		{
			if (!player.hasPermission(Constants.PERMISSION_WHITELIST))
			{
				event.disallow(Result.KICK_WHITELIST, Constants.WHITELIST_ENABLED);
				plugin.getLogger().info(Constants.getDisconnectedPlayer(player));
			}
		}
	}
}
