package si.lilbunnyrabbit.lilbot.utils;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import si.lilbunnyrabbit.lilbot.lilBot;

import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sendLog(String message) {
        System.out.println("[lilBot] " + message);
    }

    public static void sendToMinecraft(String username, String message) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "[" + username + "]: " + ChatColor.WHITE + message);
    }

    public static void sendToPlayer(Player player, String message) {
        player.sendMessage(ChatColor.GREEN + "[lilBot]: " + ChatColor.WHITE + message);
    }

    public static void sendToPlayer(Player player, TextComponent message) {
        TextComponent full_message = new TextComponent( "[lilBot]: " );
        full_message.setColor( net.md_5.bungee.api.ChatColor.GREEN );
        full_message.addExtra(message);
        player.spigot().sendMessage(full_message);
    }

    public static boolean canTeleport(Player player) {
        if(!player.isOnGround()) {
            player.sendMessage("You can't tp when in the air");
        } else if(player.getRemainingAir() < player.getMaximumAir()) {
            player.sendMessage("You can't tp when you are swimming");
        } else if(player.getFireTicks() > -20) {
            player.sendMessage("You can't tp while you are on fire");
        } else return true;
        return false;
    }

    private static void teleportAnimation(Location location) {
        Location tp_location = location.clone();
        double r = 0.5;
        int steps = 8;
        double y_steps = 0.01;

        double tp_a = tp_location.getX();
        double tp_b = tp_location.getZ();

        World world = tp_location.getWorld();


        for (int i = 0; i < 360 * 4; i += steps) {
            double radians = Math.toRadians(i);

            tp_location.setX(tp_a + r * Math.sin(radians));
            tp_location.setZ(tp_b + r * Math.cos(radians));

            tp_location.setY(tp_location.getY() + y_steps);

            world.spawnParticle(Particle.DRAGON_BREATH, tp_location, 1, 0, 0, 0, 0);

            try { TimeUnit.MILLISECONDS.sleep(25); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    public static void teleportPlayer(Player player, Location tp_location, lilBot plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                teleportAnimation(tp_location);
                player.getWorld().strikeLightningEffect(tp_location);
                player.teleport(tp_location);
            }
        }.runTaskLater(plugin, 0);
    }

    public static void sendToDiscord(JDA jda, String channel_id, String message) {
        if(channel_id != null) {
            TextChannel minecraft_channel = jda.getTextChannelById(channel_id);
            if(minecraft_channel != null) {
                minecraft_channel.sendMessage(message).queue();
            }
        }
    }

    public static void updateDiscordChannelName(JDA jda, String channel_id, String name) {
        TextChannel minecraft_channel = jda.getTextChannelById(channel_id);
        if(minecraft_channel != null) {
            minecraft_channel.getManager().setName(name).queue();
        }
    }

    public static void updatePlayerCount(JDA jda, String channel_id, int difference) {
        int players_count = Bukkit.getOnlinePlayers().size() - difference;
        updateDiscordChannelName(jda, channel_id,
                String.format("\uD83D\uDFE2-%d-%s", players_count, players_count == 1 ? "player" : "players")
        );
    }
}
