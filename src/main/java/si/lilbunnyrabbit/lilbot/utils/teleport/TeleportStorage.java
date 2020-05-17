package si.lilbunnyrabbit.lilbot.utils.teleport;

import java.util.HashMap;
import java.util.UUID;

public class TeleportStorage {
    private HashMap<UUID, TeleportRequests> tp_request_players;

    public TeleportStorage() {
        this.tp_request_players = new HashMap<>();
    }

    public TeleportRequests getRequests(UUID player_id) {
        if(!tp_request_players.containsKey(player_id)) {
            tp_request_players.put(player_id, new TeleportRequests());
        }

        return tp_request_players.get(player_id);
    }
}
