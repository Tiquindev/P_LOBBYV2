package me.Patricktopgames.lobby.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Manager {

	public static boolean chat = true;
	public static ArrayList<Player> hat = new ArrayList();
	public static HashMap<Player, String> tell = new HashMap();
	public static HashMap<Player, String> score = new HashMap();
	public static HashMap<Player, String> players =  new HashMap();
	public static HashMap<Player, Long> cooldown = new HashMap();
	
	public static ItemStack criarItem(Material material, String nome){
		ItemStack stack = new ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}
	
	public static ItemStack criarItem(Material material, String nome, String lore){
		ItemStack stack = new ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		List<String> ls = new ArrayList();
		ls.add(lore);
		stack2.setLore(ls);
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}
	
	public static ItemStack criarItem(Material material, String nome, Enchantment encantamento){
		ItemStack stack = new ItemStack(material);
		ItemMeta stack2 = stack.getItemMeta();
		stack2.addEnchant(encantamento, 1, true);
		stack2.setDisplayName(nome);
		stack.setItemMeta(stack2);
		return stack;
	}
	
	public static void servidores(Player p){
		Inventory inv = Bukkit.createInventory(p, 27, "§nServidores");
		
		inv.setItem(12, Manager.criarItem(Material.BED, "§7Conectar á §eBedwars", "§aClique para se conectar !"));
		inv.setItem(14, Manager.criarItem(Material.DIAMOND_SWORD, "§7Conectar á §eSkyWars", "§cServidor em Manutençao !"));
		p.openInventory(inv);
	}
	
	public static void configuraçao(Player p){
		Inventory inv = Bukkit.createInventory(p, 36, "§nConfiguraçoes");
		
	    ItemStack head = new ItemStack(Material.SKULL_ITEM);
	    head.setDurability((short)3);
	    head.setAmount(1);
	    SkullMeta PlayerHeadMeta = (SkullMeta)head.getItemMeta();
	    PlayerHeadMeta.setDisplayName("§7Ver Jogadores");
	    PlayerHeadMeta.setOwner(p.getName());
	    PlayerHeadMeta.addEnchant(Enchantment.LUCK, 1, true);
	    head.setItemMeta(PlayerHeadMeta);
		inv.setItem(10, head);
		inv.setItem(12, criarItem(Material.NAME_TAG, "§7ScoreBoard"));
		inv.setItem(14, criarItem(Material.REDSTONE, "§7Tell"));
		
		if(p.hasPermission("lobby.admin")){
			inv.setItem(16, criarItem(Material.GOLD_NUGGET, "§7Chat"));
			if(chat == true){
				ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)5);
				ItemMeta vidro2 = vidro.getItemMeta();
				vidro2.setDisplayName("§aChat Ativado");
				vidro.setItemMeta(vidro2);
				inv.setItem(25, vidro);
			}else{
				ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
				ItemMeta vidro2 = vidro.getItemMeta();
				vidro2.setDisplayName("§cChat Desativado");
				vidro.setItemMeta(vidro2);
				inv.setItem(25, vidro);
			}
		}
		if(players.get(p) == "ativado"){
			ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)5);
			ItemMeta vidro2 = vidro.getItemMeta();
			vidro2.setDisplayName("§aPlayers Ativado");
			vidro.setItemMeta(vidro2);
			inv.setItem(19, vidro);
		}else{
			ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
			ItemMeta vidro2 = vidro.getItemMeta();
			vidro2.setDisplayName("§cPlayers Desativado");
			vidro.setItemMeta(vidro2);
			inv.setItem(19, vidro);
		}
		if(score.get(p) == "ativado"){
			ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)5);
			ItemMeta vidro2 = vidro.getItemMeta();
			vidro2.setDisplayName("§aScore Ativado");
			vidro.setItemMeta(vidro2);
			inv.setItem(21, vidro);
		}else{
			ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
			ItemMeta vidro2 = vidro.getItemMeta();
			vidro2.setDisplayName("§cScore Desativado");
			vidro.setItemMeta(vidro2);
			inv.setItem(21, vidro);
		}
		if(tell.get(p) == "ativado"){
			ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)5);
			ItemMeta vidro2 = vidro.getItemMeta();
			vidro2.setDisplayName("§aTell Ativado");
			vidro.setItemMeta(vidro2);
			inv.setItem(23, vidro);
		}else{
			ItemStack vidro = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)14);
			ItemMeta vidro2 = vidro.getItemMeta();
			vidro2.setDisplayName("§cTell Desativado");
			vidro.setItemMeta(vidro2);
			inv.setItem(23, vidro);
		}
		p.openInventory(inv);
	}
	
	public static void extras(Player p){
		Inventory inv = Bukkit.createInventory(p, 27, "§nExtras");
		
		inv.setItem(10, criarItem(Material.GLASS, "§eHats"));
		inv.setItem(12, criarItem(Material.FIREWORK, "§eEfeitos §c(Em Breve)"));
		inv.setItem(14, criarItem(Material.getMaterial(383), "§ePets"));
		inv.setItem(16, criarItem(Material.NETHER_STAR, "§eGadgets"));
		
	    ItemStack[] arrayOfItemStack;
	    int descpyro1 = (arrayOfItemStack = inv.getContents()).length;
	    for (int metapyro1 = 0; metapyro1 < descpyro1; metapyro1++){
	      ItemStack item = arrayOfItemStack[metapyro1];
	      if (item == null) {
	        inv.setItem(inv.firstEmpty(), criarItem(Material.STAINED_GLASS_PANE, " "));
	      }
	    }
		p.openInventory(inv);
	}
	
	public static void hats(Player p){
		Inventory inv = Bukkit.createInventory(p, 27, "§nHats");
		
		if(p.hasPermission("lobby.hat.vidro")){
			inv.addItem(criarItem(Material.GLASS, "§6Hat de Vidro", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.goldblock")){
			inv.addItem(criarItem(Material.GOLD_BLOCK, "§6Hat de Bloco de Ouro", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.diamondblock")){
			inv.addItem(criarItem(Material.DIAMOND_BLOCK, "§6Hat de Bloco de Diamante", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.beacon")){
			inv.addItem(criarItem(Material.BEACON, "§6Hat de Beacon", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.tnt")){
			inv.addItem(criarItem(Material.TNT, "§6Hat de TNT", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.pistao")){
			inv.addItem(criarItem(Material.PISTON_BASE, "§6Hat de Pistao", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.ejetor")){
			inv.addItem(criarItem(Material.DISPENSER, "§6Hat de Ejetor", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.mobspawner")){
			inv.addItem(criarItem(Material.MOB_SPAWNER, "§6Hat de MobSpawner", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.feno")){
			inv.addItem(criarItem(Material.HAY_BLOCK, "§6Hat de Bloco de Feno", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.portalfim")){
			inv.addItem(criarItem(Material.ENDER_PORTAL_FRAME, "§6Hat de Portal do Fim", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.slimeblock")){
			inv.addItem(criarItem(Material.SLIME_BLOCK, "§6Hat de Slime Block", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.fornalha")){
			inv.addItem(criarItem(Material.FURNACE, "§6Hat de Fornalha", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.enderchest")){
			inv.addItem(criarItem(Material.ENDER_CHEST, "§6Hat de Ender Chest", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.bau")){
			inv.addItem(criarItem(Material.CHEST, "§6Hat de Bau", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.folha")){
			inv.addItem(criarItem(Material.LEAVES, "§6Hat de Folha", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.cascalho")){
			inv.addItem(criarItem(Material.GRAVEL, "§6Hat de Cascalho", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.hat.estante")){
			inv.addItem(criarItem(Material.BOOKSHELF, "§6Hat de Estante", "§7Clique para selecionar !"));
		}
		inv.setItem(26, criarItem(Material.BARRIER, "§cRemover Hat"));
		
	    ItemStack[] arrayOfItemStack;
	    int descpyro1 = (arrayOfItemStack = inv.getContents()).length;
	    for (int metapyro1 = 0; metapyro1 < descpyro1; metapyro1++){
	      ItemStack item = arrayOfItemStack[metapyro1];
	      if (item == null) {
	        inv.setItem(inv.firstEmpty(), criarItem(Material.STAINED_GLASS_PANE, " "));
	      }
	    }
		p.openInventory(inv);
	}
	
	public static void invgadgets(Player p){
		Inventory inv = Bukkit.createInventory(p, 27, "§nGadgets");
		
		if(p.hasPermission("lobby.gadgets.bomba")){
			inv.addItem(criarItem(Material.CLAY_BALL, "§6Bomba", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.cookiesparty")){
			inv.addItem(criarItem(Material.COOKIE, "§6Cookies Party", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.fireworkparty")){
			inv.addItem(criarItem(Material.FIREWORK, "§6Firework Party", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.smokebomb")){
			inv.addItem(criarItem(Material.COAL, "§6Smoke Bomb", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.diamondparty")){
			inv.addItem(criarItem(Material.DIAMOND, "§6Diamond Party", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.paraquedas")){
			inv.addItem(criarItem(Material.LEASH, "§6Paraquedas", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.withershoot")){
			inv.addItem(criarItem(Material.SKULL_ITEM, "§6Whiter Shoot", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.vampire")){
			inv.addItem(criarItem(Material.GHAST_TEAR, "§6Vampire", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.vectortnt")){
			inv.addItem(criarItem(Material.TNT, "§6VectorTNT", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.gadgets.cowboy")){
			inv.addItem(criarItem(Material.CACTUS, "§6CowBoy", "§7Clique para selecionar !"));
		}
		inv.setItem(26, criarItem(Material.BARRIER, "§cRemover Gadgets"));
	    ItemStack[] arrayOfItemStack;
	    int descpyro1 = (arrayOfItemStack = inv.getContents()).length;
	    for (int metapyro1 = 0; metapyro1 < descpyro1; metapyro1++){
	      ItemStack item = arrayOfItemStack[metapyro1];
	      if (item == null) {
	        inv.setItem(inv.firstEmpty(), criarItem(Material.STAINED_GLASS_PANE, " "));
	      }
	    }
		p.openInventory(inv);
	}
	
	public static void pets(Player p){
		Inventory inv = Bukkit.createInventory(p, 27, "§nPets");
		if(p.hasPermission("lobby.pet.coelho")){
			inv.addItem(criarItem(Material.RABBIT, "§6Coelho", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.pet.vaca")){
			inv.addItem(criarItem(Material.LEASH, "§6Vaca", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.pet.galinha")){
			inv.addItem(criarItem(Material.COOKED_CHICKEN, "§6Galinha", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.pet.wolf")){
			inv.addItem(criarItem(Material.BONE, "§6Wolf", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.pet.porco")){
			inv.addItem(criarItem(Material.getMaterial(398), "§6Porco", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.pet.cavalo1")){
			inv.addItem(criarItem(Material.getMaterial(418), "§6Cavalo + Efeito Infernal", "§7Clique para selecionar !"));
		}
		if(p.hasPermission("lobby.pet.cavalo2")){
			inv.addItem(criarItem(Material.getMaterial(419), "§6Cavalo + Efeito Frozen", "§7Clique para selecionar !"));
		}
		inv.setItem(26, criarItem(Material.BARRIER, "§cRemover Pet"));
		
	    ItemStack[] arrayOfItemStack;
	    int descpyro1 = (arrayOfItemStack = inv.getContents()).length;
	    for (int metapyro1 = 0; metapyro1 < descpyro1; metapyro1++){
	      ItemStack item = arrayOfItemStack[metapyro1];
	      if (item == null) {
	        inv.setItem(inv.firstEmpty(), criarItem(Material.STAINED_GLASS_PANE, " "));
	      }
	    }
		p.openInventory(inv);
	}
	
	public static void efeitos(Player p){
		Inventory inv = Bukkit.createInventory(p, 27, "§nEfeitos");
		if(p.hasPermission("lobby.efeito.coraçao")){
			ItemStack efeito = new ItemStack(Material.REDSTONE);
			ItemMeta efeito2 = efeito.getItemMeta();
			efeito2.setDisplayName("§6Coraçao");
			List<String> lore = new ArrayList();
			lore.add("§7Clique com o §cDIREITO §7para usar na cabeça !");
			lore.add("§7Clique com o §cESQUERDO §7para usar no corpo !");
			efeito2.setLore(lore);
			efeito.setItemMeta(efeito2);
			inv.addItem(efeito);
		}
		if(p.hasPermission("lobby.efeito.mobspawner")){
			ItemStack efeito = new ItemStack(Material.MOB_SPAWNER);
			ItemMeta efeito2 = efeito.getItemMeta();
			efeito2.setDisplayName("§6MobSpawner");
			List<String> lore = new ArrayList();
			lore.add("§7Clique com o §cDIREITO §7para usar na cabeça !");
			lore.add("§7Clique com o §cESQUERDO §7para usar no corpo !");
			efeito2.setLore(lore);
			efeito.setItemMeta(efeito2);
			inv.addItem(efeito);
		}
		if(p.hasPermission("lobby.efeito.slime")){
			ItemStack efeito = new ItemStack(Material.SLIME_BALL);
			ItemMeta efeito2 = efeito.getItemMeta();
			efeito2.setDisplayName("§6Slime");
			List<String> lore = new ArrayList();
			lore.add("§7Clique com o §cDIREITO §7para usar na cabeça !");
			lore.add("§7Clique com o §cESQUERDO §7para usar no corpo !");
			efeito2.setLore(lore);
			efeito.setItemMeta(efeito2);
			inv.addItem(efeito);
		}
		inv.setItem(26, criarItem(Material.BARRIER, "§cRemover Efeito"));
		
	    ItemStack[] arrayOfItemStack;
	    int descpyro1 = (arrayOfItemStack = inv.getContents()).length;
	    for (int metapyro1 = 0; metapyro1 < descpyro1; metapyro1++){
	      ItemStack item = arrayOfItemStack[metapyro1];
	      if (item == null) {
	        inv.setItem(inv.firstEmpty(), criarItem(Material.STAINED_GLASS_PANE, " "));
	      }
	    }
		p.openInventory(inv);
	}
	
	  public static void Aparecer(Player p){
		 Player[] arrayOfPlayer;
		 int j;
		 int i;
		  j = Bukkit.getOnlinePlayers().size();
		   for (i = 0; i < j; i++){
		     for (Player pl : Bukkit.getOnlinePlayers()){
		     p.showPlayer(pl);
		    }
		  }
	  }
	  public static void Esconder(Player p){
		 Player[] arrayOfPlayer;
		 int j;
		 int i;
		  j = Bukkit.getOnlinePlayers().size();
	       for (i = 0; i < j; i++){
	         for (Player pl : Bukkit.getOnlinePlayers()){
	         p.hidePlayer(pl);
	        }
	       }
	  }
}