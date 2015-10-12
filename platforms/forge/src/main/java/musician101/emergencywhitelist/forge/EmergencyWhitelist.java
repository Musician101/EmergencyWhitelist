package musician101.emergencywhitelist.forge;

import musician101.common.java.minecraft.forge.AbstractForgeCommand;
import musician101.emergencywhitelist.forge.command.ewl.EWLCommand;
import musician101.emergencywhitelist.forge.lib.ModInfo;
import musician101.emergencywhitelist.forge.listener.EWLListener;
import musician101.emergencywhitelist.forge.util.KickPlayers;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

@Mod(modid=ModInfo.ID, name=ModInfo.NAME, version=ModInfo.VERSION, serverSideOnly=true)
public class EmergencyWhitelist
{
    @Instance(value=ModInfo.ID)
    public static EmergencyWhitelist instance;

    public static ForgeConfig config;
    public static Logger logger = LogManager.getLogger(ModInfo.NAME);
    public static List<AbstractForgeCommand> commands;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        config = new ForgeConfig();
        config.init(event.getModConfigurationDirectory());

        FMLCommonHandler.instance().bus().register(new EWLListener());
        logger.info("Pre-Init complete.");
    }

    @EventHandler
    public void onServerStart(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new EWLCommand());
        commands = Collections.singletonList(new EWLCommand());
    }
}
