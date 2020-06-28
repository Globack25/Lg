package fr.globack.game;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.globack.UhcPlayer;
import fr.globack.Lg;
import fr.globack.role.Role;
import fr.globack.role.RoleCategory;
import fr.globack.role.Roles;

public class GameDayCircle extends BukkitRunnable {

	private int timer = 0;
	
	@Override
	public void run() {
		
		timer++;
		
		if(timer == 0) {
			GameType.setType(GameType.DAY);
		}
		
		addDay(19, 20, 1, 2);
		
		addType(5, GameType.NIGHT);
		addType(10, GameType.DAY);
		addType(15, GameType.NIGHT);
		addType(20, GameType.DAY);
		
		addDay(39, 40, 2, 3);
		
		addType(25, GameType.NIGHT);
		addType(30, GameType.DAY);
		addType(35, GameType.NIGHT);
		addType(40, GameType.DAY);
		
		addDay(59, 60, 3, 4);
		
		addType(45, GameType.NIGHT);
		addType(50, GameType.DAY);
		addType(55, GameType.NIGHT);
		addType(60, GameType.DAY);
		
		addDay(79, 80, 4, 5);
		
		addType(65, GameType.NIGHT);
		addType(70, GameType.DAY);
		addType(75, GameType.NIGHT);
		addType(80, GameType.DAY);
		
		addDay(99, 100, 5, 6);
		
		addType(85, GameType.NIGHT);
		addType(90, GameType.DAY);
		addType(95, GameType.NIGHT);
		addType(100, GameType.DAY);
		
		addDay(119, 120, 6, 7);
		
		addType(105, GameType.NIGHT);
		addType(110, GameType.DAY);
		addType(115, GameType.NIGHT);
		addType(120, GameType.DAY);
		
		addDay(139, 140, 7, 8);
		
		addType(125, GameType.NIGHT);
		addType(130, GameType.DAY);
		addType(135, GameType.NIGHT);
		addType(140, GameType.DAY);
		
		addDay(159, 160, 8, 9);
		
		addType(145, GameType.NIGHT);
		addType(150, GameType.DAY);
		addType(155, GameType.NIGHT);
		addType(160, GameType.DAY);
		
		addDay(179, 180, 9, 10);
		
		addType(165, GameType.NIGHT);
		addType(170, GameType.DAY);
		addType(175, GameType.NIGHT);
		addType(180, GameType.DAY);
		
		addDay(199, 200, 10, 11);
		
		addType(185, GameType.NIGHT);
		addType(190, GameType.DAY);
		addType(195, GameType.NIGHT);
		addType(200, GameType.DAY);
		
		addDay(219, 220, 11, 12);
		
		addType(205, GameType.NIGHT);
		addType(210, GameType.DAY);
		addType(215, GameType.NIGHT);
		addType(220, GameType.DAY);
		
		addDay(239, 240, 12, 13);
		
		addType(225, GameType.NIGHT);
		addType(230, GameType.DAY);
		addType(235, GameType.NIGHT);
		addType(240, GameType.DAY);
		
		addDay(259, 260, 13, 14);
		
		addType(245, GameType.NIGHT);
		addType(250, GameType.DAY);
		addType(255, GameType.NIGHT);
		addType(260, GameType.DAY);
		
		addDay(279, 280, 14, 15);
		
		addType(265, GameType.NIGHT);
		addType(270, GameType.DAY);
		addType(275, GameType.NIGHT);
		addType(280, GameType.DAY);
		
		addDay(299, 300, 15, 16);
		
		addType(285, GameType.NIGHT);
		addType(290, GameType.DAY);
		addType(295, GameType.NIGHT);
		addType(300, GameType.DAY);
		
	}
	
	public void addDay(int holdTime, int newTime, int currentDay, int newDay) {
		
		if(timer == (holdTime * 60) + 30 ) {
			for(UhcPlayer player : Lg.allPlayers) {
				player.getPlayer().sendMessage("§eFin du jour " + currentDay + " dans 30 secondes");
			}
		}
		
		if(timer == newTime * 60) {
			for(UhcPlayer player : Lg.allPlayers) {
				player.getPlayer().sendMessage("§eFin du jour " + currentDay + ", Début du jour " + newDay);
			}
			for(Role role : Lg.role) {
				if(role.getRole() == Roles.VOYANTE) {
					for(Player player : role.getPlayerRole()) {
						player.sendMessage("§9Vous pouvez désormais espionnez un joueur avec la commande /lg voir <player>");
					}
				}
				if(role.getRole() == Roles.ANCIEN) {
					for(Player player : role.getPlayerRole()) {
						if(Lg.getInstance().ancienRevive) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
						}
					}
				}
				if(role.getRole() == Roles.SALVATEUR) {
					for(Player player : role.getPlayerRole()) {
						player.sendMessage("§9Vous pouvez désormais protéger un joueur avec la commande /lg protect <player>");
					}
				}
				if(role.getRole() == Roles.MONTREUR_DOURS) {
					for(Player player : role.getPlayerRole()) {
						List<Entity> distance = player.getNearbyEntities(30, 30, 30);
						for(Entity players : distance) {
							if(!(players instanceof Player)) {
								distance.remove(players);
							}
							for(Entity playerss : distance) {
								Player playe = (Player) playerss;
								for(Role roles : Lg.role) {
									if(roles.getCategory() == RoleCategory.LG) {
										if(!roles.getPlayerRole().contains(playe)) {
											distance.remove(playe);
										}
									}
								}
							}
							for(int i = 0; i < distance.size(); i++) {
								Bukkit.broadcastMessage("§6§lGrrrrrrrr");
							}
						}
					}
				}
			}
		}
		
	}
	
	public void addType(int time, GameType type) {
		if(timer == time * 60) {
			String types = "Jour";
			switch(type) {
			
			case DAY:
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set day");
				types = "Jour";
				break;
				
			case NIGHT:
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set night");
				types = "Nuit";
				/*
				 * for(Role role : LoupGarou.role) {
					 * if(role.getRole() == RoleType.PETITE_FILLE) {
						for(Player player : role.getPlayerRole()) {
							List<Entity> players = player.getNearbyEntities(100, 100, 100);
							for(Entity p : players) {
								if(p instanceof Player) {
									if(players.size() == 0) {
										player.sendMessage("§9Joueurs dans les environs : Aucun");
									} else {
										for(int i = 0; i < players.size(); i++) {
											String playersy = players.get(i).getName();
											String[] playerss = playersy.split(" ");
											player.sendMessage("§9Joueurs dans les environs : " + playerss);
										}
									}
								} else {
									players.remove(p);
								}
							}
						}
					}
				}*/
				break;
			
			default:
				break;
			
			}
			for(UhcPlayer player : Lg.allPlayers) {
				for(PotionEffect a : player.getPlayer().getActivePotionEffects()) {
					player.getPlayer().removePotionEffect(a.getType());
				}
				player.getPlayer().sendMessage("§9Il fait désormais " + types);
			}
			GameType.setType(type);
		}
	}
}
