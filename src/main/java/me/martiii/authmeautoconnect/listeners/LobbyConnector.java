package me.martiii.authmeautoconnect.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.xephi.authme.api.v3.AuthMeApi;
import fr.xephi.authme.api.v3.AuthMePlayer;
import fr.xephi.authme.events.LoginEvent;
import fr.xephi.authme.events.RegisterEvent;
import me.martiii.authmeautoconnect.AuthmeAutoConnect;
import org.bukkit.ChatColor;
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
    public void onAuthMeLogin(LoginEvent event) {
        Player player = event.getPlayer();
        new CountdownTask(player).runTaskTimer(plugin, 0L, 20L);
    }

    @EventHandler
    public void onAuthMeRegister(RegisterEvent event) {
        Player player = event.getPlayer();
        new CountdownTask(player).runTaskTimer(plugin, 0L, 20L);
    }

    private class CountdownTask extends BukkitRunnable {
        private final Player player;
        private int count;

        public CountdownTask(Player player) {
            this.player = player;
            this.count = (int) plugin.getConfiguration().sendDelay;

            // Start the countdown in a separate thread
            new Thread(this).start();
        }

        @Override
        public void run() {
            if (count <= 0) {
                if (isPlayerLoggedIn(player)) {
                    sendToLobby(player);
                }
                cancel();
                return;
            }

            String title = ChatColor.translateAlternateColorCodes('&', plugin.getConfiguration().titleText);
            String subtitle = ChatColor.translateAlternateColorCodes('&', plugin.getConfiguration().subtitleText + count + " seconds...");

            player.sendTitle(title, subtitle);
            count--;
        }
    }

    private boolean isPlayerLoggedIn(Player player) {
        AuthMeApi authMeApi = AuthMeApi.getInstance();
        return authMeApi != null && authMeApi.isAuthenticated(player);
    }

    private void sendToLobby(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(plugin.getConfiguration().lobbyServer);

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    private void sendTitle(Player player, String title, String subtitle) {
        player.sendTitle(title, subtitle, 10, 50, 10); // Arguments: title, subtitle, fadeIn, stay, fadeOut (all in ticks)
    }
}
