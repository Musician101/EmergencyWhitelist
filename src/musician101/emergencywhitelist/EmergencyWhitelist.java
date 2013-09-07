package musician101.emergencywhitelist;

import java.io.File;
import java.util.logging.Logger;

import musician101.emergencywhitelist.commands.EWLCommandExecutor;
import musician101.emergencywhitelist.listeners.EWLListener;
import musician101.emergencywhitelist.util.RunKickMethod;
import musician101.emergencywhitelist.util.UpdateChecker;

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
	protected UpdateChecker updateChecker;
	File configFile;
	FileConfiguration config;
	
	/**
	 * Sets up the logger.
	 * 
	 * @return JavaPlugin.getLogger()
	 */
	public Logger logger()
	{
		return getLogger();
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
		if (config.getBoolean("checkForUpdate"))
		{
			this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/bukkit-plugins/emergencywhitelist/files.rss");
			getLogger().info("Update checkers is enabled.");
			if (this.updateChecker.updateNeeded())
			{
				getLogger().info("A new version is available: " + this.updateChecker.getVersion());
				getLogger().info("Get it from: " + this.updateChecker.getLink());
			}
			else
				getLogger().info("EWL is up to date.");
		}
		else if (!config.getBoolean("checkForUpdate"))
			getLogger().info("Update checker is disabled.");
	}
	
	/** Shuts off the plugin. */
	public void onDisable()
	{
		logger().info("Shutting down.");
	}
}
