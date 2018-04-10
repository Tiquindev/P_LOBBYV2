package me.Patricktopgames.Gadgets.Util;

import java.util.HashSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.Patricktopgames.Gadgets.EventManager.UpdateEvent;
import me.Patricktopgames.Gadgets.EventManager.UpdateType;

import org.bukkit.event.Listener;

public class UtilLag 
implements Listener{

	private long _lastRun = -1L;
	  private int _count;
	  private static double _ticksPerSecond;
	  double _ticksPerSecondAverage;
	  private long _lastAverage;
	  private HashSet<Player> _monitoring = new HashSet();
	  
	  public UtilLag(JavaPlugin paramJavaPlugin)
	  {
	    this._lastRun = System.currentTimeMillis();
	    this._lastAverage = System.currentTimeMillis();
	  }
	  
	  @EventHandler
	  public void playerQuit(PlayerQuitEvent paramPlayerQuitEvent)
	  {
	    this._monitoring.remove(paramPlayerQuitEvent.getPlayer());
	  }
	  
	  @EventHandler
	  public void update(UpdateEvent paramUpdateEvent)
	  {
	    if (paramUpdateEvent.getType() != UpdateType.SEC) {
	      return;
	    }
	    long l = System.currentTimeMillis();
	    _ticksPerSecond = 1000.0D / (l - this._lastRun) * 20.0D;
	    if (this._count % 30 == 0)
	    {
	      this._ticksPerSecondAverage = (30000.0D / (l - this._lastAverage) * 20.0D);
	      this._lastAverage = l;
	    }
	    this._lastRun = l;
	    
	    this._count += 1;
	  }
	  
	  public static double getTicksPerSecond()
	  {
	    return _ticksPerSecond;
	  }
	  
	  public static double getTPS()
	  {
	    return _ticksPerSecond;
	  }
	  
	  public static boolean ServerisLag()
	  {
	    if (getTicksPerSecond() <= 18.0D) {
	      return true;
	    }
	    return false;
	  }
	}