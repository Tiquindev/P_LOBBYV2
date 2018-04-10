package me.Patricktopgames.Gadgets.Gadgets;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.Patricktopgames.Gadgets.EventManager.FireworkNMSHandler;
import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;
import me.Patricktopgames.Gadgets.Util.UtilMath;

public class WitherShoot	
extends FireworkNMSHandler implements Listener{
	  
	  private void summonFirework(Location loc){
	    try{
	      playFirework(loc, 
	        FireworkEffect.builder()
	        .withColor(Color.BLACK)
	        .withColor(Color.WHITE)
	        .with(FireworkEffect.Type.BALL_LARGE)
	        .build());
	    }catch (Exception e){
	      e.printStackTrace();
	    }
	  }
	  
	  @EventHandler
	  public void Activate(PlayerInteractEvent e){
	    final Player p = e.getPlayer();
	    if ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	      return;
	    }
	    if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lWither Shoot §7(Clique Direito)")) {
	    	if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
	    	Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L)));
	      p.getWorld().playSound(p.getLocation(), Sound.WITHER_DEATH, 1.0F, 1.0F);
	      for (Entity localEntity : p.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
	        if ((localEntity instanceof Player)){
	          Player localPlayer2 = (Player)localEntity;
	          localPlayer2.playSound(localPlayer2.getLocation(), Sound.WITHER_DEATH, 1.0F, 1.0F);
	        }
	      }
	      final int i = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable(){
	        public void run(){
	          final WitherSkull localWitherSkull = (WitherSkull)p.launchProjectile(WitherSkull.class);
	          localWitherSkull.setShooter(p);
	          localWitherSkull.setVelocity(UtilMath.getRandomVector());
	          localWitherSkull.setMetadata("WITHERCATAPULT", new FixedMetadataValue(Main.plugin, ""));
	          localWitherSkull.setBounce(true);
	          Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable(){
	            public void run(){
	              summonFirework(localWitherSkull.getLocation());
	              localWitherSkull.remove();
	            }
	          }, 13L);
	        }
	      }, 10L, 8L).getTaskId();
	      Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable(){
	        public void run(){
	          Bukkit.getScheduler().cancelTask(i);
	        }
	      }, 120L);
	    }else{
		  p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
		  p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	    }
	 }
   }
	  
  @EventHandler
  public void onEntityExplode(EntityExplodeEvent e){
    if (e.getEntity().hasMetadata("WITHERCATAPULT")){
      summonFirework(e.getLocation());
      e.setCancelled(true);
    }
  }
  
  public static double arrondi(double paramDouble, int paramInt){
    return (int)(paramDouble * Math.pow(10.0D, paramInt) + 0.5D) / Math.pow(10.0D, paramInt);
  }
}