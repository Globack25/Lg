package fr.globack.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	
	String name = "";
	int amount = 1;
	byte data = 0;
	Material type = Material.DIRT;
	List<String> lore = Arrays.asList("");
	boolean glow = false;
	Map<Enchantment, Integer> ench = new HashMap<>();
	
	public Items() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public Material getType() {
		return type;
	}
	
	public void setType(Material type) {
		this.type = type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isGlow() {
		return glow;
	}
	
	public void setGlow(boolean glow) {
		this.glow = glow;
	}
	
	public byte getData() {
		return data;
	}
	
	public void setData(byte data) {
		this.data = data;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	
	public List<String> getLoreWithColor() {
    	List<String> lores = lore.stream()
	    		.map(str -> str.replace("&", "§"))
	    		.collect(Collectors.toList());
		return lores;
	}
	
	public List<String> getLore() {
		return lore;
	}
	
	public void addEnchantment(Enchantment ench, int level) {
		this.ench.put(ench, level);
	}
	
	public ItemStack toItemStack() {
		ItemStack it = new ItemStack(type, amount, data);
		it.addEnchantments(ench);
		ItemMeta i = it.getItemMeta();
		i.setDisplayName(name);
		i.setLore(lore);
		if(glow) {
			i.addEnchant(Enchantment.DURABILITY, 1, true);
			i.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		it.setItemMeta(i);
		return it;
	}
	
}
