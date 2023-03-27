package me.CAPS123987.smallspace;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
import me.CAPS123987.items.Items;

public class SmallSpace extends JavaPlugin implements SlimefunAddon {
	public static SmallSpace instance;
    @Override
    public void onEnable() {
        // Read something from your config.yml
    	instance = this;
    	
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // You could start an Auto-Updater for example
        }
        SlimefunItem sfItem = new SlimefunItem(Items.smallSpace, Items.TEST_ITEM, 
        		RecipeType.ENHANCED_CRAFTING_TABLE, Items.recipe_TEST_ITEM);
        sfItem.register(this);
        
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

}
