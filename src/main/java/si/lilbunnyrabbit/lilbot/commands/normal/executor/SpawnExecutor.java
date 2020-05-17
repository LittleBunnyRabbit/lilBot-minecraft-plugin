package si.lilbunnyrabbit.lilbot.commands.normal.executor;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.lilBot;
import si.lilbunnyrabbit.lilbot.utils.Utils;

public class SpawnExecutor implements CommandExecutor {
    private lilBot plugin;

    public SpawnExecutor(lilBot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!Utils.canTeleport(player)) return true;
        Location spawn_location = player.getWorld().getSpawnLocation();

        Utils.sendToPlayer(player, ChatColor.AQUA + "Teleporting you to spawn!");
        Utils.teleportPlayer(player, spawn_location, plugin);
        return true;
    }
}