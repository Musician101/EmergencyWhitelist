package io.musician101.emergencywhitelist.common;

import io.musician101.common.java.minecraft.AbstractConfig;

import java.io.File;

public abstract class EWLConfig extends AbstractConfig
{
    protected boolean whitelistEnabled = true;

    protected EWLConfig(File file)
    {
        super(file);
    }

    public boolean isWhitelistEnabled()
    {
        return whitelistEnabled;
    }

    public void setWhitelistEnabled(boolean whitelistEnabled)
    {
        this.whitelistEnabled = whitelistEnabled;
    }
}
