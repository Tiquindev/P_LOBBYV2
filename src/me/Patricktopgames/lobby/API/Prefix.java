package me.Patricktopgames.lobby.API;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import me.Patricktopgames.lobby.Main;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Prefix implements Listener {

	public static void startUpdate(){
	    new BukkitRunnable() {
	      public void run(){
	 		 Player[] arrayOfPlayer;
			 int j;
			 int i;
			  j = Bukkit.getOnlinePlayers().size();
		       for (i = 0; i < j; i++){
	          for (Player p : Bukkit.getOnlinePlayers()){
	        	  Update(p.getScoreboard());
	          }
	        }
	      }
	    }.runTaskTimer(Main.plugin, 0L, 100L);
	  }
	  
	  public static void Update(final Scoreboard sb){
	    Thread th = new Thread(new Runnable(){
	      public void run(){
	        try{
		 		 Player[] arrayOfPlayer;
				 int j;
				 int i;
				  j = Bukkit.getOnlinePlayers().size();
			       for (i = 0; i < j; i++){
		          for (Player p : Bukkit.getOnlinePlayers()){
			            PermissionUser pex = PermissionsEx.getUser(p);
			            String[] arrayOfString;
			            int m = (arrayOfString = pex.getGroupsNames()).length;
			            for (int k = 0; k < m; k++){
			              String g = arrayOfString[k];
			              checkTeams(sb);
			              if (sb.getTeam(g) != null) {
			                sb.getTeam(g).addPlayer(p);
			              }
			            }
		          }
	          }
	        }
	        catch (Exception localException) {}
	      }
	    });
	    th.start();
	  }
	  
	  public static void checkTeams(Scoreboard sb){
	    try{
	      if (sb.getTeam("a") == null){
	        sb.registerNewTeam("a");
	        sb.getTeam("a").setPrefix("§4[Master] §4");
	      }else{
	        sb.getTeam("a").setPrefix("§4[Master] §4");
	      }
	      if (sb.getTeam("b") == null){
	        sb.registerNewTeam("b");
	        sb.getTeam("b").setPrefix("§d[Dev] ");
	      }
	      if (sb.getTeam("c") == null){
	        sb.registerNewTeam("c");
	        sb.getTeam("c").setPrefix("§b[Builder] ");
	      }
	      if (sb.getTeam("d") == null){
	        sb.registerNewTeam("d");
	        sb.getTeam("d").setPrefix("§c[Admin] ");
	      }
	      if (sb.getTeam("e") == null){
	        sb.registerNewTeam("e");
	        sb.getTeam("e").setPrefix("§2[Moderador] ");
	      }
	      if (sb.getTeam("f") == null){
	        sb.registerNewTeam("f");
	        sb.getTeam("f").setPrefix("§9[Ajudante] ");
	      }
	      if (sb.getTeam("k") == null){
	        sb.registerNewTeam("k");
	        sb.getTeam("k").setPrefix("§7");
	      }
	    }
	    catch (Exception e){
	      e.printStackTrace();
	    }
	  }
}