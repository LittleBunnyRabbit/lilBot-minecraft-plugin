package si.lilbunnyrabbit.lilbot.commands.executor.tpa;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import si.lilbunnyrabbit.lilbot.utils.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class TpaExecutor implements CommandExecutor {
    private HashMap<UUID, UUID[]> tp_requests;

    public TpaExecutor(HashMap<UUID, UUID[]> tp_requests) {
        this.tp_requests = tp_requests;
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

//        UUID[] player_requests = tp_requests.containsKey(tp_player.getUniqueId()) ? tp_requests.get(tp_player.getUniqueId()) : new UUID[1];
//
//        if(Arrays.stream(player_requests).anyMatch(player.getUniqueId()::equals)) {
//            Utils.sendToPlayer(player, ChatColor.RED + "You already requested that...");
//            return true;
//        }

        Location tp_player_location = tp_player.getLocation();

        Utils.sendToPlayer(player, String.format(
                "%sTeleporting to %s%s",
                ChatColor.AQUA, ChatColor.DARK_PURPLE, tp_player.getDisplayName()
        ));
        player.teleport(tp_player_location);
        TextComponent message = new TextComponent( "[Accept]" );
        message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND,  "/help") );
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Visit the Spigot website!" ).create() ) );
        tp_player.spigot().sendMessage(message);
        return true;
    }
}