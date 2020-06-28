package fr.globack.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.globack.GameState;
import fr.globack.UhcPlayer;
import fr.globack.Lg;
import fr.globack.role.Role;
import fr.globack.role.RoleEffect;

public class GameTask extends BukkitRunnable {
	
	public static int timer = 0;
	private final String prefix = "§6[LgUhc] ";
	List<Role> allRoles = new ArrayList<>();
	List<UhcPlayer> allPlayers = new ArrayList<>();
	public static Player random;
	
	public GameTask() {
		
	}
	
	@Override
	public void run() {
		
		if(timer == 30) {
			Bukkit.broadcastMessage(prefix + " §cAttention : vous pouvez désormais prendre des dégats");
			Lg.setState(GameState.GAME);
		}
		
		if(timer == 20 * 60) {
			Lg.getInstance().loadRole();
			for(UhcPlayer player : Lg.allPlayers) {
				player.addInRandomRole();
			}
			allRoles.clear();
			for(Role role : Lg.role) {
				allRoles.add(role);
			}
			for(Role role : Lg.role) {
				if(role.getName().equalsIgnoreCase("Loup-Garou Blanc")) {
					for(Player player : role.getPlayerRole()) {
						player.setHealthScale(30);
					}
				}
				if(role.getName().equalsIgnoreCase("Chasseur de Prime")) {
					for(Player player : role.getPlayerRole()) {
						Random r = new Random();
						int alea = r.nextInt(Lg.allPlayers.size());
						while(Lg.allPlayers.get(alea).getPlayer() == random) {
							random = Lg.allPlayers.get(alea).getPlayer();
						}
						player.sendMessage("§cVotre cible est §9" + random.getName());
						player.setHealthScale(24);
					}
				}
				if(role.getName().equalsIgnoreCase("Petite Fille")) {
					for(Player player : role.getPlayerRole()) {
						player.getInventory().addItem(new ItemStack(Material.TNT, 5));
					}
				}
				if(role.getName().equalsIgnoreCase("Sorciere")) {
					for(Player player : role.getPlayerRole()) {
						ItemStack speed = new ItemStack(Material.POTION, 1, (byte)8194);
						ItemStack invisi = new ItemStack(Material.POTION, 1,(byte)8238);
						ItemStack heal = new ItemStack(Material.POTION, 2, (short)16453);
						player.getInventory().addItem(heal);
						player.getInventory().addItem(speed);
						player.getInventory().addItem(invisi);
					}
				}
				if(role.getName().equalsIgnoreCase("Salvateur")) {
					for(Player player : role.getPlayerRole()) {
						ItemStack heal = new ItemStack(Material.POTION, 2, (short)16453);
						player.getInventory().addItem(new ItemStack(heal));
					}
				}
				if(role.getName().equalsIgnoreCase("Voyante")) {
					for(Player player : role.getPlayerRole()) {
						player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 4));
						player.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 4));
					}
				}
				if(role.getName().equalsIgnoreCase("Cupidon")) {
					for(Player player : role.getPlayerRole()) {
						ItemStack bow = new ItemStack(Material.BOW, 1);
						bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
						player.getInventory().addItem(new ItemStack(bow));
						player.getInventory().addItem(new ItemStack(Material.BOOK, 1));
						player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
						player.getInventory().addItem(new ItemStack(Material.STRING, 3));
					}
				}
			}
			
			RoleEffect effect = new RoleEffect();
			effect.runTaskTimer(Lg.getInstance(), 40, 40);
		}
		
		allPlayers.clear();
		for(UhcPlayer players : Lg.allPlayers) {
			allPlayers.add(players);
		}
		
		for(Player playerz : Bukkit.getOnlinePlayers()) {
			
		}
		
		if(timer >= 20 * 60) {
			for(Role role : allRoles) {
				if(role.getSize() == 0) {
					Lg.role.remove(role);
				}
			}
		}
		
		if(timer == Lg.getInstance().pvp) {
			Bukkit.broadcastMessage(prefix + " §cAttention : le pvp est désormais actif");
			Bukkit.getWorld("world").setPVP(true);
		}
		
		if(timer == Lg.getInstance().border) {
			Lg.setState(GameState.BORDURE);
			Bukkit.broadcastMessage(prefix + " §cAttention : la bordure est désormais en mouvement");
			GameBorder task = new GameBorder();
			task.runTaskTimer(Lg.getInstance(), 20, 20);
		}
		
		timer++;
		
	}

}
