package me.Patricktopgames.Gadgets.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.Gadgets.Util.UtilMath;

public class UtilBlock {

	public HashSet<Byte> blockPassSet = new HashSet();
	  public ArrayList<Block> blockToRestore = new ArrayList();
	  public ArrayList<Block> frostyBlock = new ArrayList();
	  
	  public void setBlock(int paramInt, byte paramByte, Location paramLocation)
	  {
	    if ((paramLocation.getBlock().getType() != Material.SKULL) && (paramLocation.getBlock().getType() != Material.ITEM_FRAME))
	    {
	      paramLocation.getBlock().setTypeId(paramInt);
	      paramLocation.getBlock().setData(paramByte);
	    }
	  }
	  
	  public void setFakeBlock(int paramInt, byte paramByte, Location paramLocation)
	  {
	    if ((paramLocation.getBlock().getType() != Material.SKULL) && (paramLocation.getBlock().getType() != Material.ITEM_FRAME) && 
	      (!paramLocation.getBlock().hasMetadata("DJBlock")))
	    {
	      int i = paramLocation.getBlockX();
	      int j = paramLocation.getBlockY();
	      int k = paramLocation.getBlockZ();
	      for (Player localPlayer : Bukkit.getOnlinePlayers()) {
	        localPlayer.sendBlockChange(paramLocation.getWorld().getBlockAt(i, j, k).getLocation(), paramInt, paramByte);
	      }
	    }
	  }
	  
