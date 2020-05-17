package si.lilbunnyrabbit.lilbot;

import net.dv8tion.jda.api.JDA;
import org.bukkit.plugin.java.JavaPlugin;
import si.lilbunnyrabbit.lilbot.commands.admin.executor.EchestExecutor;
import si.lilbunnyrabbit.lilbot.commands.admin.executor.InventoryExecutor;
import si.lilbunnyrabbit.lilbot.commands.admin.executor.UncoverExecutor;
import si.lilbunnyrabbit.lilbot.commands.admin.executor.VanishExecutor;
import si.lilbunnyrabbit.lilbot.commands.normal.executor.HomeExecutor;
import si.lilbunnyrabbit.lilbot.commands.normal.executor.SpawnExecutor;
import si.lilbunnyrabbit.lilbot.commands.normal.executor.tpa.TpAcceptExecutor;
import si.lilbunnyrabbit.lilbot.commands.normal.executor.tpa.TpDenyExecutor;
import si.lilbunnyrabbit.lilbot.commands.normal.executor.tpa.TpaExecutor;
import si.lilbunnyrabbit.lilbot.commands.normal.tabCompleter.TpaTabCompleter;
import si.lilbunnyrabbit.lilbot.discord.Bot;
import si.lilbunnyrabbit.lilbot.listeners.EventListener;
import si.lilbunnyrabbit.lilbot.utils.Utils;
import si.lilbunnyrabbit.lilbot.utils.teleport.TeleportStorage;

import javax.security.auth.login.LoginException;

public class lilBot extends JavaPlugin {
    private JDA jda;
    private String discord_channel_id;
    private TeleportStorage tp_storage;

    public lilBot() {
        this.discord_channel_id = this.getConfig().getString("minecraft_channel");
        this.tp_storage = new TeleportStorage();
    }

    @Override
    public void onEnable() {
        createCommands();
        try { this.jda = new Bot(this).getJda(); }
        catch (LoginException e) { e.printStackTrace(); }

        getServer().getPluginManager().registerEvents(new EventListener(this.jda, this), this);
    }

    @Override
    public void onDisable() {
        Utils.updateDiscordChannelName(jda, discord_channel_id, "\uD83D\uDD34-offline");
    }

    private void createCommands() {
        try {
            getCommand("home").setExecutor(new HomeExecutor(this));
            getCommand("spawn").setExecutor(new SpawnExecutor(this));
            getCommand("inventory").setExecutor(new InventoryExecutor());
            getCommand("tpa").setExecutor(new TpaExecutor(tp_storage));
            getCommand("tpaccept").setExecutor(new TpAcceptExecutor(this, tp_storage));
            getCommand("tpdeny").setExecutor(new TpDenyExecutor(tp_storage));
            getCommand("echest").setExecutor(new EchestExecutor());
            getCommand("vanish").setExecutor(new VanishExecutor(this));
            getCommand("uncover").setExecutor(new UncoverExecutor(this));
            getCommand("tpaccept").setTabCompleter(new TpaTabCompleter(tp_storage));
            getCommand("tpdeny").setTabCompleter(new TpaTabCompleter(tp_storage));
        } catch (NullPointerException e) { Utils.sendLog("Failed to create commands..."); }
    }
}