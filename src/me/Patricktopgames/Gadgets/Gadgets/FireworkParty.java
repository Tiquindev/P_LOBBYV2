package me.Patricktopgames.Gadgets.Gadgets;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;
import me.Patricktopgames.Gadgets.Util.UtilFirework;

public class FireworkParty 
implements Listener{

	@EventHandler
	  public void paramPlayerUseFirework(PlayerInteractEvent e){
	    Player p = e.getPlayer();
	    Action action = e.getAction();
	    Location local = p.getLocation();
	    if ((action != Action.RIGHT_CLICK_AIR) && (action != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if(p.getItemInHand().getType() != Material.FIREWORK){
	    	return;
	    }
	    if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lFirework Party §7(Clique Direito)")) {
	      e.setCancelled(true);
	      if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	        return;
	      }
	       if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
	    	 Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L)));
	        List<Location> paramParty = Main.getUtilLocation().getSphere(local, 7, 1, true, false, 0);
	        for (final Location loc : paramParty){
	          final int taskId = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable(){
	            public void run(){
	              UtilFirework.spawnRandomFirework(loc);
	            }
	          }, 0L, 60L).getTaskId();
	          Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
	            public void run(){
	              Bukkit.getScheduler().cancelTask(taskId);
	            }
	          }, 160L);
	        }
	      }else{
		      p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
		      p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	      }
	    }
	  }
	}