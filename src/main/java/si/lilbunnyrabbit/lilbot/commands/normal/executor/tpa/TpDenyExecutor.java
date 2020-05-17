package si.lilbunnyrabbit.lilbot.commands.normal.executor.tpa;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.utils.Utils;
import si.lilbunnyrabbit.lilbot.utils.teleport.TeleportStorage;

public class TpDenyExecutor implements CommandExecutor {
    private TeleportStorage tp_storage;

    public TpDenyExecutor(TeleportStorage tp_storage) {
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

        boolean wasRemoved = tp_storage.getRequests(player.getUniqueId()).removeRequest(tp_player.getUniqueId());
        if(!wasRemoved) {
            Utils.sendToPlayer(player, ChatColor.RED + tp_player_name + " didn't request to teleport...");
            return true;
        }

        Utils.sendToPlayer(player, String.format("%sRequest from %s%s%s is denied!", ChatColor.AQUA, ChatColor.RED, tp_player_name, ChatColor.AQUA));
        Utils.sendToPlayer(tp_player, String.format("%s%s%s denied your request!", ChatColor.RED, player.getName(), ChatColor.AQUA));
        return true;
    }
}