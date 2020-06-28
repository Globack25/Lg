package fr.globack.game;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.globack.GameState;
import fr.globack.UhcPlayer;
import fr.globack.Lg;

public class GameWin extends BukkitRunnable {

	private UhcPlayer player;
	private List<Player> players;
	private int timer = 30;
	
	public GameWin(UhcPlayer player) {
		this.player = player;
	}
	
	public GameWin(List<Player> player) {
		this.players = player;
	}
	
	@Override
	public void run() {
		
		Lg.setState(GameState.FINISH);
		
		Bukkit.getBannedPlayers().clear();
		Bukkit.getWhitelistedPlayers().clear();
		
		if(timer == 0) {
			for(Player players : Bukkit.getOnlinePlayers()) {
				players.setOp(false);
				players.kickPlayer("");
				Bukkit.spigot().restart();
			}
		}
		
		if(player != null) {
			Firework f = (Firework)player.getPlayer().getWorld().spawnEntity(player.getPlayer().getLocation(), EntityType.FIREWORK);
			f.detonate();
			FireworkMeta FM = f.getFireworkMeta();
			FireworkEffect effect = FireworkEffect.builder()
					.flicker(true)
					.withColor(Color.BLUE)
					.withFade(Color.ORANGE)
					.with(Type.STAR)
					.trail(true)
					.build();
			FM.setPower(1);
			FM.addEffect(effect);
			f.setFireworkMeta(FM);
		}
		
		if(players != null) {
			for(Player p : players) {
				Firework f = (Firework)p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
				f.detonate();
				FireworkMeta FM = f.getFireworkMeta();
				FireworkEffect effect = FireworkEffect.builder()
						.flicker(true)
						.withColor(Color.BLUE)
						.withFade(Color.ORANGE)
						.with(Type.STAR)
						.trail(true)
						.build();
				FM.setPower(1);
				FM.addEffect(effect);
				f.setFireworkMeta(FM);
			}
		}
		
		timer--;
		
	}
	
	

}
