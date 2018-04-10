package me.Patricktopgames.lobby.Gadgets;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.Gadgets.EventManager.ParticleEffect;
import me.Patricktopgames.Gadgets.Montarias.MountHandler;
import me.Patricktopgames.Gadgets.Util.UtilPet;

public class Pets 
implements Listener{
	public static enum PetsType{
	    COELHO,  GALINHA,  VACA,  WOLF,  PORCO,  NENHUM;
	    public static HashMap<UUID, Entity> pet = new HashMap();
	    public static final Map<UUID, PetsType> booleanPet = new HashMap();
	    public static final Map<UUID, Rabbit> booleanRabbit = new HashMap();
	    public static final Map<UUID, Entity> booleanSheep = new HashMap();
	    public static final Map<UUID, Entity> booleanWolf = new HashMap();
	    
	    public static void setPet(Player uniquePlayer, PetsType pettype){
	      UUID uniqueID = uniquePlayer.getUniqueId();
	      switch (pettype)
	      {
	      case GALINHA: 
	        Chicken paramUniqueChiken = (Chicken)uniquePlayer.getWorld().spawn(uniquePlayer.getLocation(), Chicken.class);
	        paramUniqueChiken.setCustomNameVisible(true);
	        booleanPet.put(uniqueID, GALINHA);
	        pet.put(uniqueID, paramUniqueChiken);
	        paramUniqueChiken.setCustomName("§b§lGalinha de " + uniquePlayer.getName());
	        UtilPet.criarPet(paramUniqueChiken, uniqueID);
	        ParticleEffect.HEART.display(1.0F, 0.0F, 0.0F, 3.0F, 20, paramUniqueChiken.getLocation(), 12.0D);
	        paramUniqueChiken.setMetadata("petChicken", new FixedMetadataValue(Main.plugin, "petFixed1"));
	        break;
	      case VACA: 
	        Cow paramUniqueCow = (Cow)uniquePlayer.getWorld().spawn(uniquePlayer.getLocation(), Cow.class);
	        paramUniqueCow.setCustomNameVisible(true);
	        UtilPet.criarPet(paramUniqueCow, uniqueID);
	        pet.put(uniqueID, paramUniqueCow);
	        booleanPet.put(uniqueID, VACA);
	        paramUniqueCow.setCustomName("§b§lVaca de " + uniquePlayer.getName());
	        paramUniqueCow.setMetadata("petCow", new FixedMetadataValue(Main.plugin, "petFixed2"));
	        ParticleEffect.HEART.display(1.0F, 0.0F, 0.0F, 3.0F, 20, paramUniqueCow.getLocation(), 12.0D);
	        break;
	      case COELHO: 
	        Rabbit paramUniqueRabit = (Rabbit)uniquePlayer.getWorld().spawn(uniquePlayer.getLocation(), Rabbit.class);
	        paramUniqueRabit.setCustomNameVisible(true);
	        UtilPet.criarPet(paramUniqueRabit, uniqueID);
	        booleanPet.put(uniqueID, COELHO);
	        pet.put(uniqueID, paramUniqueRabit);
	        paramUniqueRabit.setCustomName("§b§lCoelho de " + uniquePlayer.getName());
	        paramUniqueRabit.setMetadata("petRabbit", new FixedMetadataValue(Main.plugin, "petFixed3"));
	        paramUniqueRabit.setRabbitType(Rabbit.Type.WHITE);
	        booleanRabbit.put(uniqueID, paramUniqueRabit);
	        ParticleEffect.HEART.display(1.0F, 0.0F, 0.0F, 3.0F, 20, paramUniqueRabit.getLocation(), 12.0D);
	        break;
	      case WOLF: 
	        Wolf paramUniqueWolf = (Wolf)uniquePlayer.getWorld().spawn(uniquePlayer.getLocation(), Wolf.class);
	        paramUniqueWolf.setCustomNameVisible(true);
	        UtilPet.criarPet(paramUniqueWolf, uniqueID);
	        paramUniqueWolf.setOwner(uniquePlayer);
	        paramUniqueWolf.setAngry(true);
	        paramUniqueWolf.setAdult();
	        pet.put(uniqueID, paramUniqueWolf);
	        booleanPet.put(uniqueID, WOLF);
	        paramUniqueWolf.setCustomName("§b§lCachorro de " + uniquePlayer.getName());
	        paramUniqueWolf.setMetadata("petWolf", new FixedMetadataValue(Main.plugin, "petFixed4"));
	        ParticleEffect.HEART.display(1.0F, 0.0F, 0.0F, 3.0F, 20, paramUniqueWolf.getLocation(), 12.0D);
	        break;
	      case PORCO: 
	        Pig paramUniquePig = (Pig)uniquePlayer.getWorld().spawn(uniquePlayer.getLocation(), Pig.class);
	        paramUniquePig.setCustomNameVisible(true);
	        paramUniquePig.setMetadata("petPorco", new FixedMetadataValue(Main.plugin, "petFixed5"));
	        paramUniquePig.setAdult();
	        pet.put(uniqueID, paramUniquePig);
	        booleanPet.put(uniqueID, PORCO);
	        UtilPet.criarPet(paramUniquePig, uniqueID);
	        paramUniquePig.setCustomName("§b§lPORCO de " + uniquePlayer.getName());
	        ParticleEffect.HEART.display(1.0F, 0.0F, 0.0F, 3.0F, 20, paramUniquePig.getLocation(), 12.0D);
	        break;
	      case NENHUM: 
	        booleanPet.remove(uniqueID);
	        MountHandler.pet.remove(uniqueID);
	      }
	    }
	    
	    public static boolean HasPet(Player paramPlayer)
	    {
	      if (booleanPet.containsKey(paramPlayer.getUniqueId())) {
	        return true;
	      }
	      return false;
	    }
	    
	    public static void removePet(Player p){
	      if (HasPet(p)){
	        if (pet.get(p.getUniqueId()) == null) {
	          return;
	        }
	        ((Entity)pet.get(p.getUniqueId())).remove();
	        setPet(p, NENHUM);
	        pet.remove(p.getUniqueId());
	      }
	    }
	    
	    public static boolean isEntity(Player petOwner, Entity entity){
	      if (pet.get(petOwner.getUniqueId()) == entity) {
	        return true;
	      }
	      return false;
	    }
	  }
	  
	  @EventHandler
	  public void onPetDamage(EntityDamageEvent e)
	  {
	    if ((e.getEntity() instanceof Chicken))
	    {
	      Chicken localChicken = (Chicken)e.getEntity();
	      if (localChicken.hasMetadata("petChicken")) {
	        e.setCancelled(true);
	      }
	    }
	    if ((e.getEntity() instanceof Cow))
	    {
	      Cow localCow = (Cow)e.getEntity();
	      if (localCow.hasMetadata("petCow")) {
	        e.setCancelled(true);
	      }
	    }
	    if ((e.getEntity() instanceof Rabbit))
	    {
	      Rabbit localRabbit = (Rabbit)e.getEntity();
	      if (localRabbit.hasMetadata("petRabbit")) {
	        e.setCancelled(true);
	      }
	    }
	    if ((e.getEntity() instanceof Wolf))
	    {
	      Wolf localWolf = (Wolf)e.getEntity();
	      if (localWolf.hasMetadata("petWolf")) {
	        e.setCancelled(true);
	      }
	    }
	    if ((e.getEntity() instanceof Pig))
	    {
	      Pig localWolf = (Pig)e.getEntity();
	      if (localWolf.hasMetadata("petPorco")) {
	        e.setCancelled(true);
	      }
	    }
	  }
	}