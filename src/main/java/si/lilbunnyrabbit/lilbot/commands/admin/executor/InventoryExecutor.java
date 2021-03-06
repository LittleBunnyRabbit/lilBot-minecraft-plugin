package si.lilbunnyrabbit.lilbot.commands.admin.executor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.commands.admin.AdminExecutor;

public class InventoryExecutor extends AdminExecutor {

    @Override
    public void executeCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        String tp_player_name = args.length > 0 ? args[0] : null;
        if(tp_player_name == null) {
            player.sendMessage("You need to insert players username...");
            return;
        }

        Player tp_player = Bukkit.getPlayer(tp_player_name);
        if(tp_player == null) {
            player.sendMessage("Can't access the inventory of that player");
            return;
        }
        // new InventoryViewer(tp_player).openInventory(player);
        player.openInventory(tp_player.getInventory());
    }
}