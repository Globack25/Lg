package fr.globack.role;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.globack.Lg;
import fr.globack.game.GameType;

public class RoleEffect extends BukkitRunnable {

	@Override
	public void run() {
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(Lg.salva != null && player == Lg.salva) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 0, Integer.MAX_VALUE, false, false));
			}
		}
		
		if(GameType.isType(GameType.NIGHT)) {
			for(Role role : Lg.role) {
				if(role.getCategory() == RoleCategory.LG) {
					for(Player player : role.getPlayerRole()) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
					}
					if(role.getRole() == Roles.VILAIN_PETIT_LOUP) {
						for(Player player : role.getPlayerRole()) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
						}
					}
				}
			}
		}
		
		if(GameType.isType(GameType.DAY)) {
			for(Role role : Lg.role) {
				if(role.getCategory() == RoleCategory.SOLO) {
					if(role.getRole() == Roles.ASSASSIN) {
						for(Player player : role.getPlayerRole()) {
							player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false));
						}
					}
				}
			}
		}
		
		if(GameType.isType(GameType.DAY) || GameType.isType(GameType.NIGHT)) {
			for(Role role : Lg.role) {
				if(role.getCategory() == RoleCategory.LG) {
					for(Player player : role.getPlayerRole()) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
					}
				}
				if(role.getRole() == Roles.VOYANTE || role.getRole() == Roles.PETITE_FILLE) {
					for(Player player : role.getPlayerRole()) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
					}
				
				}
				if(role.getRole() == Roles.RENARD) {
					for(Player player : role.getPlayerRole()) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false));
					}
				}
				if(role.getRole() == Roles.ANCIEN) {
					for(Player player : role.getPlayerRole()) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
					}
				}
				if(role.getRole() == Roles.VOLEUR) {
					for(Player player : role.getPlayerRole()) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));
					}
				}
			}
		}
		
	}

}
