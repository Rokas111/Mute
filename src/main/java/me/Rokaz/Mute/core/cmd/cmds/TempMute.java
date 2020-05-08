package me.Rokaz.Mute.core.cmd.cmds;


import me.Rokaz.Mute.lib.cmd.Command;
import me.Rokaz.Mute.core.Mute;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TempMute extends Command {
    public TempMute() {
        super("Mute.Tempmute", "Temporarily mutes a player", Arrays.asList("<Player> <Seconds>"), true, "tempmute");
    }
    public void runConsole(String enteredCmd, String[] args) {
        Mute.pl.getDataConfig().storeMute(Bukkit.getOfflinePlayer(args[0]),Integer.parseInt(args[1]));
        if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
            Player op = Bukkit.getPlayer(args[0]);
            op.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "You were muted");
            op.playSound(op.getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND,1.0F,1.0F);
        }
    }
    public void run(Player p, String enteredCmd, String[] args) {
        /*if (Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {

            return;
        }*/
        Mute.pl.getDataConfig().storeMute(Bukkit.getOfflinePlayer(args[0]),Integer.parseInt(args[1]));
        p.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "You successfully muted " + ChatColor.RESET + "" + ChatColor.YELLOW + args[0]);
        p.playSound(p.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1.0F,1.0F);
        if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
            Player op = Bukkit.getPlayer(args[0]);
            op.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "You were muted by " + p.getName());
            op.playSound(op.getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND,1.0F,1.0F);
        }
    }
}
