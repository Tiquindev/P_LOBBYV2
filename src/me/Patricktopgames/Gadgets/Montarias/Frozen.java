package me.Patricktopgames.Gadgets.Montarias;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.ItemStack;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.Gadgets.Montarias.MountHandler;

public class Frozen 
implements Listener{
	
	  @EventHandler
	  public void onFrozenMove(PlayerMoveEvent e){
	    if (e.getPlayer().isInsideVehicle()){
	      Player paramPlayer = e.getPlayer();
	      Entity paramEntity = paramPlayer.getVehicle();
	      if (paramEntity.getType() == null) {
	        return;
	      }
	      if ((paramEntity.getType() == EntityType.HORSE) && 
	        (paramEntity.hasMetadata("FrozenHorse")))
	      {
	        int i = 78;
	        int j = 0;
	        for (Block localBlock1 : Main.getUtilBlock().getInRadius(((Horse)MountHandler.pet.get(paramPlayer.getUniqueId())).getLocation(), 3.5D, true).keySet()) {
	          if ((paramPlayer.getLocation().getBlock().getType() != Material.WATER) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.STATIONARY_WATER) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.CHEST) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.SKULL) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.SNOW) && 
	            (paramPlayer.getLocation().getBlock().getType() != Material.SNOW_BLOCK) && 
	            (Main.getUtilBlock().solid(localBlock1)) && 
	            (!Main.getUtilBlock().blockToRestore.contains(localBlock1)))
	          {
	            Block localBlock2 = Main.getUtilBlock().getHighest(localBlock1.getWorld(), localBlock1.getLocation().getBlockX(), localBlock1.getLocation().getBlockZ());
	            if (!Main.getUtilBlock().blockToRestore.contains(localBlock2)){
	              Location localLocation = localBlock2.getLocation().add(0.0D, j, 0.0D);
	              Main.getUtilBlock().setBlockToRestore(localLocation.getBlock(), i, (byte)0, 4L, true, false, false);
	            }
	          }
	        }
	      }
	    }
	  }
	  
	  @EventHandler
	  public void onFrozenInventoryOpen(InventoryOpenEvent e){
	    if ((e.getInventory() instanceof HorseInventory)){
	      HorseInventory h = (HorseInventory)e.getInventory();
	      if (h.getSaddle().isSimilar(new ItemStack(Material.DIAMOND_BARDING))) {
	        e.setCancelled(true);
	      }
	    }
	  }
	}