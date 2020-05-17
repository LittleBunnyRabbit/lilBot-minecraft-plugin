package si.lilbunnyrabbit.lilbot.commands.normal.executor.tpa;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.lilBot;
import si.lilbunnyrabbit.lilbot.utils.Utils;
import si.lilbunnyrabbit.lilbot.utils.teleport.TeleportStorage;

public class TpAcceptExecutor implements CommandExecutor {
    private lilBot plugin;
    private TeleportStorage tp_storage;

    public TpAcceptExecutor(lilBot plugin, TeleportStorage tp_storage) {
        this.plugin = plugin;
        this.tp_storage = tp_storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        String tp_player_name = args.length > 0 ? args[0] : null;
        if(tp_player_name == null) {
            Utils.sendToPlayer(player, ChatColor.RED + "You have to insert players username...");
            return true;
        }

        Player tp_player = Bukkit.getPlayer(tp_player_name);
        if(tp_player == null) {
            Utils.sendToPlayer(player, ChatColor.RED + "That player doesn't exist!");
            return true;
        }

        boolean canTeleport = tp_storage.getRequests(player.getUniqueId()).removeRequest(tp_player.getUniqueId());
        if(!canTeleport) {
            Utils.sendToPlayer(player, ChatColor.RED + tp_player_name + " didn't request to teleport...");
            return true;
        }

        Utils.sendToPlayer(player, String.format("%sTeleporting %s%s%s to you!", ChatColor.AQUA, ChatColor.DARK_PURPLE, tp_player_name, ChatColor.AQUA));
        Utils.sendToPlayer(tp_player, String.format("%sTeleporting you to %s%s%s!", ChatColor.AQUA, ChatColor.DARK_PURPLE, player.getName(), ChatColor.AQUA));

        Utils.teleportPlayer(tp_player, player.getLocation(), plugin);
        return true;
    }
}