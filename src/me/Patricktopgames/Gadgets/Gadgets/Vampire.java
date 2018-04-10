package me.Patricktopgames.Gadgets.Gadgets;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;

public class Vampire 
implements Listener{
	
	  private void setVampire(final Player p, long tempo){
	    ItemStack i = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta l = (LeatherArmorMeta)i.getItemMeta();
	    l.setColor(Color.BLACK);
	    i.setItemMeta(l);
	    ItemStack paramSkull = new ItemStack(Material.SKULL_ITEM, 1, (short)1);
	    
	    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 10));
	    p.getInventory().setHelmet(paramSkull);
	    p.getInventory().setChestplate(i);
	    p.playSound(p.getLocation(), Sound.WITHER_IDLE, 1.0F, -8.0F);
	    p.updateInventory();
	    p.setAllowFlight(true);
	    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable(){
	      public void run(){
	        p.removePotionEffect(PotionEffectType.INVISIBILITY);
	        p.getInventory().setHelmet(null);
	        p.getInventory().setChestplate(null);
	        p.updateInventory();
	        p.setAllowFlight(false);
	      }
	    }, tempo * 20L);
	  }
	  
	  @EventHandler
	  public void paramPlayerUseVampir(PlayerInteractEvent e){
	    Player p = e.getPlayer();
	    Action action = e.getAction();
	    if ((action != Action.RIGHT_CLICK_AIR) && (action != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (Main.getUtilBlock().usable(e.getClickedBlock())) {
	      return;
	    }
	    if(p.getItemInHand().getType() != Material.GHAST_TEAR){
	    	return;
	    }
	    if (p.getItemInHand().getItemMeta().getDisplayName().equals("§6§lVampire §7(Clique Direito)")) {
		       if ((!Manager.cooldown.containsKey(p)) || (((Long)Manager.cooldown.get(p)).longValue() <= System.currentTimeMillis())){
	        setVampire(p, 25L);
	        Manager.cooldown.put(p, Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L)));
	      }else{
		      p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
		      p.sendMessage("§cAguarde " + TimeUnit.MILLISECONDS.toSeconds(((Long)Manager.cooldown.get(p)).longValue() - System.currentTimeMillis()) + " para usar novamente !");
	      }
	    }
	  }
	}