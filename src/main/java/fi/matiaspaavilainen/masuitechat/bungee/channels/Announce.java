package fi.matiaspaavilainen.masuitechat.bungee.channels;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Announce {
	public static void sendMessage(String msg, boolean staffOnly) {
//		for (Map.Entry<String, ServerInfo> entry : ProxyServer.getInstance().getServers().entrySet()) {
//			if (! entry.getValue().getPlayers().isEmpty()) {
//				ServerInfo serverInfo = entry.getValue();
//				serverInfo.ping((result, error) -> {
//					if (error == null) {
//						try (ByteArrayOutputStream b = new ByteArrayOutputStream();
//							 DataOutputStream out = new DataOutputStream(b)) {
//							out.writeUTF("Annonce");
//							out.writeBoolean(staffOnly);
//							out.writeUTF(ComponentSerializer.toString(new ComponentBuilder(msg).create()));
//							serverInfo.sendData("BungeeCord", b.toByteArray());
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//		}

		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			player.sendMessage(TextComponent.fromLegacyText(msg));
		}
	}
}
