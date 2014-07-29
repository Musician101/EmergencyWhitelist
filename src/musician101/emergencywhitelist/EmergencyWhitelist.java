package musician101.emergencywhitelist;

import musician101.emergencywhitelist.commands.EWLCommandExecutor;
import musician101.emergencywhitelist.listeners.EWLListener;
import musician101.emergencywhitelist.util.RunKickMethod;
import musician101.emergencywhitelist.util.Update;

import org.bukkit.plugin.java.JavaPlugin;

public class EmergencyWhitelist extends JavaPlugin
{
	public Config config;
	//TODO update version checker class
	public void versionCheck()
	{
		if (config.updateCheck)
		{
			@SuppressWarnings("unused")
			Update update = new Update(46809, "72784c134bdbc3c2216591011a29df99fac08239");
		}
		else
			getLogger().info("Update is disabled");
	}
	
	public void onEnable()
	{
		config = new Config(this);
	
		getServer().getPluginManager().registerEvents(new EWLListener(this), this);
		getCommand("ewl").setExecutor(new EWLCommandExecutor(this));
		
		new RunKickMethod(this, config.enabled);
		
		versionCheck();
	}
	
	public void onDisable()
	{
		getLogger().info("Shutting down.");
	}
}
