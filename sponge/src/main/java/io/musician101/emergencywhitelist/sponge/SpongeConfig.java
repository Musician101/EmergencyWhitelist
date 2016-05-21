package io.musician101.emergencywhitelist.sponge;

import io.musician101.emergencywhitelist.common.EWLConfig;
import io.musician101.emergencywhitelist.common.Reference;
import io.musician101.emergencywhitelist.common.Reference.Config;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.Sponge;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class SpongeConfig extends EWLConfig
{
    public SpongeConfig()
    {
        //noinspection OptionalGetWithoutIsPresent
        super(new File("config", Sponge.getPluginManager().getPlugin(Reference.ID).get().getUnqualifiedId() + ".conf"));
        reload();
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
        catch (IOException e)//NOSONAR
        {
            SpongeEmergencyWhitelist.getLogger().error(Messages.fileCreateFailed(configFile));
        }

        ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(configFile).build();
        ConfigurationNode config;
        try
        {
            config = loader.load();
        }
        catch (IOException e)//NOSONAR
        {
            SpongeEmergencyWhitelist.getLogger().error(Messages.fileLoadFailed(configFile));
            return;
        }

        whitelistEnabled = config.getNode(Config.ENABLED).getBoolean(false);
    }
}
