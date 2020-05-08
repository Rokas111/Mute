package me.Rokaz.Mute.lib.cmd;

import lombok.Getter;
import me.Rokaz.Mute.core.Mute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Command extends org.bukkit.command.Command implements ICommand {
    @Getter private String permission;
    @Getter private String description;
    private List<String> args;
    private List<String> cmdAliases;
    private boolean allowConsole;
    public Command(String permission,String description,List<String> args,boolean allowConsole,String... cmdAliases) {
        super(cmdAliases[0]);
        this.permission = permission;
        this.description = description;
        this.args = args;
        this.allowConsole = allowConsole;
        this.cmdAliases = Arrays.asList(cmdAliases);
        super.description = description;
    }
    public boolean getAllowConsole() {
        return this.allowConsole;
    }
    @Override
    public boolean execute(CommandSender s, String alias, String[] args) {
        if (s instanceof Player) Mute.pl.getCommandManager().runCommand((Player) s,this,alias,args); else Mute.pl.getCommandManager().runCommand(this,alias,args);
        return true;
    }
    public List<String> getArgs() {
        return Collections.unmodifiableList(args);
    }
    public List<String> getCmdAliases() {
        return Collections.unmodifiableList(cmdAliases);
    }
}
