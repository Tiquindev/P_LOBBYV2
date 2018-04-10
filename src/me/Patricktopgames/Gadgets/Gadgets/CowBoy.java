package me.Patricktopgames.Gadgets.Gadgets;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class CowBoy 
implements Listener{
	
	  @EventHandler
	  public void paramPlayerUseCowBoy(PlayerInteractEntityEvent e){
	    if (((e.getRightClicked() instanceof Player)) && ((e.getPlayer() instanceof Player)))
	    {
	      Player pc = (Player)e.getRightClicked();
	      Player p = e.getPlayer();
		  if(p.getItemInHand().getType() != Material.CACTUS){
		   	return;
		  }
	      if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lCowBoy §7(Clique Direito)")) {
	        pc.setPassenger(p);
	      }
	    }
	  }
}