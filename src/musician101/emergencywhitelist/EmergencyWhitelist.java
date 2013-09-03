package musician101.emergencywhitelist;

import java.io.File;
import java.util.logging.Logger;

import musician101.emergencywhitelist.commands.EWLCommandExecutor;
import musician101.emergencywhitelist.listeners.EWLListener;
import musician101.emergencywhitelist.util.RunKickMethod;

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
	
	/**
	 * Set up for a static 'plugin' reference.
	 */
	public static EmergencyWhitelist plugin;
	public EmergencyWhitelist()
	{
		super();
		plugin = this;
	}
	
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
	}
	
	/** Shuts off the plugin. */
	public void onDisable()
	{
		logger().info("Shutting down.");
	}
}
