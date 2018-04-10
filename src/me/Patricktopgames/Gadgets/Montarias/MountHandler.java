package me.Patricktopgames.Gadgets.Montarias;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MountHandler
implements Listener{
	
	  public static HashMap<UUID, Horse> pet = new HashMap();
	  
	  public static boolean HasPet(Player paramPlayer){
	    if (pet.containsKey(paramPlayer.getUniqueId())) {
	      return true;
	    }
	    return false;
	  }
	  
	  public static void removePlayerMount(Player p){
	    if (pet.containsKey(p.getUniqueId())){
	      ((Horse)pet.get(p.getUniqueId())).remove();
	      pet.remove(p.getUniqueId());
	      pet.remove(p.getUniqueId());
	    }
	    else {}
	  }
	  
	  public static boolean isMountOwner(Player paramPlayer, Horse paramHorse){
	    Horse localHorse = (Horse)pet.get(paramPlayer.getUniqueId());
	    return (localHorse != null) && (localHorse.equals(paramHorse));
	  }
	  
	  @EventHandler
	  public void OnPlayerQuit(PlayerQuitEvent paramPlayerQuitEvent){
	    Player paramPlayer = paramPlayerQuitEvent.getPlayer();
	    if (HasPet(paramPlayer)) {
	      removePlayerMount(paramPlayer);
	    }
	  }
	  
	  @EventHandler
	  public void onPlayerDamage(EntityDamageEvent e){
	    if ((e.getEntity() instanceof Horse)){
	      Horse h = (Horse)e.getEntity();
	      if (h.hasMetadata("FrozenHorse")) {
	        e.setCancelled(true);
	      }
	      if (h.hasMetadata("InfernalHorse")) {
	        e.setCancelled(true);
	      }
	    }
	  }
}