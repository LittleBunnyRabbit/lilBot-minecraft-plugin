package si.lilbunnyrabbit.lilbot.commands.executor;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import si.lilbunnyrabbit.lilbot.lilBot;

import java.util.concurrent.TimeUnit;

public class SummonParticleExecutor implements CommandExecutor {
    private lilBot plugin;

    public SummonParticleExecutor(lilBot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        new BukkitRunnable() {

            @Override
            public void run() {
                Player player = (Player) sender;
                Location location = player.getLocation();
                // player.addPotionEffect(PotionEffectType.SLOW.createEffect(Integer.MAX_VALUE, 255));
                double a = location.getX();
                double b = location.getZ();
                double r = 0.5;
                int steps = 8;

                double min_y = location.getY();
                double max_y = min_y + 2;
                double y_steps = -0.01;
                location.setY(max_y);

                for (int i = 0; i < 360 * 4; i += steps) {
                    double radians = Math.toRadians(i);
                    location.setX(a + r * Math.sin(radians));
                    location.setZ(b + r * Math.cos(radians));
                    double y = location.getY();
                    if(y + y_steps > max_y || y + y_steps < min_y) y_steps *= -1;

                    location.setY(y + y_steps);

                    player.getWorld().spawnParticle(Particle.DRAGON_BREATH, location, 1, 0, 0, 0, 0);

                    try {TimeUnit.MILLISECONDS.sleep(50); }
                    catch (InterruptedException e) {}
                }
                // player.removePotionEffect(PotionEffectType.SLOW);
            }
        }.runTaskLater(plugin, 0);

        return true;
    }
}
