package io.musician101.emergencywhitelist.sponge;

import io.musician101.emergencywhitelist.common.EWLConfig;
import io.musician101.emergencywhitelist.common.Reference.Config;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import java.io.File;
import java.io.IOException;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class SpongeConfig extends EWLConfig {

    public SpongeConfig(File configFile) {
        super(configFile);
        reload();
    }

    private ConfigurationLoader<CommentedConfigurationNode> getConfigurationLoader() {
        return HoconConfigurationLoader.builder().setFile(configFile).build();
    }

    @Override
    public void reload() {
        SpongeEmergencyWhitelist.instance().ifPresent(plugin -> {
            Logger logger = plugin.getLogger();
            try {
                if (!configFile.exists()) {
                    configFile.createNewFile();
                    ConfigurationLoader<CommentedConfigurationNode> loader = getConfigurationLoader();
                    ConfigurationNode config = SimpleCommentedConfigurationNode.root();
                    config.getNode(Config.ENABLED).setValue(true);
                    loader.save(config);
                }

                ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(configFile).build();
                ConfigurationNode config = loader.load();
                whitelistEnabled = config.getNode(Config.ENABLED).getBoolean(false);
                if (whitelistEnabled) {
                    Task.builder().delayTicks(100L).execute(() -> Sponge.getServer().getOnlinePlayers().stream().filter(player -> !player.hasPermission(Permissions.WHITELIST)).forEach(player -> player.kick(Text.of(Messages.WHITELIST_ENABLED)))).submit(SpongeEmergencyWhitelist.instance());
                }

                logger.info(Messages.whitelistLog(whitelistEnabled));
                logger.info(Messages.WHITELIST_LOG);
                Sponge.getServer().getBroadcastChannel().send(Text.builder(Messages.whitelistBroadcast(whitelistEnabled)).color(TextColors.GOLD).build());
            }
            catch (IOException e) {
                logger.error(Messages.fileLoadFailed(configFile));
            }
        });
    }

    public void save() {
        SpongeEmergencyWhitelist.instance().ifPresent(plugin -> {
            try {
                ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(configFile).build();
                ConfigurationNode config = SimpleCommentedConfigurationNode.root();
                config.getNode(Config.ENABLED).setValue(whitelistEnabled);
                loader.save(config);
                reload();
            }
            catch (IOException e) {
                plugin.getLogger().error(Messages.fileCreateFailed(configFile));
            }
        });
    }
}
