package me.Patricktopgames.Gadgets.Montarias;

import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.Gadgets.Montarias.MountHandler;
import me.Patricktopgames.Gadgets.Util.UtilParticle;

public class Infernal 
implements Listener{

	  public int random(Integer[] integers){
	    Random rand = new Random();
	    return integers[rand.nextInt(integers.length)].intValue();
	  }
	  
	  @EventHandler
	  public void onInfernalMove(PlayerMoveEvent e){
	    if (e.getPlayer().isInsideVehicle()){
	      Player paramPlayer = e.getPlayer();
	      Entity paramEntity = paramPlayer.getVehicle();
	      if (paramEntity.getType() == null) {
	        return;
	      }
	      if ((paramEntity.getType() == EntityType.HORSE) && 
	        (paramEntity.hasMetadata("InfernalHorse"))){
	        new UtilParticle(UtilParticle.ParticleType.FLAME, 0.10000000149011612D, 4, 0.30000001192092896D).sendToLocation(paramEntity.getLocation());
	        int i = 159;
	        int ie = random(new Integer[] { Integer.valueOf(1), Integer.valueOf(4), Integer.valueOf(14) });
	        for (Block localBlock1 : Main.getUtilBlock().getInRadius(((Horse)MountHandler.pet.get(paramPlayer.getUniqueId())).getLocation(), 3.5D, true).keySet()) {
	          if ((paramPlayer.getLocation().getBlock().getType() != Material.WATER) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.STATIONARY_WATER) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.CHEST) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.SKULL) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.SNOW) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.CLAY_BRICK) && 
	            (Main.getUtilBlock().solid(localBlock1)) && 
	            (!Main.getUtilBlock().blockToRestore.contains(localBlock1))){
	            Block localBlock2 = Main.getUtilBlock().getHighest(localBlock1.getWorld(), localBlock1.getLocation().getBlockX(), localBlock1.getLocation().getBlockZ());
	            if (!Main.getUtilBlock().blockToRestore.contains(localBlock2)){
	              Location localLocation = localBlock2.getLocation();
	              byte b = (byte)ie;
	              Main.getUtilBlock().setBlockToRestore(localLocation.subtract(0.0D, 1.0D, 0.0D).getBlock(), i, b, 4L, true, false, false);
	            }
	          }
	        }
	      }
	    }
	  }
	}