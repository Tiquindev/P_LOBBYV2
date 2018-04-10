package me.Patricktopgames.Gadgets.Util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.Patricktopgames.Gadgets.EventManager.UpdateEvent;
import me.Patricktopgames.Gadgets.EventManager.UpdateType;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;

public class UtilPet 
implements Listener{

	private static Field gsa;
	  private static Field goalSelector;
	  private static Field targetSelector;
	  public static Map<UUID, Entity> pet = new HashMap();
	  
	  static
	  {
	    try
	    {
	      gsa = PathfinderGoalSelector.class.getDeclaredField("b");
	      gsa.setAccessible(true);
	      
	      goalSelector = EntityInsentient.class.getDeclaredField("goalSelector");
	      goalSelector.setAccessible(true);
	      
	      targetSelector = EntityInsentient.class.getDeclaredField("targetSelector");
	      targetSelector.setAccessible(true);
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	  }
	  
	  @EventHandler
	  public void petsUpdate(UpdateEvent paramUpdateEvent){
	    if (paramUpdateEvent.getType() == UpdateType.TICK) {
	      for (UUID localUUID : pet.keySet()) {
	        if (((Entity)pet.get(localUUID)).isValid()) {
	          UtilEnt.CreatureMove((Entity)pet.get(localUUID), Bukkit.getPlayer(localUUID).getLocation().add(2.0D, 0.0D, 1.0D), 1.2F);
	        } else {
	          pet.remove(localUUID);
	        }
	      }
	    }
	  }
	  
	  public static void criarPet(LivingEntity paramLivingEntity, UUID paramUUID)
	  {
	    pet.put(paramUUID, paramLivingEntity);
	    try
	    {
	      EntityLiving localEntityLiving = ((CraftLivingEntity)paramLivingEntity).getHandle();
	      if ((localEntityLiving instanceof EntityInsentient))
	      {
	        PathfinderGoalSelector localPathfinderGoalSelector1 = (PathfinderGoalSelector)goalSelector.get(localEntityLiving);
	        PathfinderGoalSelector localPathfinderGoalSelector2 = (PathfinderGoalSelector)targetSelector.get(localEntityLiving);
	        
	        gsa.set(localPathfinderGoalSelector1, new UnsafeList());
	        gsa.set(localPathfinderGoalSelector2, new UnsafeList());
	        
	        localPathfinderGoalSelector1.a(0, new PathfinderGoalFloat((EntityInsentient)localEntityLiving));
	      }
	      else
	      {
	        throw new IllegalArgumentException(paramLivingEntity.getType().getName() + " is not an instance of an EntityInsentient.");
	      }
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	  }
	}