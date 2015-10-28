package musician101.emergencywhitelist.sponge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import musician101.common.java.minecraft.sponge.config.SpongeJSONConfig;
import musician101.emergencywhitelist.common.AbstractConfig;
import musician101.emergencywhitelist.sponge.lib.Reference;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.GameProfile;
import org.spongepowered.api.service.config.ConfigRoot;
import org.spongepowered.api.service.config.ConfigService;

public class SpongeConfig extends AbstractConfig
{
    File configFile;
    List<GameProfile> gameProfiles = new ArrayList<>();
    SpongeJSONConfig config;

    public SpongeConfig()
    {
        super();
        Game game = SpongeEmergencyWhitelist.game;
        ConfigRoot cr = game.getServiceManager().provide(ConfigService.class).get().getSharedConfig(game.getPluginManager().getPlugin(Reference.ID).get());
        configFile = new File(cr.getDirectory(), "emergency_whitelist.json");
        reloadConfiguration();
    }

    @Override
    public void setWhitelistEnabled(boolean enabled)
    {
        super.setWhitelistEnabled(enabled);
        config.set("enabled", enabled);
        try
        {
            saveConfigFile();
        }
        catch (IOException e)
        {
            SpongeEmergencyWhitelist.logger.error("Could not write to emergency_whitelist.json.");
            SpongeEmergencyWhitelist.logger.error("The 'enabled' option updated in memory but not in the file.");
        }
    }
    
    @Override
    public void reloadConfiguration()
    {
        Logger logger = SpongeEmergencyWhitelist.logger;
        try
        {
            if (!configFile.exists() && !configFile.createNewFile())
            {
                logger.warn("Could not create emergency_whitelist.json");
                return;
            }

            config = SpongeJSONConfig.load(configFile);
            setWhitelistEnabled(config.getBoolean("enabled", true));
            config.getMapList("whitelist").forEach(map -> gameProfiles.add(SpongeEmergencyWhitelist.game.getRegistry().createGameProfile(UUID.fromString(map.get("uuid").toString()), map.get("name").toString())));
        }
        catch (IOException | ParseException e)
        {
            logger.error("Could not load emergency_whitelist.json");
            setWhitelistEnabled(true);
        }
    }

    public boolean isWhitelisted(GameProfile gp)
    {
        for (GameProfile gameProfile : gameProfiles)
            if (gp.getUniqueId() == gameProfile.getUniqueId())
                return true;

        return false;
    }

    public void updatePlayer(GameProfile profile)
    {
        int index = -1;
        for (int x = 0; x > gameProfiles.size() - 1; x++)
            if (gameProfiles.get(x).getUniqueId() == profile.getUniqueId())
                index = x;

        if (index == -1)
            return;

        gameProfiles.remove(index);
        gameProfiles.add(profile);

        List<Map<String, Object>> list = new ArrayList<>();
        gameProfiles.forEach(gp -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", gp.getName());
            map.put("uuid", gp.getUniqueId().toString());
            list.add(map);
        });
        config.set("whitelist", list);

        try
        {
            saveConfigFile();
        }
        catch (IOException e)
        {
            SpongeEmergencyWhitelist.logger.error("Could not write to emergency_whitelist.json.");
            SpongeEmergencyWhitelist.logger.error("The 'whitelist' option updated in memory but not in the file.");
        }
    }

    private void saveConfigFile() throws IOException
    {
        FileWriter fw = new FileWriter(configFile);
        fw.write(config.toJSONString());
        fw.close();
    }
}
