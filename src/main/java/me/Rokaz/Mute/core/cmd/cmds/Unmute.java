package me.Rokaz.Mute.core.cmd.cmds;

import me.Rokaz.Mute.lib.cmd.Command;
import me.Rokaz.Mute.core.Mute;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Unmute extends Command {
    public Unmute() {
        super("Mute.unmute", "Unmutes a player", Arrays.asList("<Player>"), true, "unmute");
    }
    public void runConsole(String enteredCmd, String[] args) {
        if (!Mute.pl.getDataConfig().isMuted(Bukkit.getOfflinePlayer(args[0]))) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "The player isn't muted");
            return;
        }
        Mute.pl.getDataConfig().unmute(Bukkit.getOfflinePlayer(args[0]));
    }
    public void run(Player p, String enteredCmd, String[] args) {
        if (!Mute.pl.getDataConfig().isMuted(Bukkit.getOfflinePlayer(args[0]))) {
            p.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "The player isn't muted");
            p.playSound(p.getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND,1.0F,1.0F);
            return;
        }
        Mute.pl.getDataConfig().unmute(Bukkit.getOfflinePlayer(args[0]));
        p.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "You successfully unmuted " + ChatColor.RESET + "" + ChatColor.YELLOW + args[0]);
        p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1.0F,1.0F);
    }
}
