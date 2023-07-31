package me.ilsommo.authmeautoconnect;

import me.ilsommo.authmeautoconnect.config.Config;
import me.ilsommo.authmeautoconnect.listeners.LobbyConnector;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AuthmeAutoConnect extends JavaPlugin {
    private Config config;

    @Override
    public void onEnable() {
        super.onEnable();
        config = new Config(this);
        registerListener(new LobbyConnector(this));
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0){
            if (args[0].equalsIgnoreCase("reload")){
                config.loadConfig(this);
                sender.sendMessage(ChatColor.GREEN + "Authme-AutoConnect config reloaded!");
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid argument.");
            }
        } else {
            sender.sendMessage(ChatColor.GREEN + "Authme-AutoConnect Reloaded v1.0.0");
        }
        return true;
    }

    private void registerListener(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }


    public Config getConfiguration() {
        return config;
    }
}
