package io.musician101.emergencywhitelist.common;

import java.io.IOException;

public abstract class EWLConfig {

    protected boolean whitelistEnabled = true;

    public boolean isWhitelistEnabled() {
        return whitelistEnabled;
    }

    public void setWhitelistEnabled(boolean whitelistEnabled) {
        this.whitelistEnabled = whitelistEnabled;
    }

    public abstract void load() throws IOException;

    public abstract void save();
}
