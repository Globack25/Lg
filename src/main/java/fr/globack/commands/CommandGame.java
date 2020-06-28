package fr.globack.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.globack.GameState;
import fr.globack.Lg;
import fr.globack.game.GameLaunch;

public class CommandGame implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if(sender.isOp()) {
				if(Lg.isState(GameState.WAIT)) {
					GameLaunch launch = new GameLaunch();
					launch.runTaskTimer(Lg.getInstance(), 20, 20);
					return true;
				}
				if(Lg.isState(GameState.PRE_GAME)) {
					sender.sendMessage("§cErreur : le lancement est déjà en cour");
					return true;
				}
				sender.sendMessage("§cErreur : une partie est déjà en cour");
			}
		}
		return false;
	}

}
