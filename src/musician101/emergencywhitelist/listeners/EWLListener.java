package musician101.emergencywhitelist.listeners;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Constants;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerLoginEvent;


public class EWLListener implements Listener
{	
	
	EmergencyWhitelist plugin;
	public EWLListener(EmergencyWhitelist plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event)
	{
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("enabled"))
		{
			if (!player.hasPermission(Constants.PERMISSION_WHITELIST))
			{
				event.disallow(Result.KICK_WHITELIST, Constants.WHITELIST_ENABLED);
				plugin.logger().info(Constants.getDisconnectedPlayer(player));
			}
		}
	}
}
