package si.lilbunnyrabbit.lilbot.commands.executor.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TpAcceptExecutor implements CommandExecutor {
    private HashMap<UUID, UUID[]> tp_requests;

    public TpAcceptExecutor(HashMap<UUID, UUID[]> tp_requests) {
        this.tp_requests = tp_requests;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        return true;
    }
}