package fr.globack.listener;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import fr.globack.Lg;

public class DropListener implements Listener {
	
	@EventHandler
	public void onLeaveDecay(LeavesDecayEvent event) {
		
		Random r = new Random();
		int alea = r.nextInt(100);
		int drop = Lg.getInstance().appleDrop;
		
		event.setCancelled(true);
		event.getBlock().setType(Material.AIR);
		
		switch(drop) {
		
		default:
			break;
		
		case 25:
			if(alea <= (drop - 20)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
			
		case 50:
			if(alea <= (drop - 40)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
			
		case 75:
			if(alea <= (drop - 50)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
			
		case 100:
			if(alea <= (drop - 60)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
		
		}
		
	}
	
	@EventHandler
	public void onBreakLeaves(BlockBreakEvent event) {
		
		if(event.getBlock().getType() != Material.LEAVES || event.getBlock().getType() != Material.LEAVES_2) return;
		
		Random r = new Random();
		int alea = r.nextInt(100);
		int drop = Lg.getInstance().appleDrop;
		
		event.setCancelled(true);
		event.getBlock().setType(Material.AIR);
		
		switch(drop) {
		
		default:
			break;
		
		case 25:
			if(alea <= (drop - 20)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
			
		case 50:
			if(alea <= (drop - 40)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
			
		case 75:
			if(alea <= (drop - 50)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
			
		case 100:
			if(alea <= (drop - 60)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
			}
			break;
		
		}
		
	}
	
	@EventHandler
	public void onBreakGravel(BlockBreakEvent event) {
		
		if(event.getBlock().getType() != Material.GRAVEL) return;
		
		Random r = new Random();
		int alea = r.nextInt(100);
		int drop = Lg.getInstance().flintDrop;
		
		event.setCancelled(true);
		event.getBlock().setType(Material.AIR);
		
		switch(drop) {
		
		default:
			break;
		
		case 25:
			if(alea <= (drop - 20)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT, 1));
			}
			break;
			
		case 50:
			if(alea <= (drop - 40)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT, 1));
			}
			break;
			
		case 75:
			if(alea <= (drop - 50)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT, 1));
			}
			break;
			
		case 100:
			if(alea <= (drop - 60)) {
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT, 1));
			}
			break;
		
		}
		
	}

}
