package si.lilbunnyrabbit.lilbot.commands.executor;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.lilBot;
import si.lilbunnyrabbit.lilbot.utils.Utils;

public class HomeExecutor implements CommandExecutor {
    private lilBot plugin;

    public HomeExecutor(lilBot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!Utils.canTeleport(player)) return true;
        Location bed_location = player.getBedSpawnLocation();

        if(bed_location == null) {
            Utils.sendToPlayer(player, ChatColor.RED + "Missing bed!");
            return true;
        }

        Utils.sendToPlayer(player, ChatColor.AQUA + "Teleporting to your bed!");
        Utils.teleportPlayer(player, bed_location, plugin);

        return true;
    }
}
