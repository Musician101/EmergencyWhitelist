package musician101.emergencywhitelist.sponge;

import musician101.common.java.minecraft.sponge.config.AbstractSpongeConfig;
import musician101.emergencywhitelist.common.IEWLConfig;
import musician101.emergencywhitelist.common.Reference;
import musician101.emergencywhitelist.common.Reference.Config;
import musician101.emergencywhitelist.common.Reference.Messages;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class SpongeConfig extends AbstractSpongeConfig implements IEWLConfig
{
    private boolean whitelistEnabled;

    public SpongeConfig()
    {
        super(new File("config", Reference.ID + ".conf"), SpongeEmergencyWhitelist.logger);
        reload();
    }

    @Override
    public boolean isWhitelistEnabled()
    {
        return whitelistEnabled;
    }

    @Override
    public void setWhitelistEnabled(boolean whitelistEnabled)
    {
        this.whitelistEnabled = whitelistEnabled;
    }
    
    @Override
    public void reload()
    {
        try
        {
            if (!configFile.exists())
            {
                if (!configFile.createNewFile())
                    throw new IOException();

                URL url = SpongeEmergencyWhitelist.class.getResource(configFile.getName());
                if (url == null)
                    throw new IOException();

                URLConnection connection = url.openConnection();
                connection.setUseCaches(false);
                InputStream input = connection.getInputStream();
                OutputStream output = new FileOutputStream(configFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = input.read(buf)) > 0)
                    output.write(buf, 0, len);

                output.close();
                input.close();
            }
        }
        catch (IOException e)
        {
            logger.error(Messages.fileCreateFailed(configFile));
        }

        ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(configFile).build();
        ConfigurationNode config;
        try
        {
            config = loader.load();
        }
        catch (IOException e)
        {
            logger.error(Messages.fileLoadFailed(configFile));
            return;
        }

        whitelistEnabled = config.getNode(Config.ENABLED).getBoolean(false);
    }
}
