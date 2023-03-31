package me.CAPS123987.smallspace;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.CAPS123987.dimension.SmallSpaceDim;
import me.CAPS123987.implementation.SizedBlock;
import me.CAPS123987.items.Items;
import me.CAPS123987.machines.BlockAssigner;
import me.CAPS123987.tabCompleater.*;


public class SmallSpace extends JavaPlugin implements SlimefunAddon {
	public static SmallSpace instance;
	Config cfg = new Config(this);
    @Override
    public void onEnable() {
    	
        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }
    	
    	instance = this;
    	
    	getCommand("SmallSpace").setTabCompleter(new TabC());
        //setup dimension 
        setGen();
        SmallSpaceDim dimension = new SmallSpaceDim();
        dimension.createWorld("SmallSpace");
        
       
        //this.getServer().getPluginManager().registerEvents(CommandListener.onCommand(null, null, getBugTrackerURL(), null),this);
        new SizedBlock(1,Items.SIZED_BLOCK1,Items.recipe_TEST_ITEM).register(this);
        new SizedBlock(2,Items.SIZED_BLOCK2,Items.recipe_TEST_ITEM).register(this);
        new SizedBlock(3,Items.SIZED_BLOCK3,Items.recipe_TEST_ITEM).register(this);
        new SizedBlock(4,Items.SIZED_BLOCK4,Items.recipe_TEST_ITEM).register(this);
        new BlockAssigner().register(this);
        
        
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
    
    public long getMax() {
    	return cfg.getConfiguration().getLong("max");
    }
    /*
     * set bukkit.yml
     */
    public void setGen() {
    	
    	FileConfiguration bukkit = YamlConfiguration.loadConfiguration( new File(getServer().getWorldContainer(), "bukkit.yml"));
        
    	if(!bukkit.contains("worlds.SmallSpace.generator")){
	        bukkit.set("worlds.SmallSpace.generator", "SmallSpace");
	
	        try {
				bukkit.save("bukkit.yml");
			} catch (IOException e) {
	
				this.getLogger().info(e.toString());
			};

			for(int i = 0; i != 5;i++) {
				this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "--------------------------------------------------------------");
				this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "--RESTART-THE-SERVER-FOR-PROPER-WORKING-OF-SMALLSPACE-PLUGIN--");
				this.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "--------------------------------------------------------------");
			}
	    }
    }
    @Override
    public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
    	if (!p.hasPermission("SmallSpace.admin")) {
    		p.sendMessage(ChatColor.RED+"No permition");
    		return false;
    	}
    	switch(args[0]) {
    	case "tptoId":
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
    		
    		
    		break;
    	case "blockId":
    		
    		try {
    			String id1 = args[1];
    			int value1 = Integer.parseInt(id1.replaceAll("[^0-9]", ""));
    			}catch(Exception e) {
    			p.sendMessage("Please enter Id of space");
    		}
    		String id1 = args[1];
    		int value1 = Integer.parseInt(id1.replaceAll("[^0-9]", ""));
    		Calculator.getLoc(id1).getBlock().setType(Material.BARRIER);
    		
    		break;
    	case "unblockId":
    		
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

}
