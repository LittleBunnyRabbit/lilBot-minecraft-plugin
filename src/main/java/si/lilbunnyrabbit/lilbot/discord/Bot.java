package si.lilbunnyrabbit.lilbot.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.configuration.file.FileConfiguration;
import si.lilbunnyrabbit.lilbot.lilBot;
import si.lilbunnyrabbit.lilbot.utils.Utils;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class Bot extends ListenerAdapter {
    private JDA jda;
    private String discord_channel_id;

    public Bot(lilBot plugin) throws LoginException {
        FileConfiguration config = plugin.getConfig();
        this.discord_channel_id = config.getString("minecraft_channel");
        this.jda = new JDABuilder(config.getString("bot_token")).addEventListeners(this).build();
    }

    public JDA getJda() { return jda; }

    @Override
    public void onReady(ReadyEvent event) {
        Utils.updatePlayerCount(jda, discord_channel_id, 0);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if(!msg.getChannel().getId().equals(discord_channel_id)) return;
        User author = msg.getAuthor();
        if(author.isBot()) return;
        String nickname = Objects.requireNonNull(msg.getGuild().getMember(author)).getNickname();
        if(nickname == null) nickname = author.getName();
        Utils.sendToMinecraft(nickname, msg.getContentRaw());
    }
}