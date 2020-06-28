package fr.globack;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.globack.commands.CommandGame;
import fr.globack.game.GameTask;
import fr.globack.listener.DropListener;
import fr.globack.listener.OnDamage;
import fr.globack.listener.PlayerListener;
import fr.globack.listener.PlayerMove;
import fr.globack.listener.PortailListener;
import fr.globack.listener.PotionListener;
import fr.globack.listener.PrepareItemCraft;
import fr.globack.listener.WaitListener;
import fr.globack.role.Role;
import fr.globack.role.RoleCategory;
import fr.globack.role.RoleWin;
import fr.globack.role.Roles;
import fr.globack.scoreboard.FastBoard;

public class Lg extends JavaPlugin {
	
	public static List<UhcPlayer> allPlayers = new ArrayList<>();
	private static Lg instance;
	public static List<Role> role = new ArrayList<>();
	public static List<Player> death = new ArrayList<>();
	public static List<Player> couple = new ArrayList<>();
	public static Player salva = null;
	public static Player modele = null;
	
	//pvp
	public int pvp = 20 * 60;
	
	//border
	public int border = 40 * 60;
	public double maxBorder = 2000 * 2;
	public double minBorder = 200 * 2;
	public double perSecond = 1 * 2;
	
	//potion
	public boolean potionEnable = true;
	public boolean potionII = false;
	public boolean force = true;
	
	//drop
	public int flintDrop = 50;
	public int appleDrop = 50;
	
	public boolean infectRevive = true;
	public boolean ancienRevive = true;
	public boolean sorciereRevive = true;
	
	//nether
	public boolean nether = true;
	
	//player
	public int maxPlayer = 40;
	
	public static GameState state = GameState.WAIT;
	
	public static GameState getState() {
		return state;
	}
	
	public static void setState(GameState state) {
		Lg.state = state;
	}
	
	public static boolean isState(GameState state) {
		return Lg.state == state;
	}
	
	public static final Map<UUID, FastBoard> boards = new HashMap<>();
	
	public static Lg getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		Lg.setState(GameState.WAIT);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new WaitListener(), this);
		pm.registerEvents(new PrepareItemCraft(), this);
		pm.registerEvents(new DropListener(), this);
		pm.registerEvents(new PotionListener(), this);
		pm.registerEvents(new PortailListener(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new OnDamage(), this);
		
		WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
		border.setCenter(new Location(Bukkit.getWorld("world"), 0, 100, 0));
		border.setSize(maxBorder);
		
		getCommand("game").setExecutor(new CommandGame());
		
		Bukkit.getWorld("world").setPVP(false);
		Bukkit.getWorld("world").setDifficulty(Difficulty.HARD);
		Bukkit.getWorld("world").setAutoSave(false);
		Bukkit.getWorld("world").setGameRuleValue("naturalRegeneration", "false");
		
		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

			@Override
			public void run() {
				for(Entry<UUID, FastBoard> e : boards.entrySet()) {
					  Player player = Bukkit.getPlayer(e.getKey());
					  updateBoard(e.getValue(), player);
				}
			}
			
		}, 5, 5);
		
	}
	
	public void updateBoard(FastBoard board, Player player) {
		
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		String time = format.format(new Date(GameTask.timer * 1000));
		UhcPlayer uhcPlayer = null;
		
		for(UhcPlayer players : allPlayers) {
			if(players.getUuid() == player.getUniqueId()) {
				uhcPlayer = players;
			}
		}
		
		String pvp = "§c✖";
		String border = "§c✖";
		
		if(Bukkit.getWorld("world").getPVP()) {pvp = "§a✔";} else {pvp = "§c✖";}
		
		if(Bukkit.getWorld("world").getWorldBorder().getSize() != maxBorder) {border = "§a✔";} else {border = "§c✖";}
		
		if(uhcPlayer != null) {
			board.updateLines(
	        		"",
	        		"§eJoueur(s) : §b" + allPlayers.size(),
	        		"",
	        		"§eTimer : §b" + time,
	        		"§7-----------------",
	        		"§ePvP : " + pvp,
	        		"§eBordure :  " + border,
	        		"§7-----------------",
	                "§eKill : §b" + uhcPlayer.getKill(),
	                "",
	                "§6play.vinlandmc.fr");
		}
	}
	
	public void loadRole() {
		Role loupGarou = new Role("Loup-Garou", "", allPlayers.size() / 4, RoleCategory.LG, RoleWin.LG, Roles.LOUP_GAROU);
		role.add(loupGarou);
		Role loupGarouBlanc = new Role("Loup-Garou Blanc", "", 1, RoleCategory.LG, RoleWin.SOLO, Roles.LOUP_GAROU_BLANC);
		role.add(loupGarouBlanc);
		Role infectPereDesLoups = new Role("Infect Pere Des Loups", "", 1, RoleCategory.LG, RoleWin.LG, Roles.INFECT_PERE_DES_LOUPS);
		role.add(infectPereDesLoups);
		
		/*if(config.getBoolean("loupGarouFeutre") == true) {
			Role lgFeutre = new Role("Loup-Garou Feutre", "", 1, RoleCategory.MECHANT, RoleWin.LG);
			LoupGarou.role.add(lgFeutre);
		}
		
		if(config.getBoolean("loupGarouPerfilde") == true) {
			Role lgPerfilde = new Role("Loup-Garou Perfilde", "", 1, RoleCategory.MECHANT, RoleWin.LG);
			LoupGarou.role.add(lgPerfilde);
		}*/
		
		Role vilainPetitLoup = new Role("Vilain Petit Loup", "", 1, RoleCategory.LG, RoleWin.LG, Roles.VILAIN_PETIT_LOUP);
		role.add(vilainPetitLoup);
		
		//Village
		
		Role petiteFille = new Role("Petite Fille", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.PETITE_FILLE);
		role.add(petiteFille);
		Role sorciere = new Role("Sorciere", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.SORCIERE);
		role.add(sorciere);
		Role voyante = new Role("Voyante", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.VOYANTE);
		role.add(voyante);
		Role montreurDours = new Role("Montreur d'Ours", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.MONTREUR_DOURS);
		role.add(montreurDours);
		Role renard = new Role("Renard", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.RENARD);
		role.add(renard);
		Role salvateur = new Role("Salvateur", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.SALVATEUR);
		role.add(salvateur);
		Role Cupidon = new Role("Cupidon", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.CUPIDON);
		role.add(Cupidon);
		Role ancien = new Role("Ancien", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.ANCIEN);
		role.add(ancien);
		Role simpleVillageois = new Role("Simple Villageois", "", 2, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.SIMPLE_VILLAGEOIS);
		role.add(simpleVillageois);
		
		//Autre
		Role enfantSauvage = new Role("Enfant Sauvage", "", 1, RoleCategory.VILLAGE, RoleWin.VILLAGE, Roles.ENFANT_SAUVAGE);
		role.add(enfantSauvage);
		Role assasin = new Role("Assasin", "", 1, RoleCategory.SOLO, RoleWin.SOLO, Roles.ASSASSIN);
		role.add(assasin);
		Role voleur = new Role("Voleur", "", 1, RoleCategory.SOLO, RoleWin.SOLO, Roles.VOLEUR);
		role.add(voleur);
		Role chasseurDePrime = new Role("Chasseur de Prime", "", 1, RoleCategory.SOLO, RoleWin.SOLO, Roles.CHASSEUR_DE_PRIME);
		role.add(chasseurDePrime);
	}

}
