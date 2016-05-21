package io.musician101.emergencywhitelist.common;

@FunctionalInterface
public interface AbstractListener<L>
{
    void onPlayerLogin(L event);
}
