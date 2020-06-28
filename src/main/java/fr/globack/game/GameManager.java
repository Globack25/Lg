package fr.globack.game;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import fr.globack.Lg;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class GameManager {
	
	public static void launchGame() {
		
		for(Player player : Bukkit.getOnlinePlayers()) randomTeleport(player);
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			players.setGameMode(GameMode.SURVIVAL);
		}
		
		GameTask task = new GameTask();
		task.runTaskTimer(Lg.getInstance(), 20, 20);
		
	}
	
	private static void randomTeleport(Player player) {
		
		Random r = new Random();
		Location loc = new Location(Bukkit.getWorld("world"), r.nextInt(1000), 120, r.nextInt(1000) - r.nextInt(1000));
		
		player.teleport(loc);
		
	}
	
	public static void checkWin() {
		
	}
	
    public static void sendTitle(Player player, String title, String subtitle, int ticks){
        IChatBaseComponent basetitle = ChatSerializer.a("{\"text\": \"" + title + "\"}");
        IChatBaseComponent basesubtitle = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, basetitle);
        PacketPlayOutTitle subtitlepacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, basesubtitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitlepacket);
        sendTime(player, ticks);
    }
   
    private static void sendTime(Player player, int ticks){
        PacketPlayOutTitle titlepacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, ticks, 20);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(titlepacket);
    }

}
