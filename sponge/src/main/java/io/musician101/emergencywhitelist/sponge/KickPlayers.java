package io.musician101.emergencywhitelist.sponge;

import io.musician101.common.java.minecraft.sponge.TextUtils;
import io.musician101.emergencywhitelist.common.Reference.Messages;
import io.musician101.emergencywhitelist.common.Reference.Permissions;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

public class KickPlayers implements Runnable
{
    private KickPlayers()
    {

    }

    public static void kickPlayers(boolean enabled)
    {
        if (enabled)
            Task.builder().delayTicks(100L).execute(new KickPlayers()).submit(SpongeEmergencyWhitelist.instance());

        SpongeEmergencyWhitelist.getLogger().info(Messages.whitelistLog(enabled));
        SpongeEmergencyWhitelist.getLogger().info(Messages.WHITELIST_LOG);
        Sponge.getServer().getBroadcastChannel().send(TextUtils.goldText(Messages.whitelistBroadcast(enabled)));
    }

    @Override
    public void run()
    {
        Sponge.getServer().getOnlinePlayers().stream().filter(player -> !player.hasPermission(Permissions.WHITELIST)).forEach(player -> player.kick(Text.of(Messages.WHITELIST_ENABLED)));
    }
}
