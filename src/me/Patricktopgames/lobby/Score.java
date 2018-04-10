package me.Patricktopgames.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Patricktopgames.lobby.API.Manager;

public class Score 
implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		if(!(sender instanceof Player)){
			return true;
		}
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("score")){
			Manager.configuraçao(p);
		}
		return false;
	}
}