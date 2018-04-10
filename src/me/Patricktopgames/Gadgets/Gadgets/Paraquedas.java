package me.Patricktopgames.Gadgets.Gadgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import me.Patricktopgames.Gadgets.EventManager.ParticleEffect;
import me.Patricktopgames.Gadgets.EventManager.UpdateEvent;
import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;
import me.Patricktopgames.Gadgets.Util.UtilMath;

public class Paraquedas 
implements Listener{
	
	final ArrayList<Player> localArrayList = new ArrayList();

	@EventHandler
	  public void paramUseParaquedas(PlayerInteractEvent e){
	    final Player p = e.getPlayer();
	    if ((e.getAction() != Action.RIGHT_CLICK_AIR) && 
	      (e.getAction() != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	      return;
	    }
	    if (!p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lParaquedas §7(Clique Direito)")) {
	      return;
	    }
	    e.setCancelled(true);
	       if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
	    	 Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(7L)));
	      this.localArrayList.add(p);
	      for (Entity localEntity : p.getWorld().getEntities()) {
	        if ((localEntity instanceof Chicken)){
	          Chicken paramChicken = (Chicken)localEntity;
	          if ((paramChicken.isLeashed()) && 
	            (paramChicken.getLeashHolder() == p) && 
	            (paramChicken.isValid())){
	            ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 0, 
	              paramChicken.getLocation(), 10.0D);
	            paramChicken.remove();
	          }
	        }
	      }
	      if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 53.0D, 0.0D)).getType() == Material.AIR) {
	        if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 54.0D, 0.0D)).getType() == Material.AIR) {
	          if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 55.0D, 0.0D)).getType() == Material.AIR) {
	            if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 55.0D, 0.0D)).getType() == Material.AIR) {
	              if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 54.0D,0.0D)).getType() == Material.AIR) {
	                if (p.getWorld().getBlockAt(p.getLocation().add(1.0D, 53.0D, 
	                  0.0D)).getType() == Material.AIR) {
	                  if (p.getWorld().getBlockAt(p.getLocation().add(-1.0D, 53.0D, 
	                    0.0D)).getType() == Material.AIR) {
	                    if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 53.0D,1.0D)).getType() == Material.AIR) {
	                      if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 53.0D,-1.0D)).getType() == Material.AIR) {
	                        if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 52.0D, 0.0D)).getType() == Material.AIR) {
	                          if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 56.0D,0.0D)).getType() == Material.AIR) {
	                            if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 57.0D,0.0D)).getType() == Material.AIR) {
	                              if (p.getWorld().getBlockAt(p.getLocation().add(0.0D, 58.0D, 0.0D)).getType() == Material.AIR){
	                                p.setFallDistance(0.0F);
	                                p.teleport(p.getLocation().add(0.0D, 55.0D, 0.0D));
	                                p.setFallDistance(0.0F);
	                                for (int i = 0; i <= 10; i++){
	                                  final Chicken localChicken1 = (Chicken)p.getWorld().spawnEntity(p.getLocation().add(randomRangeFloat(-1.7F, 1.7F), 0.0D, randomRangeFloat(-1.7F, 1.7F)), EntityType.CHICKEN);
	                                  localChicken1.setLeashHolder(p);
	                                  setMetadata(localChicken1, "PARACHUTE", p.getName(), Main.plugin);
	                                  Bukkit.getScheduler().runTaskLater(Main.plugin, 
	                                    new Runnable(){
	                                      public void run(){
	                                        if (localChicken1.isValid()){
	                                          localChicken1.remove();
	                                          ParticleEffect.SMOKE_NORMAL.display(0.0F, 
	                                            0.0F, 0.0F, 0.0F, 0, 
	                                            localChicken1.getLocation(), 10.0D);
	                                        }
	                                      }
	                                    }, 250L);
	                                }
	                                Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable(){
	                                  public void run(){
	                                    Iterator<Entity> localIterator = p.getWorld().getEntities().iterator();
	                                    while (localIterator.hasNext()){
	                                      Entity localEntity = (Entity)localIterator.next();
	                                      if ((localEntity instanceof Chicken)){
	                                        Chicken localChicken = (Chicken)localEntity;
	                                        if ((localChicken.isLeashed()) && 
	                                          (localChicken.getLeashHolder() == p)) {
	                                          localChicken.remove();
	                                        }
	                                      }
	                                    }
	                                  }
	                                }, 230L);
	                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, 
	                                  new Runnable(){
	                                    public void run(){
	                                      Paraquedas.this.localArrayList.remove(p);
	                                    }
	                                  }, 360L);
	                                return;
	                              }
	                            }
	                          }
	                        }
	                      }
	                    }
	                  }
	                }
	              }
	            }
	          }
	        }
	      }
	      p.sendMessage("§cSem espaço !");
	    }else{
	        p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
	        p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	    }
	  }
	  
	  public void setMetadata(Entity entidade, String string, Object object, Plugin plugin){
		  entidade.setMetadata(string, new FixedMetadataValue(plugin, object));
	  }
	  
	  @EventHandler
	  public void onUnleash(EntityUnleashEvent e){
	    if (e.getEntity().hasMetadata("PARACHUTE")){
	      Chicken localChicken = (Chicken)e.getEntity();
	      localChicken.setLeashHolder(null);
	      ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 0, localChicken.getLocation(), 10.0D);
	      e.getEntity().remove();
	    }
	  }
	  
	  @EventHandler
	  public void onQuit(PlayerQuitEvent e){
	    Player p = e.getPlayer();
	    for (Entity localEntity : p.getWorld().getEntities()) {
	      if ((localEntity instanceof Chicken)){
	        Chicken localChicken = (Chicken)localEntity;
	        if ((localChicken.isLeashed()) && 
	          (localChicken.getLeashHolder() == p) && 
	          (localChicken.hasMetadata("PARACHUTE"))){
	          ParticleEffect.SMOKE_NORMAL.display(0.0F, 0.0F, 0.0F, 0.0F, 0, 
	            localChicken.getLocation(), 10.0D);
	          localChicken.remove();
	        }
	      }
	    }
	  }
	  
	  @EventHandler
	  public void onUpdate(UpdateEvent e){
	    Iterator localIterator2;
	    for (Iterator localIterator1 = Bukkit.getOnlinePlayers().iterator(); localIterator1.hasNext(); localIterator2.hasNext()){
	      Player p = (Player)localIterator1.next();
	      
	      localIterator2 = p.getWorld().getEntities().iterator(); continue;
	    }
	  }
	  
	  @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	  public void damage(EntityDamageEvent e){
	    if (((e.getEntity() instanceof Chicken)) && 
	      (e.getEntity().hasMetadata("PARACHUTE"))) {
	      e.setCancelled(true);
	    }
	  }
	  
	  @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	  public void damageFall(EntityDamageEvent e){
	    if (((e.getEntity() instanceof Player)) && (e.getCause() == EntityDamageEvent.DamageCause.FALL))
	    {
	      Player p = (Player)e.getEntity();
	      if (this.localArrayList.contains(p)) {
	        e.setCancelled(true);
	      }
	    }
	  }
	  
	  @EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	  public void eventMove(PlayerMoveEvent e){
	    Player p = e.getPlayer();
	    if ((p.getFallDistance() > 0.0F) && 
	      (p.getVelocity().getY() < -0.1000000014901161D)) {
	      for (Entity localEntity : p.getNearbyEntities(20.0D, 
	        20.0D, 40.0D)) {
	        if ((localEntity instanceof Chicken)){
	          Chicken localChicken = (Chicken)localEntity;
	          if ((localChicken.isLeashed()) && 
	            (localChicken.getLeashHolder() == p) && 
	            (localChicken.getLocation().getY() > p.getLocation().getY() + 2.0D)){
	            p.setVelocity(p
	              .getVelocity().add(
	              new Vector(0.0D, 0.01D, 0.0D)));
	            p.setFallDistance(0.0F);
	            if (p.getVelocity().getY() > -0.1000000014901161D) {
	              p.setVelocity(p.getVelocity().setY(-0.1F));
	            }
	          }
	        }
	      }
	    }
	  }
	  
	  public static int randInt(int numero1, int numero2){
	    int i = UtilMath.random.nextInt(numero2 - numero1 + 1) + numero1;
	    return i;
	  }
	  
	  public static double arrondi(double paramDouble, int paramInt){
    return (int)(paramDouble * Math.pow(10.0D, paramInt) + 0.5D) / 
     Math.pow(10.0D, paramInt);
  }
	  
  public static double randomRange(double paramDouble1, double paramDouble2){
    return Math.random() < 0.5D ? (1.0D - Math.random()) * (
      paramDouble2 - paramDouble1) + paramDouble1 : Math.random() * (
      paramDouble2 - paramDouble1) + paramDouble1;
  }
	  
  public static float randomRangeFloat(float paramFloat1, float paramFloat2){
    return (float)(Math.random() < 0.5D ? (1.0D - Math.random()) * (
      paramFloat2 - paramFloat1) + paramFloat1 : Math.random() * (
      paramFloat2 - paramFloat1) + paramFloat1);
  }
	  
  public static int randomRangeInt(int paramInt1, int paramInt2){
    return (int)(Math.random() < 0.5D ? (1.0D - Math.random()) * (
      paramInt2 - paramInt1) + paramInt1 : Math.random() * (
      paramInt2 - paramInt1) + paramInt1);
  }
}