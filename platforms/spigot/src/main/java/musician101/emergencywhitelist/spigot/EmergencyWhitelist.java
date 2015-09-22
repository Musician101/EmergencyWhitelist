package musician101.emergencywhitelist.spigot;

import musician101.emergencywhitelist.spigot.command.AbstractSpigotCommand;
import musician101.emergencywhitelist.spigot.command.ewl.EWLCommand;
import musician101.emergencywhitelist.spigot.listener.EWLListener;
import musician101.emergencywhitelist.spigot.util.KickPlayers;
import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateResult;
import net.gravitydevelopment.updater.Updater.UpdateType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class EmergencyWhitelist extends JavaPlugin
{
	private SpigotConfig config;
    private List<AbstractSpigotCommand> commands;

    private void versionCheck()
	{
		if (config.isUpdateCheckEnabled())
        {
            Updater updater = new Updater(this, 46809, this.getFile(), UpdateType.NO_DOWNLOAD, true);
            if (updater.getResult() == UpdateResult.NO_UPDATE)
                getLogger().info("There are no new versions available.");
            else if (updater.getResult() == UpdateResult.FAIL_DBO)
                getLogger().info("Failed to connect to CurseAPI.");
            else if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE)
            {
                getLogger().info("An update is available!");
                getLogger().info(updater.getLatestName() + " " + updater.getLatestType() + " " + updater.getLatestGameVersion() + " " + updater.getLatestFileLink());
            }
            else
                getLogger().info("An error occurred while attempting to check for updates");
		}
		else
			getLogger().info("Update is disabled");
	}

	@Override
	public void onEnable()
	{
		config = new SpigotConfig(this);
	
		getServer().getPluginManager().registerEvents(new EWLListener(this), this);
        commands = Collections.singletonList(new EWLCommand(this));

        KickPlayers.kickPlayers(this, config.isWhitelistEnabled());
		
		versionCheck();
	}

	@Override
	public void onDisable()
	{
		getLogger().info("Shutting down.");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		for (AbstractSpigotCommand cmd : commands)
            if (command.getName().equalsIgnoreCase(cmd.getName()))
                return cmd.onCommand(sender, args);

		return false;
	}

    public SpigotConfig getPluginConfig()
    {
        return config;
    }

	public List<AbstractSpigotCommand> getCommands()
    {
        return commands;
    }
}
