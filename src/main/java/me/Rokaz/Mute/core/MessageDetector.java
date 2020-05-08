package me.Rokaz.Mute.core;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageDetector implements Listener {
    public MessageDetector() {
        Mute.pl.getServer().getPluginManager().registerEvents(this,Mute.pl);
    }
    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        if (Mute.pl.getDataConfig().isMuted(e.getPlayer())) e.setCancelled(true);
    }
}
