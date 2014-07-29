package musician101.emergencywhitelist.runnables;

import musician101.emergencywhitelist.EmergencyWhitelist;
import musician101.emergencywhitelist.lib.Commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KickPlayers implements Runnable
{
	EmergencyWhitelist plugin;
	
	public KickPlayers(EmergencyWhitelist plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public void run()
	{
		for (Player player : Bukkit.getOnlinePlayers())
		{
			if (!player.hasPermission(Commands.WHITELIST_PERM))
				player.kickPlayer("Server whitelist has been enabled.");
		}
	}
	
}
