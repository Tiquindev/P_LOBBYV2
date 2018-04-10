package me.Patricktopgames.lobby;

import org.bukkit.entity.Player;

import me.Patricktopgames.lobby.API.Pscorebord;;

public class Board {
	
	public static void setarBoard(Player p){
		Pscorebord score = new Pscorebord("§9§lLitleGames");
		
		score.addLinha("§3 ", 8);
		score.addLinha(" §fServidores: ", 7);
		score.addLinha("§3 ", 6);
		score.addLinha(" §fBedwars: §2§l✔", 5);
		score.addLinha(" §fKitPvP: §4§l✖", 4);
		score.addLinha(" §fSkywars: §4§l✖", 3);
		score.addLinha("§3", 2);
		score.addLinha("§7/score", 1);
		
		score.setScoreboard(p);
	}
	
	public static void setarNull(Player p){
		Pscorebord sc = new Pscorebord("");
		
		sc.setScoreboard(p);
	}
}