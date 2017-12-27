package me.martiii.authmeautoconnect.config;

import me.martiii.authmeautoconnect.AuthmeAutoConnect;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    public String lobbyServer;
    public List<String> commands;
    public long sendDelay;

    public Config(AuthmeAutoConnect plugin){
        plugin.saveDefaultConfig();
        loadConfig(plugin);
    }

    public void loadConfig(AuthmeAutoConnect plugin){
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        lobbyServer = config.getString("lobby-server");
        commands = config.getStringList("commands");
        sendDelay = config.getLong("send-delay");
    }
}
