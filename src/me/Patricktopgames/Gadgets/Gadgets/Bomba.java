package me.Patricktopgames.Gadgets.Gadgets;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import me.Patricktopgames.Gadgets.EventManager.ParticleEffect;
import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;
import me.Patricktopgames.Gadgets.Util.UtilLocations;
import me.Patricktopgames.Gadgets.Util.UtilMath;

public class Bomba 
implements Listener{

	 List<Location> saveParam = new ArrayList();
	  
	  @EventHandler
	  public void paramPlayerUseBomb(PlayerInteractEvent e){
	    final Player p = e.getPlayer();
	    Action action = e.getAction();
	    if ((action != Action.RIGHT_CLICK_AIR) && (action != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	      return;
	    }
	    if(p.getItemInHand().getType() != Material.CLAY_BALL){
	    	return;
	    }
	    if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lBomba §7(Clique Direito)")) {
	    	if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
	    	Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L)));
	        ItemStack paramBomba = new ItemStack(Material.CLAY_BALL);
	        final Item paramItemDrop = p.getWorld().dropItem(p.getEyeLocation().add(0.0D, 0.0D, 0.0D), paramBomba);
	        paramItemDrop.setVelocity(p.getEyeLocation().getDirection().multiply(0.8D).normalize());
	        paramItemDrop.setPickupDelay(Integer.MAX_VALUE);
	        final BukkitTask repeatingID = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable(){
	          public void run(){
	            paramItemDrop.getWorld().playSound(paramItemDrop.getLocation(), Sound.BURP, 2.5F, 12.0F);
	          }
	        }, 1L, 8L);
	        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
	          public void run(){
	            Bomba.this.saveParam.add(paramItemDrop.getLocation());
	            repeatingID.cancel();
	            ParticleEffect.EXPLOSION_HUGE.display(1.0F, 1.0F, 1.0F, 3.0F, 18, paramItemDrop.getLocation(), 35.0D);
	            paramItemDrop.getWorld().playSound(paramItemDrop.getLocation(), Sound.EXPLODE, 5.0F, 1.0F);
	            UtilLocations near = new UtilLocations();
	            Entity[] arrayOfEntity;
	            int j = (arrayOfEntity = near.getNearbyEntities(paramItemDrop.getLocation(), 10)).length;
	            double d1;
	            for (int i = 0; i < j; i++){
	              Entity nears = arrayOfEntity[i];
	              if ((nears instanceof Player)){
	                Player pn = (Player)nears;
	                d1 = paramItemDrop.getLocation().getX() - p.getLocation().getX();
	                double d2 = paramItemDrop.getLocation().getY() - p.getLocation().getY();
	                double d3 = paramItemDrop.getLocation().getZ() - p.getLocation().getZ();
	                double d4 = Math.atan2(d3, d1);
	                double d5 = Math.atan2(Math.sqrt(d3 * d3 + d1 * d1), d2) + 3.141592653589793D;
	                double d6 = Math.sin(d5) * Math.cos(d4);
	                double d7 = Math.sin(d5) * Math.sin(d4);
	                double d8 = Math.cos(d5);
	                
	                Vector localVector = new Vector(d6, d8, d7);
	                p.setVelocity(localVector.multiply(1.321483642374632D).add(new Vector(0.0D, 1.4397268432482635D, 0.0D)));
	                pn.setVelocity(new Vector(UtilMath.random.nextInt(5), UtilMath.random.nextInt(2), UtilMath.random.nextInt(5)));
	              }
	            }
	            byte b = 15;
	            Location localLocation = paramItemDrop.getLocation();
	            for (Block localBlock : Main.getUtilBlock().getInRadius(localLocation, 3.5D).keySet()) {
	              if ((Main.getUtilBlock().solid(localBlock)) && 
	                (!Main.getUtilBlock().blockToRestore.contains(localBlock))) {
	                if (localBlock.getType() != Material.CLAY_BRICK) {
	                	Main.getUtilBlock().setBlockToRestore(localBlock, 159, b, 4L, true, false, false);
	                }
	              }
	            }
	            paramItemDrop.remove();
	          }
	        }, 100L);
	      }else{
		      p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
		      p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	      }
	    }
	  }
	}