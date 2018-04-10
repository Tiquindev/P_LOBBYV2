package me.Patricktopgames.Gadgets.Gadgets;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
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

import me.Patricktopgames.Gadgets.EventManager.ParticleEffect;
import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;
import me.Patricktopgames.Gadgets.Util.UtilLag;
import me.Patricktopgames.Gadgets.Util.UtilMath;

public class SmokeBomb
implements Listener{
	    
	    @EventHandler
	    public void paramPlayerUseSmoke(PlayerInteractEvent e){
	      Player p = e.getPlayer();
	      Action action = e.getAction();
	      if ((action != Action.RIGHT_CLICK_AIR) && (action != Action.RIGHT_CLICK_BLOCK)) {
	        return;
	      }
	      if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	        return;
	      }
		    if(p.getItemInHand().getType() != Material.COAL){
		    	return;
		    }
	      if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lSmoke Bomb §7(Clique Direito)")) {
		     if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
			  Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L)));
	          ItemStack paramBomba = new ItemStack(Material.COAL);
	          final Item paramItemDrop = p.getWorld().dropItem(p.getEyeLocation().add(0.0D, 0.0D, 0.0D), paramBomba);
	          paramItemDrop.setVelocity(p.getEyeLocation().getDirection().multiply(0.8D).normalize());
	          paramItemDrop.setPickupDelay(Integer.MAX_VALUE);
	          Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
	            public void run(){
	              final BukkitTask paramSmokeRepeatingController = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable(){
	                public void run(){
	                  paramItemDrop.getWorld().playSound(paramItemDrop.getLocation(), Sound.FIZZ, 3.0F, 1.0F);
	                  if (UtilLag.ServerisLag()) {
	                    ParticleEffect.EXPLOSION_HUGE.display(0.0F, 0.0F, 0.0F, 5.0F, UtilMath.random.nextInt(50), paramItemDrop.getLocation(), 35.0D);
	                  } else {
	                    ParticleEffect.EXPLOSION_HUGE.display(UtilMath.random.nextInt(3), UtilMath.random.nextInt(3), UtilMath.random.nextInt(3), 5.0F, 180, paramItemDrop.getLocation(), 35.0D);
	                  }
	                }
	              }, 1L, 8L);
	              Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
	                public void run(){
	                	paramItemDrop.remove();
	                  paramSmokeRepeatingController.cancel();
	                }
	              }, 100L);
	            }
	          }, 100L);
	        }else{
		        p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
		        p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	        }
	      }
	    }
	  }