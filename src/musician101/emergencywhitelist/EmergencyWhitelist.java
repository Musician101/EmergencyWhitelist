package musician101.emergencywhitelist;

import java.io.File;

import musician101.emergencywhitelist.commands.EWLCommandExecutor;
import musician101.emergencywhitelist.listeners.EWLListener;
import musician101.emergencywhitelist.util.RunKickMethod;
import musician101.emergencywhitelist.util.Update;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The plugin's main class.
 * 
 * @author Musician101
 */
public class EmergencyWhitelist extends JavaPlugin
{
	File configFile;
	FileConfiguration config;
	
	/** Checks if a new version is available. */
	public void versionCheck()
	{
		@SuppressWarnings("unused")
		Update update = null;
		if (config.getBoolean("checkForUpdate"))
			update = new Update(46809, "72784c134bdbc3c2216591011a29df99fac08239");
		else
			getLogger().info("Update is disabled");
	}
	
	/** Initializes the plugin, checks for the config, and register commands and listeners. */
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new EWLListener(this), this);
		
		getCommand("ewl").setExecutor(new EWLCommandExecutor(this));
		
		configFile = new File(getDataFolder(), "config.yml");
		saveDefaultConfig();
		config = new YamlConfiguration();
		try
		{
			config.load(configFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		new RunKickMethod(this, this.getConfig().getBoolean("enabled"));
		
		/** Check for a new version if it's enabled. */
		versionCheck();
	}
	
	/** Shuts off the plugin. */
	public void onDisable()
	{
		getLogger().info("Shutting down.");
	}
}
