package musician101.emergencywhitelist;

import java.io.File;

import musician101.emergencywhitelist.commands.EWLCommandExecutor;
import musician101.emergencywhitelist.listeners.EWLListener;
import musician101.emergencywhitelist.util.RunKickMethod;
import musician101.emergencywhitelist.util.Update;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The plugin's main class.
 * 
 * @author Musician101
 */
public class EmergencyWhitelist extends JavaPlugin
{
	Config config;
	
	/** Loads the plugin's various configurations and reference files/folders. */
	public void loadConfiguration()
	{
		if (!new File(getDataFolder(), "config.yml").exists()) saveDefaultConfig();
	}
	
	/** Checks if a new version is available. */
	public void versionCheck()
	{
		@SuppressWarnings("unused")
		Update update = null;
		if (config.updateCheck)
			update = new Update(46809, "72784c134bdbc3c2216591011a29df99fac08239");
		else
			getLogger().info("Update is disabled");
	}
	
	/** Initializes the plugin, checks for the config, and register commands and listeners. */
	public void onEnable()
	{
		loadConfiguration();
		config = new Config(this);
	
		getServer().getPluginManager().registerEvents(new EWLListener(this, config), this);
		getCommand("ewl").setExecutor(new EWLCommandExecutor(this, config));
		
		new RunKickMethod(this, config.enabled);
		
		/** Check for a new version if it's enabled. */
		versionCheck();
	}
	
	/** Shuts off the plugin. */
	public void onDisable()
	{
		getLogger().info("Shutting down.");
	}
}
