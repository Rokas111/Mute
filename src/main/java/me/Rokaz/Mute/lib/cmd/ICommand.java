package me.Rokaz.Mute.lib.cmd;

import org.bukkit.entity.Player;

import java.util.List;

public interface ICommand {
    List<String> getAliases();
    List<String> getArgs();
    String getPermission();
    String getDescription();
    void runConsole(String enteredCmd, String[] args);
    void run(Player p, String enteredCmd, String[] args);
}
