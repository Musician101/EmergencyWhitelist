package musician101.emergencywhitelist;

import java.io.File;
import java.util.logging.Logger;

import musician101.emergencywhitelist.commands.EWLCommandExecutor;
import musician101.emergencywhitelist.listeners.EWLListener;
import musician101.emergencywhitelist.util.RunKickMethod;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class EmergencyWhitelist extends JavaPlugin
{
	
	File configFile;
	FileConfiguration config;
	
	public static EmergencyWhitelist plugin;
	public EmergencyWhitelist()
	{
		super();
		plugin = this;
	}
	
	public Logger logger()
	{
		return getLogger();
	}
	
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
	
	public void onDisable()
	{
		logger().info("Shutting down.");
	}
}
