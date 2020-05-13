package si.lilbunnyrabbit.lilbot.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import si.lilbunnyrabbit.lilbot.utils.Utils;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // player.sendMessage("Vector: " + player.getVelocity().toString());
        // player.sendMessage("Fire ticks: " + player.getFireTicks());
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Utils.sendLog(String.format("[%s]:%s", player.getName(), message));
    }
}
