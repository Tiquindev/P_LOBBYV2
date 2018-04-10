package me.Patricktopgames.Gadgets.Util;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Patricktopgames.lobby.API.Manager;

public enum Tipos {

	  NENHUM,  BOMBA,  COOKIEK,  FIREWORKP, SMOKEBOMB,  DIAMONDP,  PARAQUEDAS,  WITHERSHOOT, VAMPIRE,  VECTORTNT,  COWBOY;
	  
	  public static final Map<Player, String> getPlayerGadget = new HashMap();
	  
	  public static boolean playerHasGadget(Player p){
	    if (getPlayerGadget.get(p) != "Nenhum") {
	      return true;
	    }
	    return false;
	  }
	  
	  public static void setGadget(Player p, Tipos gadgetTipo)
	  {
	    switch (gadgetTipo)
	    {
	    case NENHUM: 
	      getPlayerGadget.put(p, "Nenhum");
	      ItemStack air = new ItemStack(Material.AIR);
	      p.sendMessage("§cGadgets removido !");
	      p.getInventory().setItem(0, air);
	      p.closeInventory();
	      break;
	    case BOMBA: 
	        getPlayerGadget.put(p, "Bomba");
	        p.getInventory().setItem(0, Manager.criarItem(Material.CLAY_BALL, "§6§lBomba §7(Clique Direito)"));
	        p.closeInventory();
	        p.sendMessage("§aGadgets selecionado !");
	      break;
	    case COOKIEK: 
	        getPlayerGadget.put(p, "Cookies Party");
	        p.sendMessage("§aGadgets selecionado !");
	        p.getInventory().setItem(0, Manager.criarItem(Material.COOKIE, "§6§lCookieKookie §7(Clique Direito)"));
	        p.closeInventory();
	      break;
	    case FIREWORKP: 
	        getPlayerGadget.put(p, "Firework Party");
	        p.sendMessage("§aGadgets selecionado !");
	        p.getInventory().setItem(0, Manager.criarItem(Material.FIREWORK, "§6§lFirework Party §7(Clique Direito)"));
	        p.closeInventory();
	      break;
	    case SMOKEBOMB: 
	        getPlayerGadget.put(p, "Smoke Bomb");
	        p.sendMessage("§aGadgets selecionado !");
	        p.getInventory().setItem(0, Manager.criarItem(Material.COAL, "§6§lSmoke Bomb §7(Clique Direito)"));
	        p.closeInventory();
	      break;
	    case DIAMONDP: 
	          getPlayerGadget.put(p, "Diamond Party");
	          p.sendMessage("§aGadgets selecionado !");
	          p.getInventory().setItem(0, Manager.criarItem(Material.DIAMOND, "§6§lDiamond Party §7(Clique Direito)"));
	          p.closeInventory();
	        break;
	    case PARAQUEDAS: 
	          getPlayerGadget.put(p, "Paraquedas");
	          p.sendMessage("§aGadgets selecionado !");
	          p.getInventory().setItem(0, Manager.criarItem(Material.LEASH, "§6§lParaquedas §7(Clique Direito)"));
	          p.closeInventory();
	        break;
	    case WITHERSHOOT: 
	        getPlayerGadget.put(p, "Wither Shoot");
	        p.sendMessage("§aGadgets selecionado !");
	        p.getInventory().setItem(0, Manager.criarItem(Material.SKULL_ITEM, "§6§lWither Shoot §7(Clique Direito)"));
	        p.closeInventory();
	      break;
	    case VAMPIRE: 
	          getPlayerGadget.put(p, "Vampire");
	          p.sendMessage("§aGadgets selecionado !");
	          p.getInventory().setItem(0, Manager.criarItem(Material.GHAST_TEAR, "§6§lVampire §7(Clique Direito)"));
	          p.closeInventory();
	        break;
	    case VECTORTNT: 
	        getPlayerGadget.put(p, "VectorTNT");
	        p.sendMessage("§aGadgets selecionado !");
	        p.getInventory().setItem(0, Manager.criarItem(Material.TNT, "§6§lVectorTNT §7(Clique Direito)"));
	        p.closeInventory();
	      break;
	    case COWBOY: 
	        getPlayerGadget.put(p, "CowBoy");
	        p.sendMessage("§aGadgets selecionado !");
	        p.getInventory().setItem(0, Manager.criarItem(Material.CACTUS, "§6§lCowBoy §7(Clique Direito)"));
	        p.closeInventory();
	      break;
	    }
	  }
	}