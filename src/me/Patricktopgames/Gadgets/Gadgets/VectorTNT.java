package me.Patricktopgames.Gadgets.Gadgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;
import me.Patricktopgames.Gadgets.Util.UtilMath;

public class VectorTNT 
implements Listener{
	
	  public List<Player> toVector = new ArrayList();
	  
	  public void launchTNT(Player localPlayer){
	    TNTPrimed localTNT = (TNTPrimed)localPlayer.getWorld().spawn(localPlayer.getLocation(), TNTPrimed.class);
	    localTNT.setMetadata("localData", new FixedMetadataValue(Main.plugin, null));
	    localTNT.setVelocity(localPlayer.getLocation().getDirection().multiply(1));
	    localTNT.setIsIncendiary(false);
	    localTNT.setFuseTicks(80);
	  }
	  
	  Map<UUID, Integer> mapOfP = new HashMap();
	  
	  @EventHandler
	  public void onLocalDataTNTExplode(final EntityExplodeEvent e)
	  {
	    if ((e.getEntity() instanceof TNTPrimed))
	    {
	      final TNTPrimed localTNT = (TNTPrimed)e.getEntity();
	      if (localTNT.hasMetadata("LocalData2"))
	      {
	        e.setCancelled(true);
	        e.setYield(0.0F);
	      }
	      if (localTNT.hasMetadata("localData"))
	      {
	        e.setYield(0.0F);
	        e.setCancelled(true);
	        Entity[] ent = Main.getUtilLocation().getNearbyEntities(e.getLocation(), 12);
	        Entity[] arrayOfEntity1;
	        int j = (arrayOfEntity1 = ent).length;
	        for (int i = 0; i < j; i++)
	        {
	          Entity entity = arrayOfEntity1[i];
	          if (entity.hasMetadata("NPC")) {
	            return;
	          }
	          if (entity.hasMetadata("PET")) {
	            return;
	          }
	          final BukkitTask t = Bukkit.getScheduler().runTaskTimer(Main.plugin, new Runnable()
	          {
	            public void run()
	            {
	              TNTPrimed newTNT = (TNTPrimed)e.getEntity().getWorld().spawn(localTNT.getLocation(), TNTPrimed.class);
	              newTNT.setVelocity(new Vector(UtilMath.randomRange(-0.5D, 0.5D), UtilMath.randomRange(0.5000000029802323D, 1.2D), UtilMath.randomRange(-0.5D, 0.5D)));
	              newTNT.setMetadata("LocalData2", new FixedMetadataValue(Main.plugin, null));
	            }
	          }, 0L, 4L);
	          Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
	          {
	            public void run()
	            {
	              t.cancel();
	            }
	          }, 80L);
	          
	          Vector v = new Vector(UtilMath.random.nextInt(5), UtilMath.random.nextInt(2), UtilMath.random.nextInt(5));
	          entity.setVelocity(v);
	        }
	      }
	    }
	  }
	  
	  @EventHandler
	  public void paramPlayerUseVectorTNT(PlayerInteractEvent e)
	  {
	    Player p = e.getPlayer();
	    Action paramAction = e.getAction();
	    if ((paramAction != Action.RIGHT_CLICK_AIR) && (paramAction != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	      return;
	    }
	    if(p.getItemInHand().getType() != Material.TNT){
	    	return;
	    }
	    if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lVectorTNT §7(Clique Direito)")) {
		       if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
	        launchTNT(p);
	        Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L)));
	      }else{
		    p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
		    p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	      }
	    }
	  }
	  
	  @EventHandler
	  public void damage(EntityDamageByEntityEvent e)
	  {
	    if ((e.getDamager() instanceof TNTPrimed)) {
	      e.setCancelled(true);
	    }
	  }
}
