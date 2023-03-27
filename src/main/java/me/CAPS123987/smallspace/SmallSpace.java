package me.CAPS123987.smallspace;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
import me.CAPS123987.*;
import me.CAPS123987.dimension.SmallSpaceDim;
import me.CAPS123987.implementation.SizedBlock;
import me.CAPS123987.items.Items;


public class SmallSpace extends JavaPlugin implements SlimefunAddon {
	public static SmallSpace instance;
    @Override
    public void onEnable() {
    	Config cfg = new Config(this);
        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }
    	
    	instance = this;
    	
        //setup dimension 
        setGen();
        SmallSpaceDim dimension = new SmallSpaceDim();
        dimension.createWorld("SmallSpace");
        
       
        
        new SizedBlock(1,Items.SIZED_BLOCK1,Items.recipe_TEST_ITEM).register(this);
        new SizedBlock(2,Items.SIZED_BLOCK2,Items.recipe_TEST_ITEM).register(this);
        new SizedBlock(3,Items.SIZED_BLOCK3,Items.recipe_TEST_ITEM).register(this);
        new SizedBlock(4,Items.SIZED_BLOCK4,Items.recipe_TEST_ITEM).register(this);
        
        
        
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

}
