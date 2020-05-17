package si.lilbunnyrabbit.lilbot.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!canExecute(player)) {
            player.sendMessage("You can't use this command!");
            return true;
        }

        executeCommand(sender, command, label, args);
        return true;
    }

    private boolean canExecute(Player player) {
        if(!player.isOp()) return false;
        return true;
    }

    public void executeCommand(CommandSender sender, Command command, String label, String[] args) {}
}