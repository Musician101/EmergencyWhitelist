package io.musician101.emergencywhitelist.sponge;

import io.musician101.emergencywhitelist.common.EWLConfig;
import java.io.IOException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.ValueReference;

public class SpongeConfig extends EWLConfig {

    private final ConfigurationReference<ConfigurationNode> configReference;
    private ValueReference<DefaultConfig, ConfigurationNode> config;

    public SpongeConfig(ConfigurationReference<ConfigurationNode> configReference) {
        this.configReference = configReference;
    }

    @Override
    public boolean isWhitelistEnabled() {
        return config.get().whitelistEnabled;
    }

    @Override
    public void setWhitelistEnabled(boolean whitelistEnabled) {
        config.get().whitelistEnabled = whitelistEnabled;
    }

    @Override
    public void load() throws IOException {
        config = configReference.referenceTo(DefaultConfig.class);
    }

    public void save() {
        DefaultConfig defaultConfig = config.get();
        defaultConfig.whitelistEnabled = whitelistEnabled;
        config.setAndSave(defaultConfig);
    }

    @ConfigSerializable
    public static class DefaultConfig {

        @Setting
        private boolean whitelistEnabled = true;
    }
}
