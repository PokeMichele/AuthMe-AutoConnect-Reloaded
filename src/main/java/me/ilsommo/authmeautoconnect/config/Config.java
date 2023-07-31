package me.ilsommo.authmeautoconnect.config;

import me.ilsommo.authmeautoconnect.AuthmeAutoConnect;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public String lobbyServer;
    public long sendDelay;
    public String titleText;
    public String subtitleText;

    public Config(AuthmeAutoConnect plugin){
        plugin.saveDefaultConfig();
        loadConfig(plugin);
    }

    public void loadConfig(AuthmeAutoConnect plugin){
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        lobbyServer = config.getString("lobby-server");
        sendDelay = config.getLong("send-delay");
        titleText = config.getString("titleText");
        subtitleText = config.getString("subtitleText");
    }
}
