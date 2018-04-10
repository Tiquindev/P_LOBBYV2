package me.Patricktopgames.Gadgets.EventManager;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.Patricktopgames.Gadgets.EventManager.UpdateType;

public class UpdateEvent 
extends Event{

	  private static final HandlerList handlers = new HandlerList();
	  private UpdateType _type;
	  
	  public UpdateEvent(UpdateType paramUpdateType)
	  {
	    this._type = paramUpdateType;
	  }
	  
	  public UpdateType getType()
	  {
	    return this._type;
	  }
	  
	  public HandlerList getHandlers()
	  {
	    return handlers;
	  }
	  
	  public static HandlerList getHandlerList()
	  {
	    return handlers;
	  }
}
