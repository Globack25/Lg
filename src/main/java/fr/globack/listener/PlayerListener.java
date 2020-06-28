package fr.globack.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.globack.GameState;
import fr.globack.UhcPlayer;
import fr.globack.Lg;
import fr.globack.game.GameLaunch;
import fr.globack.game.GameManager;
import fr.globack.scoreboard.FastBoard;
import net.md_5.bungee.api.ChatColor;

public class PlayerListener implements Listener {
	
	private final String prefix = "§6[LgUhc] ";
	public List<UhcPlayer> allPlayers = new ArrayList<>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		event.setJoinMessage(null);
		
		if(Bukkit.getOnlinePlayers().size() >= Lg.getInstance().maxPlayer) {
			player.kickPlayer("Server full");
		}
		
        FastBoard board = new FastBoard(player);
        board.updateTitle(ChatColor.YELLOW + "VinLandMc - LgUhc");
        Lg.boards.put(player.getUniqueId(), board);
		
		if(Lg.isState(GameState.WAIT) || Lg.isState(GameState.PRE_GAME)) {
			player.setLevel(0);
			event.setJoinMessage(prefix + "§a" + player.getName() + " §aa rejoint la partie !");
			player.setGameMode(GameMode.ADVENTURE);
			UhcPlayer uhcPlayer = new UhcPlayer(player.getName(), player.getUniqueId());
			Lg.allPlayers.add(uhcPlayer);
			allPlayers.add(uhcPlayer);
	        player.setHealth(20);
	        player.setFoodLevel(20);
	        player.getInventory().clear();
	        player.getInventory().setHelmet(null);
	        player.getInventory().setChestplate(null);
	        player.getInventory().setLeggings(null);
	        player.getInventory().setBoots(null);
			player.teleport(new Location(Bukkit.getWorld("world"), 0, 170, 0));
			
			if(Lg.allPlayers.size() == 10) {
				GameLaunch launch = new GameLaunch();
				launch.runTaskTimer(Lg.getInstance(), 20, 20);
			}
			
		}
		
		if(Lg.isState(GameState.GAME)) {
			for(UhcPlayer players : Lg.allPlayers) {
				if(players.getUuid() == player.getUniqueId()) {
					event.setJoinMessage(prefix + "§a" + player.getName() + " §aest revenu dans la partie");
					return;
				}
			}
			player.setGameMode(GameMode.SPECTATOR);
		}
		
		if(Lg.isState(GameState.BORDURE) || Lg.isState(GameState.FINISH)) {
			player.setGameMode(GameMode.SPECTATOR);
		}
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		event.setQuitMessage(null);
		
		FastBoard board = Lg.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
		
		if(Lg.isState(GameState.WAIT) || Lg.isState(GameState.PRE_GAME)) {
			event.setQuitMessage(prefix + "§c" + player.getName() + " §ca quitté la partie");
			
			for(UhcPlayer players : allPlayers) {
				if(players.getUuid() == player.getUniqueId()) {
					Lg.allPlayers.remove(players);
					players.removeInRole();
				}
			}

		}
		
		if(Lg.isState(GameState.GAME)) {
			event.setQuitMessage(prefix + "§c" + player.getName() + " §ca quitté la partie");
		}
		
		if(Lg.isState(GameState.BORDURE) || Lg.isState(GameState.FINISH)) {
			event.setQuitMessage(prefix + "§c" + player.getName() + " §ca quitté la partie");
			GameManager.checkWin();
			for(UhcPlayer players : allPlayers) {
				if(players.getUuid() == player.getUniqueId()) {
					Lg.allPlayers.remove(players);
					players.removeInRole();
				}
			}
		}
		
	}

}