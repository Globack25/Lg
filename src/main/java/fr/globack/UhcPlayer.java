package fr.globack;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import fr.globack.role.Role;

public class UhcPlayer {
	
	private String name;
	private UUID uuid;
	private int kill = 0;
	
	public UhcPlayer(String name, UUID uuid) {
		this.name = name;
		this.uuid = uuid;
	}
	
	public String getName() {
		return name;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}
	
	public OfflinePlayer getOfflinePlayer() {
		return Bukkit.getOfflinePlayer(uuid);
	}
	
	public boolean isOnline() {
		if(getPlayer() != null) return true;
		return false;
	}
	
	public void addInRole(Role role) {
		getPlayer().sendMessage("§6[Lg] §9Vous êtes "  + role.getName());
		getPlayer().sendMessage(role.getDesc());
		role.getPlayerRole().add(getPlayer());
	}
	
	public void removeInRole() {
		for(Role role : Lg.role) {
			role.getPlayerRole().remove(getPlayer());
		}
	}
	
	public boolean hasRole() {
		for(Role role : Lg.role) {
			if(role.getPlayerRole().contains(getPlayer())) return true;
		}
		return false;
	}
	
	public Role getRole() {
		for(Role role : Lg.role) {
			if(role.getPlayerRole().contains(getPlayer())) return role;
		}
		return null;
	}
	
	public void addInRandomRole() {
		while(!hasRole()) {
			for(Role role : Lg.role) {
				if(role.getSize() < role.getMaxSize()) {
					addInRole(role);
					break;
				}
			}
		}
	}
	
	public int getKill() {
		return kill;
	}
	
	public void setKill(int kill) {
		this.kill = kill;
	}

}
