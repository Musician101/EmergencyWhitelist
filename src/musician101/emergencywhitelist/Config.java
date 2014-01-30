package musician101.emergencywhitelist;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public class Config
{
	EmergencyWhitelist plugin;
	public boolean enabled;
	public boolean updateCheck;
	
	/**
	 * Config constructor.
	 * 
	 * @param plugin Reference's the plugin's main class.
	 */
	public Config(EmergencyWhitelist plugin)
	{
		this.plugin = plugin;
		File config = new File(plugin.getDataFolder(), "config.yml");
		if (!config.exists())
		{
			if (!config.getParentFile().mkdirs()) plugin.getLogger().warning("Could not create config.yml!");
			plugin.saveDefaultConfig();
		}
		reloadConfiguration();
	}
	
	/** Reloads the server's config file. */
	public void reloadConfiguration()
	{
		plugin.reloadConfig();
		final FileConfiguration config = plugin.getConfig();
		config.getBoolean("enabled", true);
		config.getBoolean("checkForUpdate", true);
	}
}
