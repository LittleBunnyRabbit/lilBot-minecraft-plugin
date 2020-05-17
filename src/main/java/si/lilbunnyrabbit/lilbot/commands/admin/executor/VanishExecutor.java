package si.lilbunnyrabbit.lilbot.commands.admin.executor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.commands.admin.AdminExecutor;
import si.lilbunnyrabbit.lilbot.lilBot;

public class VanishExecutor extends AdminExecutor {
    private lilBot plugin;

    public VanishExecutor(lilBot plugin) {
        this.plugin = plugin;
    }

    @Override
    public void executeCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        for(Player online_player : Bukkit.getOnlinePlayers()) {
            online_player.hidePlayer(plugin, player);
        }
    }
}