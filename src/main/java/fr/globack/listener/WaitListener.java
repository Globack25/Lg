package fr.globack.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.globack.GameState;
import fr.globack.Lg;

public class WaitListener implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(Lg.isState(GameState.WAIT) || Lg.isState(GameState.PRE_GAME)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMobSpawn(EntitySpawnEvent event) {
		if(Lg.isState(GameState.WAIT) || Lg.isState(GameState.PRE_GAME)) {
			event.setCancelled(true);
		}		
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if(Lg.isState(GameState.WAIT) || Lg.isState(GameState.PRE_GAME)) {
			event.setCancelled(true);
		}		
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent event) {
		if(Lg.isState(GameState.WAIT) || Lg.isState(GameState.PRE_GAME)) {
			event.setCancelled(true);
		}
	}
	
}
