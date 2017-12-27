package me.martiii.authmeautoconnect.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.xephi.authme.api.v3.AuthMeApi;
import me.martiii.authmeautoconnect.AuthmeAutoConnect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyConnector implements Listener {
    private AuthmeAutoConnect plugin;

    public LobbyConnector(AuthmeAutoConnect plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreCmd(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String msg = event.getMessage().substring(1);
        if (plugin.getConfiguration().commands.contains(msg.split(" ")[0])) {
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (sendIfLoggedIn(player) || count > 10){
                        cancel();
                        return;
                    }
                    count++;
                }
            }.runTaskTimer(plugin, plugin.getConfiguration().sendDelay, 4L);
        }
    }

    private boolean sendIfLoggedIn(Player player) {
        if (AuthMeApi.getInstance().isAuthenticated(player)) {
            sendToLobby(player);
            return true;
        } else {
            return false;
        }
    }

    private void sendToLobby(Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(plugin.getConfiguration().lobbyServer);

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}

