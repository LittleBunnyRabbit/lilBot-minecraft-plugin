package si.lilbunnyrabbit.lilbot.utils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import si.lilbunnyrabbit.lilbot.lilBot;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sendLog(String message) {
        System.out.println("[My plugin] " + message);
    }

    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "[lilBot]: " + ChatColor.WHITE + message);
    }

    public static void sendToPlayer(Player player, String message) {
        player.sendMessage(ChatColor.GREEN + "[lilBot]: " + ChatColor.WHITE + message);
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

    public static void runTeleportAnimation(Player player, Location tp_location) {
        Location player_location = player.getLocation();
        double r = 0.5;
        int steps = 8;
        double y_steps = -0.01;
        int height_offset = 2;

        double pl_a = player_location.getX();
        double pl_b = player_location.getZ();
        double pl_y = player_location.getY();

        double tp_a = tp_location.getX();
        double tp_b = tp_location.getZ();

        player_location.setY(pl_y + height_offset);

        World world = player.getWorld();

        for (int i = 0; i < 360 * 4; i += steps) {
            double radians = Math.toRadians(i);

            player_location.setX(pl_a + r * Math.sin(radians));
            player_location.setZ(pl_b + r * Math.cos(radians));

            tp_location.setX(tp_a + r * Math.sin(radians));
            tp_location.setZ(tp_b + r * Math.cos(radians));

            double y = player_location.getY();
            if(y + y_steps > pl_y + height_offset || y + y_steps < pl_y) y_steps *= -1;

            player_location.setY(y + y_steps);
            tp_location.setY(tp_location.getY() - y_steps);

            world.spawnParticle(Particle.DRAGON_BREATH, player_location, 1, 0, 0, 0, 0);
            world.spawnParticle(Particle.DRAGON_BREATH, tp_location, 1, 0, 0, 0, 0);

            try {
                TimeUnit.MILLISECONDS.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void teleportPlayer(Player player, Location tp_location, lilBot plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                runTeleportAnimation(player, tp_location);
                player.getWorld().strikeLightningEffect(tp_location);
                player.teleport(tp_location);
            }
        }.runTaskLater(plugin, 0);
    }
}