	  public void setBlockToRestore(final Block paramBlock, int paramInt, byte paramByte, long paramLong, final boolean paramBoolean1, final boolean paramBoolean2, boolean paramBoolean3)
	  {
	    if (!paramBlock.hasMetadata("DJBlock"))
	    {
	      this.blockToRestore.add(paramBlock);
	      if (paramBoolean2) {
	        this.frostyBlock.add(paramBlock);
	      }
	      final int i = paramBlock.getTypeId();
	      final byte b = paramBlock.getData();
	      if (paramBoolean1)
	      {
	        if (paramBlock.getType() != Material.SNOW) {
	          setFakeBlock(paramInt, paramByte, paramBlock.getLocation());
	        }
	      }
	      else {
	        setBlock(paramInt, paramByte, paramBlock.getLocation());
	      }
	      Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable()
	      {
	        public void run()
	        {
	          if (paramBoolean1) {
	            UtilBlock.this.setFakeBlock(i, b, paramBlock.getLocation());
	          } else {
	            UtilBlock.this.setBlock(i, b, paramBlock.getLocation());
	          }
	          UtilBlock.this.blockToRestore.remove(paramBlock);
	          if (paramBoolean2) {
	            UtilBlock.this.frostyBlock.remove(paramBlock);
	          }
	        }
	      }, paramLong * 20L);
	    }
	  }
	  
	  public boolean solid(Block paramBlock)
	  {
	    if (paramBlock == null) {
	      return false;
	    }
	    return solid(paramBlock.getTypeId());
	  }
	  
	  public boolean solid(int paramInt)
	  {
	    return solid((byte)paramInt);
	  }
	  
	  public boolean solid(byte paramByte)
	  {
	    if (this.blockPassSet.isEmpty())
	    {
	      this.blockPassSet.add(Byte.valueOf((byte)0));
	      this.blockPassSet.add(Byte.valueOf((byte)6));
	      this.blockPassSet.add(Byte.valueOf((byte)8));
	      this.blockPassSet.add(Byte.valueOf((byte)9));
	      this.blockPassSet.add(Byte.valueOf((byte)10));
	      this.blockPassSet.add(Byte.valueOf((byte)11));
	      this.blockPassSet.add(Byte.valueOf((byte)26));
	      this.blockPassSet.add(Byte.valueOf((byte)27));
	      this.blockPassSet.add(Byte.valueOf((byte)28));
	      this.blockPassSet.add(Byte.valueOf((byte)30));
	      this.blockPassSet.add(Byte.valueOf((byte)31));
	      this.blockPassSet.add(Byte.valueOf((byte)32));
	      this.blockPassSet.add(Byte.valueOf((byte)37));
	      this.blockPassSet.add(Byte.valueOf((byte)38));
	      this.blockPassSet.add(Byte.valueOf((byte)39));
	      this.blockPassSet.add(Byte.valueOf((byte)40));
	      this.blockPassSet.add(Byte.valueOf((byte)50));
	      this.blockPassSet.add(Byte.valueOf((byte)51));
	      this.blockPassSet.add(Byte.valueOf((byte)55));
	      this.blockPassSet.add(Byte.valueOf((byte)59));
	      this.blockPassSet.add(Byte.valueOf((byte)63));
	      this.blockPassSet.add(Byte.valueOf((byte)64));
	      this.blockPassSet.add(Byte.valueOf((byte)65));
	      this.blockPassSet.add(Byte.valueOf((byte)66));
	      this.blockPassSet.add(Byte.valueOf((byte)68));
	      this.blockPassSet.add(Byte.valueOf((byte)69));
	      this.blockPassSet.add(Byte.valueOf((byte)70));
	      this.blockPassSet.add(Byte.valueOf((byte)71));
	      this.blockPassSet.add(Byte.valueOf((byte)72));
	      this.blockPassSet.add(Byte.valueOf((byte)75));
	      this.blockPassSet.add(Byte.valueOf((byte)76));
	      this.blockPassSet.add(Byte.valueOf((byte)77));
	      this.blockPassSet.add(Byte.valueOf((byte)78));
	      this.blockPassSet.add(Byte.valueOf((byte)83));
	      this.blockPassSet.add(Byte.valueOf((byte)90));
	      this.blockPassSet.add(Byte.valueOf((byte)92));
	      this.blockPassSet.add(Byte.valueOf((byte)93));
	      this.blockPassSet.add(Byte.valueOf((byte)94));
	      this.blockPassSet.add(Byte.valueOf((byte)96));
	      this.blockPassSet.add(Byte.valueOf((byte)101));
	      this.blockPassSet.add(Byte.valueOf((byte)102));
	      this.blockPassSet.add(Byte.valueOf((byte)104));
	      this.blockPassSet.add(Byte.valueOf((byte)105));
	      this.blockPassSet.add(Byte.valueOf((byte)106));
	      this.blockPassSet.add(Byte.valueOf((byte)107));
	      this.blockPassSet.add(Byte.valueOf((byte)111));
	      this.blockPassSet.add(Byte.valueOf((byte)115));
	      this.blockPassSet.add(Byte.valueOf((byte)116));
	      this.blockPassSet.add(Byte.valueOf((byte)117));
	      this.blockPassSet.add(Byte.valueOf((byte)118));
	      this.blockPassSet.add(Byte.valueOf((byte)119));
	      this.blockPassSet.add(Byte.valueOf((byte)120));
	      this.blockPassSet.add(Byte.valueOf((byte)-85));
	    }
	    return !this.blockPassSet.contains(Byte.valueOf(paramByte));
	  }
	  
	  public HashSet<Byte> blockAirFoliageSet = new HashSet();
	  
	  public boolean airFoliage(Block paramBlock)
	  {
	    if (paramBlock == null) {
	      return false;
	    }
	    return airFoliage(paramBlock.getTypeId());
	  }
	  
	  public boolean airFoliage(int paramInt)
	  {
	    return airFoliage((byte)paramInt);
	  }
	  
	  public boolean airFoliage(byte paramByte)
	  {
	    if (this.blockAirFoliageSet.isEmpty())
	    {
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)0));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)6));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)31));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)32));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)37));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)38));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)39));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)40));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)51));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)59));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)104));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)105));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)115));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)-115));
	      this.blockAirFoliageSet.add(Byte.valueOf((byte)-114));
	    }
	    return this.blockAirFoliageSet.contains(Byte.valueOf(paramByte));
	  }
	  
	  public HashSet<Byte> fullSolid = new HashSet();
	  
	  public boolean fullSolid(Block paramBlock)
	  {
	    if (paramBlock == null) {
	      return false;
	    }
	    return fullSolid(paramBlock.getTypeId());
	  }
	  
	  public boolean fullSolid(int paramInt)
	  {
	    return fullSolid((byte)paramInt);
	  }
	  
	  public boolean fullSolid(byte paramByte)
	  {
	    if (this.fullSolid.isEmpty())
	    {
	      this.fullSolid.add(Byte.valueOf((byte)1));
	      this.fullSolid.add(Byte.valueOf((byte)2));
	      this.fullSolid.add(Byte.valueOf((byte)3));
	      this.fullSolid.add(Byte.valueOf((byte)4));
	      this.fullSolid.add(Byte.valueOf((byte)5));
	      this.fullSolid.add(Byte.valueOf((byte)7));
	      this.fullSolid.add(Byte.valueOf((byte)12));
	      this.fullSolid.add(Byte.valueOf((byte)13));
	      this.fullSolid.add(Byte.valueOf((byte)14));
	      this.fullSolid.add(Byte.valueOf((byte)15));
	      this.fullSolid.add(Byte.valueOf((byte)16));
	      this.fullSolid.add(Byte.valueOf((byte)17));
	      this.fullSolid.add(Byte.valueOf((byte)18));
	      this.fullSolid.add(Byte.valueOf((byte)19));
	      this.fullSolid.add(Byte.valueOf((byte)20));
	      this.fullSolid.add(Byte.valueOf((byte)21));
	      this.fullSolid.add(Byte.valueOf((byte)22));
	      this.fullSolid.add(Byte.valueOf((byte)23));
	      this.fullSolid.add(Byte.valueOf((byte)24));
	      this.fullSolid.add(Byte.valueOf((byte)25));
	      this.fullSolid.add(Byte.valueOf((byte)29));
	      this.fullSolid.add(Byte.valueOf((byte)33));
	      this.fullSolid.add(Byte.valueOf((byte)35));
	      this.fullSolid.add(Byte.valueOf((byte)41));
	      this.fullSolid.add(Byte.valueOf((byte)42));
	      this.fullSolid.add(Byte.valueOf((byte)43));
	      this.fullSolid.add(Byte.valueOf((byte)44));
	      this.fullSolid.add(Byte.valueOf((byte)45));
	      this.fullSolid.add(Byte.valueOf((byte)46));
	      this.fullSolid.add(Byte.valueOf((byte)47));
	      this.fullSolid.add(Byte.valueOf((byte)48));
	      this.fullSolid.add(Byte.valueOf((byte)49));
	      this.fullSolid.add(Byte.valueOf((byte)56));
	      this.fullSolid.add(Byte.valueOf((byte)57));
	      this.fullSolid.add(Byte.valueOf((byte)58));
	      this.fullSolid.add(Byte.valueOf((byte)60));
	      this.fullSolid.add(Byte.valueOf((byte)61));
	      this.fullSolid.add(Byte.valueOf((byte)62));
	      this.fullSolid.add(Byte.valueOf((byte)73));
	      this.fullSolid.add(Byte.valueOf((byte)74));
	      this.fullSolid.add(Byte.valueOf((byte)79));
	      this.fullSolid.add(Byte.valueOf((byte)80));
	      this.fullSolid.add(Byte.valueOf((byte)82));
	      this.fullSolid.add(Byte.valueOf((byte)84));
	      this.fullSolid.add(Byte.valueOf((byte)86));
	      this.fullSolid.add(Byte.valueOf((byte)87));
	      this.fullSolid.add(Byte.valueOf((byte)88));
	      this.fullSolid.add(Byte.valueOf((byte)89));
	      this.fullSolid.add(Byte.valueOf((byte)91));
	      this.fullSolid.add(Byte.valueOf((byte)95));
	      this.fullSolid.add(Byte.valueOf((byte)97));
	      this.fullSolid.add(Byte.valueOf((byte)98));
	      this.fullSolid.add(Byte.valueOf((byte)99));
	      this.fullSolid.add(Byte.valueOf((byte)100));
	      this.fullSolid.add(Byte.valueOf((byte)103));
	      this.fullSolid.add(Byte.valueOf((byte)110));
	      this.fullSolid.add(Byte.valueOf((byte)112));
	      this.fullSolid.add(Byte.valueOf((byte)121));
	      this.fullSolid.add(Byte.valueOf((byte)123));
	      this.fullSolid.add(Byte.valueOf((byte)124));
	      this.fullSolid.add(Byte.valueOf((byte)125));
	      this.fullSolid.add(Byte.valueOf((byte)126));
	      this.fullSolid.add(Byte.valueOf((byte)-97));
	      this.fullSolid.add(Byte.valueOf((byte)-94));
	      this.fullSolid.add(Byte.valueOf((byte)-84));
	      this.fullSolid.add(Byte.valueOf((byte)-127));
	      this.fullSolid.add(Byte.valueOf((byte)-123));
	      this.fullSolid.add(Byte.valueOf((byte)-119));
	      this.fullSolid.add(Byte.valueOf((byte)-118));
	      this.fullSolid.add(Byte.valueOf((byte)-104));
	      this.fullSolid.add(Byte.valueOf((byte)-103));
	      this.fullSolid.add(Byte.valueOf((byte)-101));
	      this.fullSolid.add(Byte.valueOf((byte)-98));
	    }
	    return this.fullSolid.contains(Byte.valueOf(paramByte));
	  }
	  
	  public HashSet<Byte> blockUseSet = new HashSet();
	  
	  public boolean usable(Block paramBlock)
	  {
	    if (paramBlock == null) {
	      return false;
	    }
	    return usable(paramBlock.getTypeId());
	  }
	  
	  public boolean usable(int paramInt)
	  {
	    return usable((byte)paramInt);
	  }
	  
	  public boolean usable(byte paramByte)
	  {
	    if (this.blockUseSet.isEmpty())
	    {
	      this.blockUseSet.add(Byte.valueOf((byte)23));
	      this.blockUseSet.add(Byte.valueOf((byte)26));
	      this.blockUseSet.add(Byte.valueOf((byte)33));
	      this.blockUseSet.add(Byte.valueOf((byte)47));
	      this.blockUseSet.add(Byte.valueOf((byte)54));
	      this.blockUseSet.add(Byte.valueOf((byte)58));
	      this.blockUseSet.add(Byte.valueOf((byte)61));
	      this.blockUseSet.add(Byte.valueOf((byte)62));
	      this.blockUseSet.add(Byte.valueOf((byte)64));
	      this.blockUseSet.add(Byte.valueOf((byte)69));
	      this.blockUseSet.add(Byte.valueOf((byte)71));
	      this.blockUseSet.add(Byte.valueOf((byte)77));
	      this.blockUseSet.add(Byte.valueOf((byte)93));
	      this.blockUseSet.add(Byte.valueOf((byte)94));
	      this.blockUseSet.add(Byte.valueOf((byte)96));
	      this.blockUseSet.add(Byte.valueOf((byte)107));
	      this.blockUseSet.add(Byte.valueOf((byte)116));
	      this.blockUseSet.add(Byte.valueOf((byte)117));
	      this.blockUseSet.add(Byte.valueOf((byte)-126));
	      this.blockUseSet.add(Byte.valueOf((byte)-111));
	      this.blockUseSet.add(Byte.valueOf((byte)-110));
	      this.blockUseSet.add(Byte.valueOf((byte)-102));
	      this.blockUseSet.add(Byte.valueOf((byte)-98));
	    }
	    return this.blockUseSet.contains(Byte.valueOf(paramByte));
	  }
	  
	  public HashMap<Block, Double> getInRadius(Location paramLocation, double paramDouble)
	  {
	    return getInRadius(paramLocation, paramDouble, 999.0D);
	  }
	  
	  public HashMap<Block, Double> getInRadius(Location paramLocation, double paramDouble, boolean paramBoolean)
	  {
	    return getInRadius(paramLocation, paramDouble, 999.0D);
	  }
	  
	  public HashMap<Block, Double> getInRadius(Location paramLocation, double paramDouble1, double paramDouble2)
	  {
	    HashMap<Block, Double> localHashMap = new HashMap();
	    int i = (int)paramDouble1 + 1;
	    for (int j = -i; j <= i; j++) {
	      for (int k = -i; k <= i; k++) {
	        for (int m = -i; m <= i; m++) {
	          if (Math.abs(m) <= paramDouble2)
	          {
	            Block localBlock = paramLocation.getWorld().getBlockAt(
	              (int)(paramLocation.getX() + j), 
	              (int)(paramLocation.getY() + m), 
	              (int)(paramLocation.getZ() + k));
	            
	            double d = UtilMath.offset(paramLocation, localBlock
	              .getLocation().add(0.5D, 0.5D, 0.5D));
	            if (d <= paramDouble1) {
	              localHashMap.put(localBlock, 
	                Double.valueOf(1.0D - d / paramDouble1));
	            }
	          }
	        }
	      }
	    }
	    return localHashMap;
	  }
	  
	  public HashMap<Block, Double> getInRadius(Block paramBlock, double paramDouble)
	  {
	    HashMap<Block, Double> localHashMap = new HashMap();
	    int i = (int)paramDouble + 1;
	    for (int j = -i; j <= i; j++) {
	      for (int k = -i; k <= i; k++) {
	        for (int m = -i; m <= i; m++)
	        {
	          Block localBlock = paramBlock.getRelative(j, m, k);
	          
	          double d = UtilMath.offset(paramBlock.getLocation(), 
	            localBlock.getLocation());
	          if (d <= paramDouble) {
	            localHashMap.put(localBlock, 
	              Double.valueOf(1.0D - d / paramDouble));
	          }
	        }
	      }
	    }
	    return localHashMap;
	  }
	  
	  public boolean isBlock(ItemStack paramItemStack)
	  {
	    if (paramItemStack == null) {
	      return false;
	    }
	    return (paramItemStack.getTypeId() > 0) && (
	      paramItemStack.getTypeId() < 256);
	  }
	  
	  public Block getHighest(World paramWorld, int paramInt1, int paramInt2)
	  {
	    return getHighest(paramWorld, paramInt1, paramInt2, null);
	  }
	  
	  public Block getHighest(World paramWorld, int paramInt1, int paramInt2, HashSet<Material> paramHashSet)
	  {
	    Block localBlock = paramWorld.getHighestBlockAt(paramInt1, paramInt2);
	    while ((airFoliage(localBlock)) || 
	      (localBlock.getType() == Material.LEAVES) || (
	      (paramHashSet != null) && 
	      (paramHashSet.contains(localBlock.getType())))) {
	      localBlock = localBlock.getRelative(BlockFace.DOWN);
	    }
	    return localBlock.getRelative(BlockFace.UP);
	  }
	  
	  public ArrayList<Block> getSurrounding(Block paramBlock, boolean paramBoolean)
	  {
	    ArrayList<Block> localArrayList = new ArrayList();
	    if (paramBoolean)
	    {
	      for (int i = -1; i <= 1; i++) {
	        for (int j = -1; j <= 1; j++) {
	          for (int k = -1; k <= 1; k++) {
	            if ((i != 0) || (j != 0) || (k != 0)) {
	              localArrayList.add(paramBlock.getRelative(i, j, k));
	            }
	          }
	        }
	      }
	    }
	    else
	    {
	      localArrayList.add(paramBlock.getRelative(BlockFace.UP));
	      localArrayList.add(paramBlock.getRelative(BlockFace.DOWN));
	      localArrayList.add(paramBlock.getRelative(BlockFace.NORTH));
	      localArrayList.add(paramBlock.getRelative(BlockFace.SOUTH));
	      localArrayList.add(paramBlock.getRelative(BlockFace.EAST));
	      localArrayList.add(paramBlock.getRelative(BlockFace.WEST));
	    }
	    return localArrayList;
	  }
	  
	  public boolean isVisible(Block paramBlock)
	  {
	    for (Block localBlock : getSurrounding(paramBlock, false)) {
	      if (!localBlock.getType().isOccluding()) {
	        return true;
	      }
	    }
	    return false;
	  }
	}