package me.Patricktopgames.lobby;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;

import me.Patricktopgames.lobby.Main;
import me.Patricktopgames.lobby.API.Manager;
import me.Patricktopgames.lobby.Gadgets.Pets;
import me.Patricktopgames.Gadgets.EventManager.ParticleEffect;
import me.Patricktopgames.Gadgets.Montarias.MountHandler;
import me.Patricktopgames.Gadgets.Montarias.Mounts;
import me.Patricktopgames.Gadgets.Util.Tipos;

public class Eventos 
implements Listener{
	
	@EventHandler
	public void entrar(PlayerJoinEvent e){
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		Board.setarBoard(p);
		Manager.score.put(p, "ativado");
		Manager.tell.put(p, "ativado");
		Manager.players.put(p, "ativado");
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(p.getWorld().getSpawnLocation());
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.getInventory().setItem(7, Manager.criarItem(Material.SLIME_BALL, "§c§lDesativar Jogadores", Enchantment.LUCK));
		p.getInventory().setItem(0, Manager.criarItem(Material.COMPASS, "§f§lServidores", Enchantment.LUCK));
		p.getInventory().setItem(1, Manager.criarItem(Material.SKULL_ITEM, "§f§lConfiguraçoes", Enchantment.LUCK));
	    p.getInventory().setItem(5, Manager.criarItem(Material.CHEST, "§f§lExtras", Enchantment.LUCK));
	    p.getInventory().setItem(3, Manager.criarItem(Material.EMERALD, "§f§lLoja", Enchantment.LUCK));
	    p.getInventory().setItem(8, Manager.criarItem(Material.NETHER_STAR, "§f§lLobbies", Enchantment.LUCK));
	}
	
	@EventHandler
	public void sair(PlayerQuitEvent e){
		e.setQuitMessage(null);
		Manager.hat.remove(e.getPlayer());
	}
	
	@EventHandler
	public void dropar(PlayerDropItemEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void clicar(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand().getType() == Material.COMPASS){
			Manager.servidores(p);
			p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
		}
		if(p.getItemInHand().getType() == Material.SKULL_ITEM){
			Manager.configuraçao(p);
			p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
		}
		if(p.getItemInHand().getType() == Material.CHEST){
			Manager.extras(p);
			p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
		}
		if(p.getItemInHand().getType() == Material.SLIME_BALL){
			if(p.getItemInHand().getItemMeta().getDisplayName().equals("§c§lDesativar Jogadores")){
				if(Manager.players.get(p) == "ativado"){
					Manager.players.put(p, "desativado");
					Manager.Esconder(p);
					p.getInventory().remove(Material.SLIME_BALL);
					p.getInventory().setItem(7, Manager.criarItem(Material.SLIME_BALL, "§a§lAtivar Jogadores", Enchantment.LUCK));
					p.updateInventory();
					p.sendMessage("§9§LitleGames §6» §7Jogadores desativados !");
					p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
					return;
				}
			}
			if(p.getItemInHand().getItemMeta().getDisplayName().equals("§a§lAtivar Jogadores")){
				if(Manager.players.get(p) == "desativado"){
					Manager.players.put(p, "ativado");
					Manager.Aparecer(p);
					p.getInventory().remove(Material.SLIME_BALL);
					p.getInventory().setItem(7, Manager.criarItem(Material.SLIME_BALL, "§c§lDesativar Jogadores", Enchantment.LUCK));
					p.updateInventory();
					p.sendMessage("§9§LitleGames §6» §7Jogadores ativados !");
					p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void fome(FoodLevelChangeEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler
	public void motd(ServerListPingEvent e){
		e.setMotd("§9LitleGames §7(1.8) ");
	}
	
	private Map<String, Long> timeout = new HashMap();

	@EventHandler
	 public void onChat(AsyncPlayerChatEvent e){
	   Player p = e.getPlayer();
	   if ((this.timeout.containsKey(p.getName())) && 
	     (((Long)this.timeout.get(p.getName())).longValue() > System.currentTimeMillis())){
	       p.sendMessage("§cAguarde para falar novamente !");
	     e.setCancelled(true);
	     return;
	   }
	   this.timeout.put(p.getName(), Long.valueOf(System.currentTimeMillis() + 2000L));
	 }
	
	@EventHandler
	public void move(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STATIONARY_WATER){
			if(p.getLocation().getBlockY() == 94){
				p.teleport(p.getWorld().getSpawnLocation());
			}
		}
	}
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(e.getMessage().contains("%")){
			e.setCancelled(true);
			return;
		}
		if(Manager.chat == false){
			e.setCancelled(true);
			p.sendMessage("§9LitleGames §6» §7O chat dos servidores estao desativados !");
			return;
		}
		
		}
	
	
	@EventHandler
	public void colocarBloco(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void quebrarBloco(BlockBreakEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode() != GameMode.CREATIVE){
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void ClicarServidores(InventoryClickEvent e){
	  if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
	    Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    if (inv.getTitle().equals("§nServidores")){
	      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
	      e.setCancelled(true);
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Conectar á §eBedwars")){
	    	  p.chat("/server bedwars");
	    	  p.closeInventory();
	      }
	    }
	  }
	}
	
	@EventHandler
	public void ClicarConfiguraçoes(InventoryClickEvent e){
	  if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
	    Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    if (inv.getTitle().equals("§nConfiguraçoes")){
	      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
	      e.setCancelled(true);
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aPlayers Ativado")){
				Manager.players.put(p, "desativado");
				Manager.Esconder(p);
				p.getInventory().remove(Material.SLIME_BALL);
				p.getInventory().setItem(3, Manager.criarItem(Material.SLIME_BALL, "§a§lAtivar Jogadores", Enchantment.LUCK));
				p.updateInventory();
				p.sendMessage("§9LitleGames §6» §7Jogadores desativados !");
				p.closeInventory();
				Manager.configuraçao(p);
				return;
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cPlayers Desativado")){
				Manager.players.put(p, "ativado");
				Manager.Aparecer(p);
				p.getInventory().remove(Material.SLIME_BALL);
				p.getInventory().setItem(3, Manager.criarItem(Material.SLIME_BALL, "§c§lDesativar Jogadores", Enchantment.LUCK));
				p.updateInventory();
				p.sendMessage("§9LitleGames §6» §7Jogadores ativados !");
				p.closeInventory();
				Manager.configuraçao(p);
				return;
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aScore Ativado")){
	    	  Board.setarNull(p);
	    	  Manager.score.put(p, "desativado");
			  p.sendMessage("§9LitleGames §6» §7Scoreboard desativado !");
			  p.closeInventory();
			  Manager.configuraçao(p);
			  return;
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cScore Desativado")){
	    	  Board.setarBoard(p);
	    	  Manager.score.put(p, "ativado");
			  p.sendMessage("§9LitleGames §6» §7Scoreboard ativado !");
			  p.closeInventory();
			  Manager.configuraçao(p);
			  return;
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTell Ativado")){
	    	  Manager.tell.put(p, "desativado");
			  p.sendMessage("§9LitleGames §6» §7Tell desativado !");
			  p.closeInventory();
			  Manager.configuraçao(p);
			  return;
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cTell Desativado")){
	    	  Manager.tell.put(p, "ativado");
			  p.sendMessage("§9LitleGames §6» §7Tell ativado !");
			  p.closeInventory();
			  Manager.configuraçao(p);
			  return;
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aChat Ativado")){
	    	  Manager.chat = false;
			  p.sendMessage("§9LitleGames §6» §7Chat desativado !");
			  p.closeInventory();
			  Manager.configuraçao(p);
			  return;
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cChat Desativado")){
	    	  Manager.chat = true;
			  p.sendMessage("§9LitleGames §6» §7Chat ativado !");
			  p.closeInventory();
			  Manager.configuraçao(p);
			  return;
	      }
	    }
	  }
	}
	
	@EventHandler
	public void clicarEnt(PlayerInteractEntityEvent e){
		if(!(e.getRightClicked() instanceof Player)){
			return;
		}
		Player p = (Player)e.getRightClicked();
		if(p.getName().equalsIgnoreCase("§6")){
			e.getPlayer().chat("/connectSkywars");
			return;
		}
		if(p.getName().equalsIgnoreCase("§7")){
			e.getPlayer().sendMessage("§cServidor em manutenção !");
			return;
		}
		if(p.getName().equalsIgnoreCase("§c????")){
			e.getPlayer().sendMessage("§cEsse servidor ainda não foi lançado !");
			return;
		}
	}
	
	@EventHandler
	public void ClicarExtras(InventoryClickEvent e){
	  if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
	    Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    if (inv.getTitle().equals("§nExtras")){
	      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
	      e.setCancelled(true);
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eHats")){
	    	  p.closeInventory();
	    	  Manager.hats(p);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eEfeitos §c(Em Breve)")){
	    	  p.closeInventory();
	    	  p.sendMessage("§cEste sistema ainda nao esta funcionando 100% !");
	    	  Manager.efeitos(p);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§ePets")){
	    	  p.closeInventory();
	    	  Manager.pets(p);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eGadgets")){
	    	  p.closeInventory();
	    	  Manager.invgadgets(p);
	      }
	    }
	  }
	}
	
	@EventHandler
	public void ClicarHats(InventoryClickEvent e){
	  if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
	    Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    if (inv.getTitle().equals("§nHats")){
	      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
	      e.setCancelled(true);
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Vidro")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.GLASS, "§6Hat de Vidro"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Bloco de Ouro")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.GOLD_BLOCK, "§6Hat de Bloco de Ouro"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Bloco de Diamante")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.DIAMOND_BLOCK, "§6Hat de Bloco de Diamante"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Beacon")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.BEACON, "§6Hat de Beacon"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de TNT")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.TNT, "§6Hat de TNT"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Pistao")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.PISTON_BASE, "§6Hat de Pistao"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Ejetor")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.DISPENSER, "§6Hat de Ejetor"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de MobSpawner")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.MOB_SPAWNER, "§6Hat de MobSpawner"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de de Bloco de Feno")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.HAY_BLOCK, "§6Hat de Bloco de Feno"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Portal do Fim")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.ENDER_PORTAL_FRAME, "§6Hat de Portal do Fim"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Slime Block")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.SLIME_BLOCK, "§6Hat de Slime Block"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Fornalha")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.FURNACE, "§6Hat de Fornalha"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Ender Chest")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.ENDER_CHEST, "§6Hat de Ender Chest"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Bau")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.CHEST, "§6Hat de Bau"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Folha")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.LEAVES, "§6Hat de Folha"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Cascalho")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.GRAVEL, "§6Hat de Cascalho"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Hat de Estante")){
	    	  p.closeInventory();
	    	  Manager.hat.add(p);
	    	  p.sendMessage("§aHat selecionada !");
	    	  p.getInventory().setHelmet(Manager.criarItem(Material.BOOKSHELF, "§6Hat de Estante"));
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemover Hat")){
	    	  if(!Manager.hat.contains(p)){
	    		  p.closeInventory();
	    		  p.sendMessage("§cVoce precisa estar usando uma Hat para fazer isto !");
	    		  return;
	    	  }
	    	  Manager.hat.remove(p);
	    	  p.closeInventory();
	    	  p.sendMessage("§cHat removido !");
	    	  p.getInventory().setHelmet(null);
	      }
	    }
	  }
	}
	
	@EventHandler
	public void ClicarGadgets(InventoryClickEvent e){
	  if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
	    Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    if (inv.getTitle().equals("§nGadgets")){
	      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
	      e.setCancelled(true);
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Bomba")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.BOMBA);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Cookies Party")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.COOKIEK);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Firework Party")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.FIREWORKP);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Smoke Bomb")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.SMOKEBOMB);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Diamond Party")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.DIAMONDP);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Paraquedas")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.PARAQUEDAS);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Whiter Shoot")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.WITHERSHOOT);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Vampire")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.VAMPIRE);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6VectorTNT")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.VECTORTNT);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6CowBoy")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.COWBOY);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemover Gadgets")){
	    	  p.closeInventory();
	    	  Tipos.setGadget(p, Tipos.NENHUM);
	      }
	    }
	  }
	}
	
	@EventHandler
	public void ClicarPets(InventoryClickEvent e){
	  if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
	    Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    if (inv.getTitle().equals("§nPets")){
	      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
	      e.setCancelled(true);
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Coelho")){
	          if (MountHandler.HasPet(p)) {
	              MountHandler.removePlayerMount(p);
	          }
	    	  p.closeInventory();
	    	  Pets.PetsType.setPet(p, Pets.PetsType.COELHO);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Vaca")){
	          if (MountHandler.HasPet(p)) {
	              MountHandler.removePlayerMount(p);
	          }
	    	  p.closeInventory();
	    	  Pets.PetsType.setPet(p, Pets.PetsType.VACA);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Galinha")){
	          if (MountHandler.HasPet(p)) {
	              MountHandler.removePlayerMount(p);
	          }
	    	  p.closeInventory();
	    	  Pets.PetsType.setPet(p, Pets.PetsType.GALINHA);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Wolf")){
	          if (MountHandler.HasPet(p)) {
	              MountHandler.removePlayerMount(p);
	          }
	    	  p.closeInventory();
	    	  Pets.PetsType.setPet(p, Pets.PetsType.WOLF);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Porco")){
	          if (MountHandler.HasPet(p)) {
	              MountHandler.removePlayerMount(p);
	          }
	    	  p.closeInventory();
	    	  Pets.PetsType.setPet(p, Pets.PetsType.PORCO);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Cavalo + Efeito Infernal")){
	          if (MountHandler.HasPet(p)) {
	              MountHandler.removePlayerMount(p);
	          }
	    	  p.closeInventory();
	    	  Mounts.summonMount(p, Mounts.INFERNO);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Cavalo + Efeito Frozen")){
	          if (MountHandler.HasPet(p)) {
	              MountHandler.removePlayerMount(p);
	          }
	    	  p.closeInventory();
	    	  Mounts.summonMount(p, Mounts.FROZEN);
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemover Pet")){
	    	  p.closeInventory();
	    	  Pets.PetsType.removePet(p);
	    	  MountHandler.removePlayerMount(p);
	    	  p.sendMessage("§cPet removido !");
	      }
	    }
	  }
	}
	
	@EventHandler
	public void ClicarEfeitos(InventoryClickEvent e){
	  if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null)){
	    Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    if (inv.getTitle().equals("§nEfeitos")){
	      p.playSound(p.getLocation(), Sound.WOOD_CLICK, 5.0F, 5.0F);
	      e.setCancelled(true);
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Coraçao")){
	    	  if(!Main.getParticles().hasEffect(p)){
		    	  Main.getParticles().rorationEffect(p, ParticleEffect.HEART);
		    	  p.sendMessage("§aParticula ativada !");
	    	  }
	    	  p.closeInventory();
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Slime")){
	    	  if(!Main.getParticles().hasEffect(p)){
		    	  Main.getParticles().rorationEffect(p, ParticleEffect.SLIME);
		    	  p.sendMessage("§aParticula ativada !");
	    	  }
	    	  p.closeInventory();
	      }
	      if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemover Efeito")){
		      Main.getParticles().stopAll(p);
		      p.closeInventory();
	      }
	    }
	  }
	}
	
	  @EventHandler
	  public void TirarHats(InventoryClickEvent e){
	    Player p = (Player)e.getWhoClicked();
	    Inventory inv = e.getInventory();
	    if(!inv.getName().contains("§n")){
		    if (Manager.hat.contains(p)){
		    	e.setCancelled(true);
		    	p.sendMessage("§cVoce nao pode fazer isto enquanto esta usando uma Hat !");
		    }
	    }
	  }
}