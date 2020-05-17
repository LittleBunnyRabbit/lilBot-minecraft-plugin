package si.lilbunnyrabbit.lilbot.commands.normal.executor.tpa;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.utils.Utils;
import si.lilbunnyrabbit.lilbot.utils.teleport.TeleportStorage;

import java.util.UUID;

public class TpaExecutor implements CommandExecutor {
    private TeleportStorage tp_storage;

    public TpaExecutor(TeleportStorage tp_storage) {
        this.tp_storage = tp_storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!Utils.canTeleport(player)) return true;

        String tp_player_name = args.length > 0 ? args[0] : null;
        if(tp_player_name == null) {
            Utils.sendToPlayer(player, ChatColor.RED + "You have to insert players username...");
            return true;
        }

        Player tp_player = Bukkit.getPlayer(tp_player_name);
        if(tp_player == null) {
            Utils.sendToPlayer(player, ChatColor.RED + "Can't tp to that player...");
            return true;
        }

        UUID player_id = player.getUniqueId();
        UUID tp_player_id = tp_player.getUniqueId();

        boolean isAdded = tp_storage.getRequests(tp_player_id).addRequest(player_id);

        if(!isAdded) {
            Utils.sendToPlayer(player, ChatColor.RED + "You have already requested to teleport to " + ChatColor.DARK_PURPLE + tp_player_name);
            return true;
        }

        String player_name = player.getName();

        TextComponent tp_request_message = new TextComponent(String.format("%s%s%s wants to teleport to you!\n", ChatColor.DARK_PURPLE, player_name, ChatColor.AQUA));

        TextComponent message_accept = new TextComponent( "[Accept]" );
        message_accept.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND,  "/tpaccept " + player_name));
        message_accept.setColor( net.md_5.bungee.api.ChatColor.GREEN );
        message_accept.setBold( true );

        TextComponent message_deny = new TextComponent( "[Deny]" );
        message_deny.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND,  "/tpdeny " + player_name));
        message_deny.setColor( net.md_5.bungee.api.ChatColor.RED );
        message_deny.setBold( true );

        tp_request_message.addExtra(message_accept);
        tp_request_message.addExtra(" ");
        tp_request_message.addExtra(message_deny);

        Utils.sendToPlayer(tp_player, tp_request_message);
        return true;
    }
}