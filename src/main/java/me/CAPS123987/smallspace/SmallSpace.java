package me.CAPS123987.smallspace;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import me.CAPS123987.cargo.SpaceInterface;
import me.CAPS123987.cargo.ExportBus;
import me.CAPS123987.cargo.ImportBus;
import me.CAPS123987.dimension.SmallSpaceDim;
import me.CAPS123987.implementation.SizedBlock;
import me.CAPS123987.implementation.Teleporterss;
import me.CAPS123987.items.Items;
import me.CAPS123987.machines.BlockAssigner;
import me.CAPS123987.tabCompleater.*;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;


public class SmallSpace extends JavaPlugin implements SlimefunAddon {
	public static SmallSpace instance;
	Config cfg = new Config(this);
    @Override
    public void onEnable() {
    	
        if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
        	
        	GitHubBuildsUpdater
        	updater 
        	= 
        	new GitHubBuildsUpdater(
        			this
        			, this.getFile()
        			, "CAPS123987/SmallSpace/master");
        	
        	updater.start();
        	
        }
    	
    	instance = this;
    	
    	getCommand("SmallSpace").setTabCompleter(new TabC());
        //setup dimension 
        WorldCreator worldCreator = new WorldCreator("SmallSpace");
        worldCreator.generator(new SmallSpaceDim());
        worldCreator.createWorld();
        
        new SlimefunItem(Items.smallSpace, Items.SPACE_STABILIZER, RecipeType.ENHANCED_CRAFTING_TABLE , Items.recipe_SPACE_STABILIZER).register(this);
        //this.getServer().getPluginManager().registerEvents(CommandListener.onCommand(null, null, getBugTrackerURL(), null),this);
        new SizedBlock(1,Items.SIZED_BLOCK1,Items.recipe_SIZED_BLOCK1).register(this);
        new SizedBlock(2,Items.SIZED_BLOCK2,Items.recipe_SIZED_BLOCK2).register(this);
        new SizedBlock(3,Items.SIZED_BLOCK3,Items.recipe_SIZED_BLOCK3).register(this);
        new SizedBlock(4,Items.SIZED_BLOCK4,Items.recipe_SIZED_BLOCK4).register(this);
        new Teleporterss().register(this);
        new BlockAssigner().register(this);
        
        new SpaceInterface().register(this);
        ImportBus ibus = new ImportBus();
        ibus.register(this);
        ibus.setHidden(true);

