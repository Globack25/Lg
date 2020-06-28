package fr.globack.game;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

import fr.globack.Lg;

public class GameBorder extends BukkitRunnable {
	
	@Override
	public void run() {
		
		WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
		if(border.getSize() <= Lg.getInstance().minBorder) {
			cancel();
			return;
		}
		border.setSize(border.getSize() - Lg.getInstance().perSecond);
		
	}

}
