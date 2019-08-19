package fi.matiaspaavilainen.masuitechat.bukkit.events;

import fi.matiaspaavilainen.masuitechat.bukkit.MaSuiteChat;
import fi.matiaspaavilainen.masuitecore.core.objects.PluginChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.thecraft.tcapi.utils.icon.IconRpMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatEvent implements Listener {

    private MaSuiteChat plugin;
    private Pattern pattern;

    public ChatEvent(MaSuiteChat plugin) {
        this.plugin = plugin;
        pattern = Pattern.compile(":([^:]*):");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMessage(AsyncPlayerChatEvent e) {
        e.setMessage(parseEmoticon(e));
        e.getPlayer().sendRawMessage("send");
        e.setCancelled(true);
        new PluginChannel(plugin, e.getPlayer(), new Object[]{"MaSuiteChat", "Chat", e.getPlayer().getUniqueId().toString(),  parseEmoticon(e)}).send();
    }

    private String parseEmoticon(AsyncPlayerChatEvent playerChatEvent) {
        String message = playerChatEvent.getMessage();
        Player p = playerChatEvent.getPlayer();
        StringBuffer sb = new StringBuffer();
        Matcher m = pattern.matcher(message);
        String icon;
        String matchedText;
        while (m.find()) {
            matchedText = m.group();
            if (matchedText.length() == 2) continue;
            icon = IconRpMapping.getIconStatic(matchedText.substring(1, matchedText.length()-1), false);
            if (icon != null && p.hasPermission("tcemote." + icon)) {
                m.appendReplacement(sb, ChatColor.WHITE + icon + ChatColor.RESET);
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
