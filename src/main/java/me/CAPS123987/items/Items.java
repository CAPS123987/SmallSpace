package me.CAPS123987.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.CAPS123987.smallspace.*;

public class Items {
	public static final ItemGroup smallSpace = new ItemGroup(new NamespacedKey(SmallSpace.getInstance(),
	        "Small_Space"),
	        new CustomItemStack(Material.TUFF, "&aSmall Space")
	    );
	public static final SlimefunItemStack TEST_ITEM = new SlimefunItemStack("TEST_ITEM",
	        Material.DIAMOND_BLOCK,
	        "TEST_ITEM",
	        ""
	    );
	public static final ItemStack[] recipe_TEST_ITEM= {
			null,null,null,
			null,new ItemStack(Material.PINK_WOOL),null,
			null,null,null
	};
}
