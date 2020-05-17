package si.lilbunnyrabbit.lilbot.listeners;

import net.dv8tion.jda.api.JDA;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import si.lilbunnyrabbit.lilbot.lilBot;
import si.lilbunnyrabbit.lilbot.utils.Utils;

public class EventListener implements Listener {
    private JDA jda;
    private String discord_channel_id;

    public EventListener(JDA jda, lilBot plugin) {
        this.jda = jda;
        this.discord_channel_id = plugin.getConfig().getString("minecraft_channel");
    }

    private void sendToDiscord(String message) {
        Utils.sendToDiscord(jda, discord_channel_id, message);
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event) {
        sendToDiscord(String.format("`[%s]:` %s", event.getPlayer().getName(), event.getMessage()));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        sendToDiscord(String.format(":heavy_check_mark: `%s` joined the server!", event.getPlayer().getName()));
        Utils.updatePlayerCount(jda, discord_channel_id, 0);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        sendToDiscord(String.format(":x: `%s` left the server!", event.getPlayer().getName()));
        Utils.updatePlayerCount(jda, discord_channel_id, 1);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        sendToDiscord(String.format(":skull: %s", event.getDeathMessage()));
    }

    @EventHandler
    public void onKick(PlayerKickEvent event){
        sendToDiscord(String.format(":skull_crossbones: `%s` got kicked! %s", event.getPlayer().getName(), event.getReason()));
    }
}
