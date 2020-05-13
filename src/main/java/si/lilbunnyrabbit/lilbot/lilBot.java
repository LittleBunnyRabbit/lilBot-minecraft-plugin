package si.lilbunnyrabbit.lilbot;

import si.lilbunnyrabbit.lilbot.commands.executor.*;
import si.lilbunnyrabbit.lilbot.commands.executor.tpa.TpAcceptExecutor;
import si.lilbunnyrabbit.lilbot.commands.executor.tpa.TpDenyExecutor;
import si.lilbunnyrabbit.lilbot.commands.executor.tpa.TpaExecutor;
import si.lilbunnyrabbit.lilbot.listeners.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

import si.lilbunnyrabbit.lilbot.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class lilBot extends JavaPlugin {
    public HashMap<UUID, UUID[]> tp_requests = new HashMap<>();
    public List<UUID> players_teleporting = new ArrayList<>();

    @Override
    public void onEnable() {
        Utils.sendLog("onEnable");
        createCommands();

        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        Utils.sendLog("onDisable");
    }

    private void createCommands() {
        try {
            getCommand("home").setExecutor(new HomeExecutor(this));
            getCommand("spawn").setExecutor(new SpawnExecutor(this));
            getCommand("playerdata").setExecutor(new PlayerDataExecutor());
            getCommand("summonparticle").setExecutor(new SummonParticleExecutor(this));
            getCommand("inventory").setExecutor(new InventoryExecutor());
            getCommand("tpa").setExecutor(new TpaExecutor(tp_requests));
            getCommand("tpaccept").setExecutor(new TpAcceptExecutor(tp_requests));
            getCommand("tpdeny").setExecutor(new TpDenyExecutor(tp_requests));
            getCommand("echest").setExecutor(new EchestExecutor());
        } catch (NullPointerException e) { Utils.sendLog("Failed to create commands..."); }
    }
}