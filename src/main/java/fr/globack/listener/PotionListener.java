package fr.globack.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;

import fr.globack.Lg;

public class PotionListener implements Listener {
	
	@EventHandler
	public void onBrew(BrewEvent event) {
		
		boolean potionEnable = Lg.getInstance().potionEnable;
		boolean potionII = Lg.getInstance().potionII;
		boolean force = Lg.getInstance().force;
		
		if(!potionEnable) {
			event.setCancelled(true);
		}
		
		if(!potionII) {
			if(event.getContents().contains(Material.GLOWSTONE)) {
				event.setCancelled(true);
			}
		}
		
		if(!force) {
			if(event.getContents().contains(Material.BLAZE_POWDER)) {
				event.setCancelled(true);
			}
		}
		
	}

}
