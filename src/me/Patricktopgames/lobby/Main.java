package me.Patricktopgames.lobby;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.Patricktopgames.lobby.API.Prefix;
import me.Patricktopgames.lobby.Gadgets.Pets;
import me.Patricktopgames.Gadgets.Efeitos.UtilParticleType;
import me.Patricktopgames.Gadgets.Gadgets.Bomba;
import me.Patricktopgames.Gadgets.Gadgets.CookieKookie;
import me.Patricktopgames.Gadgets.Gadgets.CowBoy;
import me.Patricktopgames.Gadgets.Gadgets.DiamondParty;
import me.Patricktopgames.Gadgets.Gadgets.FireworkParty;
import me.Patricktopgames.Gadgets.Gadgets.SmokeBomb;
import me.Patricktopgames.Gadgets.Gadgets.Vampire;
import me.Patricktopgames.Gadgets.Gadgets.VectorTNT;
import me.Patricktopgames.Gadgets.Gadgets.WitherShoot;
import me.Patricktopgames.Gadgets.Montarias.Frozen;
import me.Patricktopgames.Gadgets.Montarias.Infernal;
import me.Patricktopgames.Gadgets.Montarias.MountHandler;
import me.Patricktopgames.Gadgets.Util.PlayerIsInMoviment;
import me.Patricktopgames.Gadgets.Util.UtilBlock;
import me.Patricktopgames.Gadgets.Util.UtilLag;
import me.Patricktopgames.Gadgets.Util.UtilLocations;
import me.Patricktopgames.Gadgets.Util.UtilPet;

public class Main extends JavaPlugin{
	
	public static Plugin plugin;
	private static UtilBlock ub;
	private static UtilLocations ul;
	private static UtilParticleType upt;
	
	public void onEnable() {
		plugin = this;
		ub = new UtilBlock();
		ul = new UtilLocations();
		upt = new UtilParticleType();
		getLogger().info("Plugin ativado !");
		getServer().getPluginManager().registerEvents(new Eventos(), this);
		getServer().getPluginManager().registerEvents(new PlayerIsInMoviment(), this);
		getServer().getPluginManager().registerEvents(new Pets(), this);
		getServer().getPluginManager().registerEvents(new UtilPet(), this);
		getServer().getPluginManager().registerEvents(new MountHandler(), this);
		getServer().getPluginManager().registerEvents(new Frozen(), this);
		getServer().getPluginManager().registerEvents(new Infernal(), this);
		getServer().getPluginManager().registerEvents(new UtilLag(this), this);
		getServer().getPluginManager().registerEvents(new Bomba(), this);
		getServer().getPluginManager().registerEvents(new CookieKookie(), this);
		getServer().getPluginManager().registerEvents(new CowBoy(), this);
		getServer().getPluginManager().registerEvents(new DiamondParty(), this);
		getServer().getPluginManager().registerEvents(new FireworkParty(), this);
		getServer().getPluginManager().registerEvents(new SmokeBomb(), this);
		getServer().getPluginManager().registerEvents(new Vampire(), this);
		getServer().getPluginManager().registerEvents(new VectorTNT(), this);
		getServer().getPluginManager().registerEvents(new WitherShoot(), this);
		getServer().getPluginManager().registerEvents(new Prefix(), this);
		
		getCommand("score").setExecutor(new Score());
	}
	
	  public static UtilBlock getUtilBlock(){
	    return ub;
	  }
	  
	  public static UtilLocations getUtilLocation(){
	    return ul;
	  }
	  
	  public static UtilParticleType getParticles(){
	    return upt;
	  }
}