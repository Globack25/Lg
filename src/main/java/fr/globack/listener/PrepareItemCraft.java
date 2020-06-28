package fr.globack.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class PrepareItemCraft implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCraft(PrepareItemCraftEvent e) {
		
		if(e.getInventory() instanceof CraftingInventory) {
			
			CraftingInventory inv = (CraftingInventory)e.getInventory();
			
			if(inv.getResult().getType() == Material.GOLDEN_APPLE && inv.getResult().getData().getData() == 1) {
				inv.setResult(new ItemStack(Material.AIR));
			}
			
		}
		
	}
	
}
