package fr.globack.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.globack.GameState;
import fr.globack.Lg;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class PlayerMove implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		int distance = (int) loc.distance(new Location(Bukkit.getWorld("world"), 0, loc.getY(), 0));
		
		if(Lg.isState(GameState.WAIT) || Lg.isState(GameState.PRE_GAME)) {
			if(Lg.allPlayers.size() >= 10) {
				sendActionBar(player, "§eJoueur(s) manquant -> §cAucun");
				return;
			}
			sendActionBar(player, "§eJoueur(s) manquant -> §b" + String.valueOf(10 - Lg.allPlayers.size()));
		} else {
			if(distance == 0 ) {
				sendActionBar(player, "§dCentre -> §aVous êtes au centre");
			}
			if(distance > 0 && distance <= 300) {
				sendActionBar(player, "§dCentre -> §6Entre 0 et 300 blocks");
			}
			if(distance > 300 && distance <= 600) {
				sendActionBar(player, "§dCentre -> §eEntre 300 et 600 blocks");
			}
			if(distance > 900 && distance <= 1200) {
				sendActionBar(player, "§dCentre -> §cEntre 900 et 1200 blocks");
			}
			if(distance > 1200) {
				sendActionBar(player, "§dCentre -> §cSupérieur à 1200 blocks");
			}
		}
		
	}
	
    public void sendActionBar(Player player, String message){
        IChatBaseComponent basetitle = ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(basetitle, (byte)2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
