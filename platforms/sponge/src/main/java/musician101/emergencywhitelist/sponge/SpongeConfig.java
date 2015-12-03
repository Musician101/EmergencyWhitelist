package musician101.emergencywhitelist.sponge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import musician101.common.java.minecraft.sponge.config.SpongeJSONConfig;
import musician101.emergencywhitelist.common.AbstractConfig;
import musician101.emergencywhitelist.common.Reference;
import musician101.emergencywhitelist.common.Reference.Config;
import musician101.emergencywhitelist.common.Reference.Messages;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.service.config.ConfigRoot;
import org.spongepowered.api.service.config.ConfigService;

public class SpongeConfig extends AbstractConfig
{
    File configFile;
    SpongeJSONConfig config;

    public SpongeConfig()
    {
        super();
        Game game = SpongeEmergencyWhitelist.game;
        ConfigRoot cr = game.getServiceManager().provide(ConfigService.class).get().getSharedConfig(game.getPluginManager().getPlugin(Reference.ID).get());
        configFile = new File(cr.getDirectory().toFile(), Config.EWL_FILE);
        reloadConfiguration();
    }

    @Override
    public void setWhitelistEnabled(boolean enabled)
    {
        super.setWhitelistEnabled(enabled);
        config.set(Config.ENABLED, enabled);
        try
        {
            saveConfigFile();
        }
        catch (IOException e)
        {
            SpongeEmergencyWhitelist.logger.error(Messages.CONFIG_FAIL_SAVE);
            SpongeEmergencyWhitelist.logger.error(Messages.WHITELIST_MANUAL_UPDATE);
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
                logger.warn(Messages.CONFIG_FAIL_CREATE);
                return;
            }

            config = SpongeJSONConfig.load(configFile);
            setWhitelistEnabled(config.getBoolean(Config.ENABLED, true));
        }
        catch (IOException | ParseException e)
        {
            logger.error(Messages.CONFIG_FAIL_LOAD);
            setWhitelistEnabled(true);
        }
    }

    private void saveConfigFile() throws IOException
    {
        FileWriter fw = new FileWriter(configFile);
        fw.write(config.toJSONString());
        fw.close();
    }
}
