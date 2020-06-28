package fr.globack;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.globack.game.GameTask;
import fr.globack.role.Role;
import fr.globack.role.Roles;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerDeathTask extends BukkitRunnable {
	
	private int timer = 10;
	
	Player player;
	Location loc;
	List<ItemStack> it;
	Role role;
	Entity killer;
	
	public PlayerDeathTask(Player player, Location loc, List<ItemStack> it, Role role) {
		this.player = player;
		this.loc = loc;
		this.it = it;
		this.role = role;
	}
	
	public PlayerDeathTask(Player player, Location loc, List<ItemStack> it, Role role, Entity killer) {
		this.player = player;
		this.loc = loc;
		this.it = it;
		this.role = role;
		this.killer = killer;
	}
	
	@Override
	public void run() {
		
		if(timer == 9) {
			for(Role role : Lg.role) {
				if(role.getRole() == Roles.ANCIEN) {
					for(Player player : role.getPlayerRole()) {
						if(this.player == player) {
							if(Lg.getInstance().ancienRevive) {
								revive(player);
								Lg.getInstance().ancienRevive = false;
								cancel();
								return;
							}
						}
					}
				}
				if(role.getRole() == Roles.INFECT_PERE_DES_LOUPS) {
					for(Player players : role.getPlayerRole()) {
						if(players == this.player) {
							if(Lg.getInstance().infectRevive) {
								player.sendMessage("§eLe joueur " + players.getName() + " §eest mort");
								TextComponent info = new TextComponent("§fCliquez ici pour le réssusiter !");
								String cmd = "lg rev " + players.getName();
								info.setClickEvent(new ClickEvent(Action.RUN_COMMAND, cmd));
								player.spigot().sendMessage(info);
							}
						}
					}
				}
				if(role.getRole() == Roles.SORCIERE) {
					for(Player players : role.getPlayerRole()) {
						if(players == this.player) {
							if(Lg.getInstance().sorciereRevive) {
								player.sendMessage("§eLe joueur " + players.getName() + " §eest mort");
								TextComponent info = new TextComponent("§fCliquez ici pour le réssusiter !");
								String cmd = "lg re " + players.getName();
								info.setClickEvent(new ClickEvent(Action.RUN_COMMAND, cmd));
								player.spigot().sendMessage(info);
							}
						}
					}
				}
			}
		}
		
		if(timer == 0) {
			
			if(Lg.couple.contains(player)) {
				for(Player players : Lg.couple) {
					players.setHealth(0);
				}
			}
			
			for(Role role : Lg.role) {
				if(role.getPlayerRole().contains(player)) {
					for(Player players : Bukkit.getOnlinePlayers()) {
						players.sendMessage("§2Le village a perdu un de ses membres : §2§l" + player.getName());
						players.sendMessage("§2qui était " + role.getName());
					}
				}
				if(killer instanceof Player) {
					Player killer = (Player)this.killer;
					if(role.getPlayerRole().contains(player)) {
						if(role.getRole() == Roles.CHASSEUR_DE_PRIME) {
							if(GameTask.random != null && GameTask.random == this.player) {
								killer.setHealthScale(30);
								killer.sendMessage("§9Vous avez fait du bon boulaut, je vous récompense donc comme il se doit");
							}
						}
					}
				}
				if(Lg.modele == player) {
					if(role.getRole() == Roles.ENFANT_SAUVAGE) {
						for(Player players : role.getPlayerRole()) {
							for(UhcPlayer playerz : Lg.allPlayers) {
								if(players.getUniqueId() == playerz.getUuid()) {
									playerz.removeInRole();
									for(Role roles : Lg.role) {
										if(roles.getRole() == Roles.LOUP_GAROU) {
											playerz.addInRole(roles);
										}
									}
								}
							}
						}
					}
				}
			}
			for(Role role : Lg.role) {
				if(role.getPlayerRole().contains(player)) {
					role.getPlayerRole().remove(player);
				}
			}
			
			Lg.death.remove(player);
			player.setGameMode(GameMode.SPECTATOR);
			cancel();
			
		}
		
		timer--;
		
	}
	
	public void revive(Player player) {
		
		player.sendMessage("§eVous avez été réssusité !");
		
		String w = "world";
		World world = Bukkit.getWorld(w);
		
		Random r = new Random();
		
		double x = r.nextInt(1000);
		double y = 120;
		double z = - r.nextInt(1000);
		
		player.setFoodLevel(20);
		player.setHealth(player.getMaxHealth());
		
		Location loc = new Location(world, x, y, z);
		
		player.teleport(loc);
		
		player.setGameMode(GameMode.SURVIVAL);
		
		Lg.death.remove(player);
		cancel();
	}
	
	public void reviveInfect(Player player) {
		
		for(Role role : Lg.role) {
			if(role.getPlayerRole().contains(player)) {
				role.getPlayerRole().remove(player);
			}
			if(role.getRole() == Roles.LOUP_GAROU) {
				for(Player players : role.getPlayerRole()) {
					players.sendMessage("§cUn nouveau joueur a rejoint le groupe /lg role");
				}
				role.getPlayerRole().add(player);
			}
		}
		
		player.sendMessage("§eVous avez été infécté par l'Infect Père des Loups");
		player.sendMessage("§9Vous êtes donc maintenant Loup Garou, Pour voir la liste");
		player.sendMessage("§9des Loups-Garou, /lg role");
		
		String w = "world";
		World world = Bukkit.getWorld(w);
		
		Random r = new Random();
		
		double x = r.nextInt(1000);
		double y = 120;
		double z = - r.nextInt(1000);
		
		player.setFoodLevel(20);
		player.setHealth(player.getMaxHealth());
		
		Location loc = new Location(world, x, y, z);
		
		player.teleport(loc);
		
		player.setGameMode(GameMode.SURVIVAL);
		
		Lg.death.remove(player);
		cancel();
		
	}

}
