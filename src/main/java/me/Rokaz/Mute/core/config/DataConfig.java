package me.Rokaz.Mute.core.config;

import me.Rokaz.Mute.lib.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;

import java.util.UUID;

public class DataConfig extends Config {
    public DataConfig() {
        super("data");
    }
    public void update() {
        getYaml().getKeys(false).forEach(player -> {
            if (getYaml().getInt(player) == 1) {
                unmute(Bukkit.getOfflinePlayer(UUID.fromString(player)));
            } else {
                getYaml().set(player,getYaml().getInt(player) - 1);
                save();
            }
        });
        save();
    }
    public void storeMute(OfflinePlayer p,int seconds) {
            getYaml().set(p.getUniqueId().toString(),seconds);
            save();
    }
    public boolean isMuted(OfflinePlayer p) {
        return getYaml().contains(p.getUniqueId().toString());
    }
    public void unmute(OfflinePlayer p) {
        if (isMuted(p)) {
            getYaml().set(p.getUniqueId().toString(),null);
            save();
            if (p.isOnline()) {
                p.getPlayer().sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "You were unmuted");
                p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1.0F,1.0F);
            }
        }
    }
}
