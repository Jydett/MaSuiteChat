package fi.matiaspaavilainen.masuitechat.bungee;

import fi.matiaspaavilainen.masuitechat.bungee.objects.Group;
import fi.matiaspaavilainen.masuitecore.bungee.chat.Formator;
import fi.matiaspaavilainen.masuitecore.core.configuration.BungeeConfiguration;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class Utilities {

    public static BaseComponent[] chatFormat(ProxiedPlayer p, String msg, String channel) {

        Formator formator = new Formator();
        BungeeConfiguration bconfig = new BungeeConfiguration();
        Configuration config = bconfig.load("chat", "chat.yml");
        String format = config.getString("formats." + channel);
        String server = config.getString("channels." + p.getServer().getInfo().getName().toLowerCase() + ".prefix");

        Group group = new Group().get(p.getUniqueId());
        String prefixAdmin = config.getString("prefix." + group.getUser().getPrimaryGroup(), "");
        format = formator.colorize(
                format.replace("%server%", server)
                        .replace("%prefix%", group.getPrefix())
                        .replace("%nickname%", p.getDisplayName())
                        .replace("%realname%", p.getName())
                        .replace("%staffprefix%", prefixAdmin)
                        .replace("%suffix%", group.getSuffix()));
        if (p.hasPermission("masuitechat.chat.colors")) {
            format = formator.colorize(format.replace("%message%", msg));
        } else {
            format = format.replace("%message%", msg);
        }

        //p.sendMessage(new ComponentBuilder(format).create());

        //TextComponent message = MDChat.getMessageFromString(format);
        return new ComponentBuilder(format).create();/*.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(formator.colorize(config.load("chat", "messages.yml")
                        .getString("message-hover-actions")
                        .replace("%timestamp%", new Date().getDate(new java.util.Date())))).create())).create();*/
    }
}
