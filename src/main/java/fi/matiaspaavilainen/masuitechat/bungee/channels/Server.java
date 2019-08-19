package fi.matiaspaavilainen.masuitechat.bungee.channels;

import fi.matiaspaavilainen.masuitechat.bungee.MaSuiteChat;
import fi.matiaspaavilainen.masuitechat.bungee.Utilities;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server {

    public static List<UUID> ignoredChannels = new ArrayList<>();

    public static void sendMessage(ProxiedPlayer p, String msg) {
        BaseComponent[] txt = Utilities.chatFormat(p, msg, "server");
        for (ProxiedPlayer player : ProxyServer.getInstance().getServerInfo(p.getServer().getInfo().getName()).getPlayers()) {
            if (!ignoredChannels.contains(player.getUniqueId())) {
                if(MaSuiteChat.ignores.get(player.getUniqueId()) == null){
                    player.sendMessage(txt);
                } else if(!MaSuiteChat.ignores.get(player.getUniqueId()).contains(p.getUniqueId())){
                    player.sendMessage(txt);
                }
            }
          



        }
    }
}
