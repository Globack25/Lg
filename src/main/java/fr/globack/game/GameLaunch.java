package fr.globack.game;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.globack.GameState;
import fr.globack.Lg;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class GameLaunch extends BukkitRunnable {
	
	private int timer = 120;
	private int[] time = new int[] {120, 100, 60, 30, 10, 5, 4, 3, 2, 1};
	
	@Override
	public void run() {
		
		Lg.setState(GameState.PRE_GAME);
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setLevel(timer);
		}
		
		for(int i : time) {
			if(timer == i) {
				for(Player player : Bukkit.getOnlinePlayers()) {
					sendTitle(player, "§eTéléportation..", "§edans §b" + timer + " §esecondes", 20);
				}
			}
		}
		
		if(timer == 0) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				sendTitle(player, "§eTéléportation..", "", 20);
				player.getInventory().clear();
			}
			GameManager.launchGame();
			cancel();
		}
		
		timer--;
		
		if(Lg.allPlayers.size() < 10) {
			for(Player player : Bukkit.getOnlinePlayers()) {
				if(player.getName().equalsIgnoreCase("Aytrexois")) {
					return;
				}
			}
			timer = 120;
			cancel();
		}
		
	}
	
    public void sendTitle(Player player, String title, String subtitle, int ticks){
        IChatBaseComponent basetitle = ChatSerializer.a("{\"text\": \"" + title + "\"}");
        IChatBaseComponent basesubtitle = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, basetitle);
        PacketPlayOutTitle subtitlepacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, basesubtitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlepacket);
        sendTime(player, ticks);
    }
   
    private void sendTime(Player player, int ticks){
        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
    }

}
