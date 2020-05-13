package si.lilbunnyrabbit.lilbot.commands.executor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import si.lilbunnyrabbit.lilbot.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerDataExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        String players_array = players.stream()
                               .map(HumanEntity::getName)
                               .collect(Collectors.joining(", ", "[", "]"));

        Utils.sendLog(players_array);

        for (Player player: players) {
            PlayerInventory player_inventory = player.getInventory();
            Utils.sendLog("" + player.getName() + "");
            Utils.sendLog("-------------------- Inventory --------------------");
            for (int i = 0; i < 4 * 9; i++) {
                ItemStack is = player_inventory.getItem(i);
                if(is == null) continue;
                Utils.sendLog("Slot " + i + ": " + is.getType().toString() + " (" + is.getAmount() + ")");
            }
            Utils.sendLog("<------------------------------------------------------------>");
        }
        return true;
    }
}
