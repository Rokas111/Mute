package me.Rokaz.Mute.core.cmd;

import lombok.NoArgsConstructor;
import me.Rokaz.Mute.lib.cmd.Command;
import me.Rokaz.Mute.lib.cmd.ICommand;
import me.Rokaz.Mute.core.Mute;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CommandManager {
    private final List<Command> commands = new ArrayList<>();
    public void runCommand(Player p, ICommand cmd, String enteredCmd, String[] args) {
        if (!p.getPlayer().hasPermission(cmd.getPermission())) {
            p.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "You don't have the permission to execute this command");
            p.playSound(p.getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND,1.0F,1.0F);
            return;
        }
        if (cmd.getArgs().stream().noneMatch(arg -> args.length >= (arg.contains(" ")?arg.split(" ").length:(arg.isEmpty()?0:1)))) {
            p.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Invalid command usage. /"+ enteredCmd + " " + cmd.getArgs().stream().filter(arg -> !arg.isEmpty()).collect(Collectors.joining(" or ")));
            p.playSound(p.getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND,1.0F,1.0F);
            return;
        }
        cmd.run(p,enteredCmd, args);
    }
    public void runCommand(ICommand cmd, String enteredCmd, String[] args) {
        if (cmd.getArgs().stream().noneMatch(arg -> args.length >= (arg.contains(" ")?arg.split(" ").length:(arg.isEmpty()?0:1)))) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Invalid command usage. " + cmd.getArgs().stream().filter(arg -> !arg.isEmpty()).collect(Collectors.joining(" or ")));
            return;
        }
        cmd.runConsole(enteredCmd, args);
    }
    public void registerCommand(Command cmd) {
        commands.add(cmd);
        try {
            final Field f = Mute.pl.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            ((CommandMap) f.get(Mute.pl.getServer())).register(cmd.getCmdAliases().get(0),cmd);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public List<Command> getCommands() {
        return Collections.unmodifiableList(this.commands);
    }
}
