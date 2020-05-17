package si.lilbunnyrabbit.lilbot.utils.teleport;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TeleportRequests {
    private Set<UUID> tp_requests;

    TeleportRequests() {
        this.tp_requests = new HashSet<>();
    }

    public Set<UUID> getTpRequests() {
        return tp_requests;
    }

    public boolean addRequest(UUID tp_player_id) {
        if(tp_requests.contains(tp_player_id)) return false;
        tp_requests.add(tp_player_id);
        return true;
    }

    public boolean removeRequest(UUID tp_player_id) {
        if(!tp_requests.contains(tp_player_id)) return false;
        tp_requests.remove(tp_player_id);
        return true;
    }
}
