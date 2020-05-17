package si.lilbunnyrabbit.lilbot.commands.admin.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.commands.admin.AdminExecutor;

public class EchestExecutor extends AdminExecutor {

    @Override
    public void executeCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.openInventory(player.getEnderChest());
    }
}
