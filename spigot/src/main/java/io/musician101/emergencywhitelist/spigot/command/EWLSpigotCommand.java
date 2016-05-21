package io.musician101.emergencywhitelist.spigot.command;

import io.musician101.common.java.minecraft.spigot.command.AbstractSpigotCommand;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandArgument;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandPermissions;
import io.musician101.common.java.minecraft.spigot.command.SpigotCommandUsage;
import io.musician101.common.java.minecraft.spigot.command.SpigotHelpCommand;
import io.musician101.emergencywhitelist.common.Reference;
import io.musician101.emergencywhitelist.common.Reference.Commands;
import io.musician101.emergencywhitelist.spigot.SpigotEmergencyWhitelist;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Collections;

public class EWLSpigotCommand extends AbstractSpigotCommand
{
    public EWLSpigotCommand()
    {
        super(Reference.ID, Commands.EWL_DESC, new SpigotCommandUsage(Collections.singletonList(new SpigotCommandArgument(Commands.EWL_CMD))), new SpigotCommandPermissions("", false, "", ""), Arrays.asList(new ReloadSpigotCommand(), new ToggleSpigotCommand()));
    }

    @Override
    public boolean onCommand(CommandSender sender, String... args)
    {
        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase(Commands.HELP))
                return new SpigotHelpCommand<>(SpigotEmergencyWhitelist.instance(), this).onCommand(sender, moveArguments(args));

            for (AbstractSpigotCommand command : getSubCommands())
                if (command.getName().equalsIgnoreCase(args[0]))
                    return command.onCommand(sender, args);
        }

        return new SpigotHelpCommand<>(SpigotEmergencyWhitelist.instance(), this).onCommand(sender, moveArguments(args));
    }
}
