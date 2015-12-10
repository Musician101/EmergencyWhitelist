package musician101.emergencywhitelist.forge;

import java.util.Collections;
import java.util.List;
import musician101.common.java.minecraft.forge.command.AbstractForgeCommand;
import musician101.emergencywhitelist.common.Reference;
import musician101.emergencywhitelist.forge.command.EWLCommand;
import musician101.emergencywhitelist.forge.listener.EWLListener;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VERSION, serverSideOnly = true)
public class ForgeEmergencyWhitelist
{
    public static final Logger logger = LogManager.getLogger(Reference.NAME);
    @Instance(value = Reference.ID)
    public static ForgeEmergencyWhitelist instance;
    public static ForgeConfig config;
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