        ExportBus ebus = new ExportBus();
        ebus.register(this);
        ebus.setHidden(true);
        
        
    }
    

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new SmallSpaceDim();
    }
    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }
    public static SmallSpace getInstance() {
        return instance;
    }
    
    /*
     * set bukkit.yml
     */
    @Override
    public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
    	
    	
    	
    	
    	
    	
    	switch(args[0]) {
    	
	    	case "tptoId":
	    		if (!p.hasPermission("SmallSpace.admin")) {
	        		p.sendMessage(ChatColor.RED+"No permition");
	        		return false;
	        	}
	    		if(p instanceof Player) {
		    		try {
		    			String id = args[1];
		    			int value = Integer.parseInt(id.replaceAll("[^0-9]", ""));
		    			}catch(Exception e) {
		    			p.sendMessage("Please enter Id of space");
		    			return true;
		    		}
		    		String id = args[1];
		    		int value = Integer.parseInt(id.replaceAll("[^0-9]", ""));
		    		tryToTeleport(Calculator.getLoc(id),p);
	    		}else {
	    			notSeder(p);
	    			return true;
	    		}
	    		
	    		break;
	    		
	    		
	    	case "blockId":
	    		if (!p.hasPermission("SmallSpace.admin")) {
	        		p.sendMessage(ChatColor.RED+"No permition");
	        		return false;
	        	}
	    		try {
	    			String id1 = args[1];
	    			int value1 = Integer.parseInt(id1.replaceAll("[^0-9]", ""));
	    			}catch(Exception e) {
	    			p.sendMessage("Please enter Id of space");
	    			return true;
	    		}
	    		String id1 = args[1];
	    		int value1 = Integer.parseInt(id1.replaceAll("[^0-9]", ""));
	    		Calculator.getLoc(id1).getBlock().setType(Material.BARRIER);
	    		
	    		break;
	    		
	    		
	    	case "unblockId":
	    		if (!p.hasPermission("SmallSpace.admin")) {
	        		p.sendMessage(ChatColor.RED+"No permition");
	        		return false;
	        	}
	    		try {
	    			String id2 = args[1];
	    			int value2 = Integer.parseInt(id2.replaceAll("[^0-9]", ""));
	    			}catch(Exception e) {
	    			p.sendMessage("Please enter Id of space");
	    			return true;
	    		}
	    		String id2 = args[1];
	    		int value2 = Integer.parseInt(id2.replaceAll("[^0-9]", ""));
	    		Calculator.getLoc(id2).getBlock().setType(Material.AIR);
	    		
	    		break;
	    		
	    		
	    	case "teleportRemove":
	    		if (!p.hasPermission("SmallSpace.admin")) {
	        		p.sendMessage(ChatColor.RED+"No permition");
	        		return false;
	        	}
	    		if(!(p instanceof Player)) {
	    			notSeder(p);
	    			return true;
	    		}
	    		
	    		Player pp;
	    		pp = (Player) p;
	    		Block b =pp.getTargetBlockExact(20);
	    		if(!BlockStorage.hasBlockInfo(b)) {
	    			p.sendMessage("this block doesn't have BlockStorage data");
	    			return true;
	    		}
	    		if(!BlockStorage.getLocationInfo(b.getLocation(),"id").equals("TELEPORT")) {
	    			p.sendMessage("this block is not teleporter");
	    			return true;
	    		}
	    		BlockStorage.clearBlockInfo(b);
	    		
	    		break;
	    		
		    	case "memberAdd":
		    		if(1<args.length) {
		    			if(p instanceof Player) {
		    				groupAdd(p,args);
		    			}else {
		    				notSeder(p);
		    			}
		    		}
		    		return true;
		    		
		    		
		    	case "memberRemove":
		    		if(1<args.length) {
		    			if(p instanceof Player) {
		    				groupRemove(p,args);
		    			}else {
		    				notSeder(p);
		    			}
		    		}
		    		return true;
	    	
	    	default:
	    		p.sendMessage("Unknown command");
    	}
    	
    	return true;
		
	}
    public void tryToTeleport(Location l,CommandSender p) {
    	World world = Bukkit.getWorld("SmallSpace");
    	Location newloc2 = new Location(world,l.getX()+0.5,l.getY()+1,l.getZ()+0.5);
    	if(newloc2.getBlock().getType()==Material.BEDROCK) {
    		p.sendMessage("Space is not initialized yet");
    		return;
    	}
    	Player p2 = (Player) p;
    	p2.teleport(newloc2);
    	
    }
    public void notSeder(CommandSender p) {
    	p.sendMessage("You must by Player to use this command");
    }
    
    
    
    public boolean groupAdd(CommandSender p, String[] args) {
    	
    	final Player pp = (Player) p;
		Block b =pp.getTargetBlockExact(20);
		if(!BlockStorage.hasBlockInfo(b)) {
			p.sendMessage("this block doesn't have BlockStorage data");
			return false;
		}

		SlimefunItem item = BlockStorage.check(b);
		if (!(item instanceof SizedBlock)) {
			p.sendMessage("this block is not SIZED BLOCK");
			return false;
		}
		
		if(!(BlockStorage.getLocationInfo(b.getLocation(),"owner").equals(pp.getName())) && !p.hasPermission("SmallSpace.admin")) {
			pp.sendMessage("You are not owner of this block");
			return false;
		}
		
		List<String> info = Calculator.playersGet(BlockStorage.getLocationInfo(b.getLocation(), "Players"));
		if(info.contains(args[1])) {
			return false;
		}
		
		String old = BlockStorage.getLocationInfo(b.getLocation(), "Players");
		
		if(old == null) {
			BlockStorage.addBlockInfo(b, "Players",args[1]+";");
		}else {
			BlockStorage.addBlockInfo(b, "Players",old + args[1] + ";");
    	}
		pp.sendMessage("Player "+args[1]+" added");
		return true;
		
		
    }
    
    
    
    public boolean groupRemove(CommandSender p, String[] args) {
    	
    	final Player pp = (Player) p;
		Block b =pp.getTargetBlockExact(20);
		if(!BlockStorage.hasBlockInfo(b)) {
			p.sendMessage("this block doesn't have BlockStorage data");
			return false;
		}
		
		SlimefunItem item = BlockStorage.check(b);
		if (!(item instanceof SizedBlock)) {
			p.sendMessage("this block is not SIZED BLOCK");
			return false;
		}
		
		if(!(BlockStorage.getLocationInfo(b.getLocation(),"owner").equals(pp.getName())) && !p.hasPermission("SmallSpace.admin")) {
			pp.sendMessage("You are not owner of this block");
			return false;
		}
		
		String arg = args[1];
		
		List<String> info = Calculator.playersGet(BlockStorage.getLocationInfo(b.getLocation(), "Players"));
		
		if(info.contains(args[1])) {
				
			if(info.contains(arg)) {
				info.remove(info.indexOf(arg));
			}	
			String text = "";
			for(String s : info) {
				text = text + s + ";";
				
			}
			BlockStorage.addBlockInfo(b, "Players", text);
			pp.sendMessage("Player "+args[1]+" removed");
			
			return true;
		}else {
			pp.sendMessage("Player "+args[1]+" isn't registered in this block");
			return false;
		}
		
		
		
    }

}
