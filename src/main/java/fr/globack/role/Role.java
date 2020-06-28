package fr.globack.role;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Role {
	
	private String name;
	private RoleCategory category;
	private RoleWin winPossible;
	private Roles role;
	private String desc;
	private List<Player> playerRole = new ArrayList<>();
	private int maxSize;
	
	public Role(String name, String desc, int maxSize, RoleCategory category, RoleWin win, Roles role) {
		this.name = name;
		this.category = category;
		this.winPossible = win;
		this.role = role;
		this.desc = desc;
		this.maxSize = maxSize;
	}
	
	public RoleCategory getCategory() {
		return category;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public Roles getRole() {
		return role;
	}
	
	public RoleWin getWinPossible() {
		return winPossible;
	}
	
	public List<Player> getPlayerRole() {
		return playerRole;
	}
	
	public int getSize() {
		return playerRole.size();
	}

}
