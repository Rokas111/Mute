package me.Rokaz.Mute.core;

import lombok.Getter;
import me.Rokaz.Mute.core.cmd.CommandManager;
import me.Rokaz.Mute.core.config.DataConfig;
import me.Rokaz.Mute.core.cmd.cmds.TempMute;
import me.Rokaz.Mute.core.cmd.cmds.Unmute;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Mute extends JavaPlugin {
    public static final String PLUGIN_FOLDER = "Mute";
    public static Mute pl;
    @Getter private CommandManager commandManager;
    @Getter private DataConfig dataConfig;
    private MessageDetector messageDetector;
    public void onLoad() {
        File directory = new File("plugins//" + PLUGIN_FOLDER);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    public void onEnable() {
        pl = this;
        commandManager = new CommandManager();
        messageDetector = new MessageDetector();
        registerConfigs();
        registerCommands();
        scheduleMute();
    }
    public void onDisable() {
        pl = null;
    }
    private void registerConfigs() {
        this.dataConfig = new DataConfig();
    }
    private void registerCommands() {
        commandManager.registerCommand(new Unmute());
        commandManager.registerCommand(new TempMute());
    }
    private void scheduleMute() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            dataConfig.update();
        },10,20);
    }

}
