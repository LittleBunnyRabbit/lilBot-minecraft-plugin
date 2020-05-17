package si.lilbunnyrabbit.lilbot.commands.normal.tabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.utils.teleport.TeleportStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TpaTabCompleter implements TabCompleter {
    private TeleportStorage tp_storage;

    public TpaTabCompleter(TeleportStorage tp_storage) {
        this.tp_storage = tp_storage;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        Player player = (Player) sender;
        Set<UUID> tp_requests = tp_storage.getRequests(player.getUniqueId()).getTpRequests();
        List<String> player_names = new ArrayList<>();
        for(UUID tp_player_id : tp_requests) {
            Player tp_player = Bukkit.getPlayer(tp_player_id);
            if(tp_player != null) {
                player_names.add(tp_player.getName());
            }
        }
        return player_names;
    }
}

