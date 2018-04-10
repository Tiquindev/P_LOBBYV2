package me.Patricktopgames.Gadgets.Montarias;

import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.Gadgets.Montarias.MountHandler;
import me.Patricktopgames.Gadgets.Util.UtilPet;

public enum Mounts {
	
	NENHUM,  FROZEN,  INFERNO;
	
	  public static void summonMount(Player p, Mounts type){
	    UUID uniqueID = p.getUniqueId();
	    switch (type){
	    case NENHUM: 
	      MountHandler.pet.remove(p.getUniqueId());
	    case FROZEN: 
	      Horse paramEntityFrozen = (Horse)p.getWorld().spawn(p.getLocation(), Horse.class);
	      paramEntityFrozen.setStyle(Horse.Style.WHITE);
	      paramEntityFrozen.setColor(Horse.Color.WHITE);
	      paramEntityFrozen.setAdult();
	      paramEntityFrozen.setCustomName("§bFrozen Horse");
	      paramEntityFrozen.setMetadata("FrozenHorse", new FixedMetadataValue(Main.plugin, null));
	      paramEntityFrozen.setTamed(true);
	      paramEntityFrozen.setOwner(p);
	      paramEntityFrozen.getInventory().setSaddle(new ItemStack(Material.DIAMOND_BARDING, 1));
	      UtilPet.criarPet(paramEntityFrozen, uniqueID);
	      MountHandler.pet.put(p.getUniqueId(), paramEntityFrozen);
	      break;
	    case INFERNO: 
	      Horse paramEntityInferno = (Horse)p.getWorld().spawn(p.getLocation(), Horse.class);
	      paramEntityInferno.setVariant(Horse.Variant.SKELETON_HORSE);
	      paramEntityInferno.setAdult();
	      paramEntityInferno.setMetadata("InfernalHorse", new FixedMetadataValue(Main.plugin, null));
	      paramEntityInferno.setTamed(true);
	      paramEntityInferno.setCustomName("§6Infernal Horse");
	      paramEntityInferno.setOwner(p);
	      paramEntityInferno.getInventory().setSaddle(new ItemStack(Material.DIAMOND_BARDING, 1));
	      UtilPet.criarPet(paramEntityInferno, uniqueID);
	      MountHandler.pet.put(p.getUniqueId(), paramEntityInferno);
	    }
  }
}