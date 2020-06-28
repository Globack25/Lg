package fr.globack.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import fr.globack.PlayerDeathTask;
import fr.globack.UhcPlayer;
import fr.globack.Lg;

public class OnDamage implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(Lg.death.contains(player)) {
				event.setCancelled(true);
				return;
			}
			if(event.getDamage() >= player.getHealth()) {
				player.teleport(new Location(Bukkit.getWorld("world"), 0, 180, 0));
				List<ItemStack> it = new ArrayList<>();
				for(int i = 0; i < player.getInventory().getSize(); i++) {
					if(player.getInventory().getItem(i) != null) {
						it.add(player.getInventory().getItem(i));
					}
				}
				UhcPlayer playerz = null;
				for(UhcPlayer players : Lg.allPlayers) {
					if(player.getUniqueId() == players.getUuid()) {
						playerz = players;
					}
				}
				PlayerDeathTask task = new PlayerDeathTask(player, player.getLocation(), it, playerz.getRole());
				task.runTaskTimer(Lg.getInstance(), 20, 20);
				player.setGameMode(GameMode.ADVENTURE);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(Lg.death.contains(player)) {
				event.setCancelled(true);
				return;
			}
			if(event.getDamage() >= player.getHealth()) {
				player.teleport(new Location(Bukkit.getWorld("world"), 0, 180, 0));
				List<ItemStack> it = new ArrayList<>();
				for(int i = 0; i < player.getInventory().getSize(); i++) {
					if(player.getInventory().getItem(i) != null) {
						it.add(player.getInventory().getItem(i));
					}
				}
				UhcPlayer playerz = null;
				for(UhcPlayer players : Lg.allPlayers) {
					if(player.getUniqueId() == players.getUuid()) {
						playerz = players;
					}
				}
				PlayerDeathTask task = new PlayerDeathTask(player, player.getLocation(), it, playerz.getRole(), event.getDamager());
				task.runTaskTimer(Lg.getInstance(), 20, 20);
				player.setGameMode(GameMode.ADVENTURE);
			}
		}
	}

}
