package musician101.emergencywhitelist.forge;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import musician101.emergencywhitelist.common.AbstractConfig;
import musician101.emergencywhitelist.forge.lib.ModInfo;
import musician101.emergencywhitelist.forge.util.EWLGameProfileList;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;

import java.io.File;

public class ForgeConfig extends AbstractConfig
{
    public File configDir;
    public static Configuration config;
    List<UUID> uuids = new ArrayList<>();

    public ForgeConfig()
    {
        super();
    }

    public void init(File configDir)
    {
        this.configDir = configDir;
        configDir.mkdirs();
        if (config == null)
        {
            config = new Configuration(new File(configDir, ModInfo.ID + ".cfg"));
            reloadConfiguration();
        }

        try
        {
            File whitelistFile = new File(configDir, "whitelist.json");
            BufferedReader r = new BufferedReader(new FileReader(whitelistFile));
            Gson gson = new Gson();
            EWLGameProfileList list = gson.fromJson(r.readLine(), EWLGameProfileList.class);
            list.getList().forEach(profile -> uuids.add(profile.getUUID()));
        }
        catch (IOException e)
        {
            EmergencyWhitelist.logger.warn("An error occurred while loading the whitelist.");
        }
    }

    @Override
    public void reloadConfiguration()
    {
        config.load();
        setWhitelistEnabled(config.getBoolean("enabled", Configuration.CATEGORY_GENERAL, false, "Enable/Disable the whitelist."));
        if (config.hasChanged())
            config.save();
    }

    @EventHandler
    public void onConfigChange(OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(ModInfo.ID))
            reloadConfiguration();
    }

    public boolean hasPermission(UUID playerId)
    {
        return uuids.contains(playerId);
    }

    public File getConfigDir()
    {
        return configDir;
    }
}
