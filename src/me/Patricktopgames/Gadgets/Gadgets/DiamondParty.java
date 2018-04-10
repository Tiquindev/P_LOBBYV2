package me.Patricktopgames.Gadgets.Gadgets;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import me.Patricktopgames.Gadgets.EventManager.FireworkNMSHandler;
import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;

public class DiamondParty 
extends FireworkNMSHandler implements Listener{
	
	private void startParty(final Player p){
	    final Vector direction = Vector.getRandom();
	    direction.setX(direction.getX() - 0.5D);
	    direction.setY(0.6F);
	    direction.setZ(direction.getZ() - 0.5D);
	    final BukkitTask ftask = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable(){
	      public void run(){
	        try{
	          ItemStack paramDiamond = new ItemStack(Material.DIAMOND);
	          final Item paramItemDrop = p.getWorld().dropItem(p.getEyeLocation().add(0.0D, 0.0D, 0.0D), paramDiamond);
	          paramItemDrop.setVelocity(direction);
	          paramItemDrop.setPickupDelay(Integer.MAX_VALUE);
	          Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
	            public void run(){
	              paramItemDrop.remove();
	            }
	          }, 36L);
	          DiamondParty.this.playFirework(p.getLocation().add(0.0D, 4.0D, 0.0D), 
	            FireworkEffect.builder()
	            .withColor(Color.BLUE)
	            .withColor(Color.AQUA)
	            .withColor(Color.WHITE)
	            .withFade(Color.WHITE)
	            .with(FireworkEffect.Type.BURST)
	            .build());
	        }
	        catch (Exception e){
	          e.printStackTrace();
	        }
	      }
	    }, 1L, 5L);
	    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
	      public void run(){
	        ftask.cancel();
	      }
	    }, 240L);
	  }
	  
	  @EventHandler
	  public void paramPlayerUseDiamondParty(PlayerInteractEvent e){
	    Player p = e.getPlayer();
	    Action action = e.getAction();
	    if ((action != Action.RIGHT_CLICK_AIR) && (action != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	      return;
	    }
	    if(p.getItemInHand().getType() != Material.DIAMOND){
	    	return;
	    }
	    if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lDiamond Party §7(Clique Direito)")) {
		   if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
			Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L)));
	        startParty(p);
	      }else{
	        p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
	        p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	      }
	    }
	  }
	}