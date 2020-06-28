package fr.globack.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import fr.globack.Lg;

public class PortailListener implements Listener {
	
	@EventHandler
	public void onPortalCreate(PortalCreateEvent event) {
		if(!Lg.getInstance().nether) event.setCancelled(true);
	}
	
}
