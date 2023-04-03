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
	public static final SlimefunItemStack SIZED_BLOCK1 = new SlimefunItemStack("SIZED_BLOCK1",
	        Material.DIAMOND_BLOCK,
	        "SIZED_BLOCK1",
	        "&l§4Put to Block Assigner"
	    );
	public static final SlimefunItemStack SIZED_BLOCK2 = new SlimefunItemStack("SIZED_BLOCK2",
	        Material.DIAMOND_BLOCK,
	        "SIZED_BLOCK2",
	        "&l§4Put to Block Assigner"
	    );
	public static final SlimefunItemStack SIZED_BLOCK3 = new SlimefunItemStack("SIZED_BLOCK3",
	        Material.DIAMOND_BLOCK,
	        "SIZED_BLOCK3",
	        "&l§4Put to Block Assigner"
	    );
	public static final SlimefunItemStack SIZED_BLOCK4 = new SlimefunItemStack("SIZED_BLOCK4",
	        Material.DIAMOND_BLOCK,
	        "SIZED_BLOCK4",
	        "&l§4Put to Block Assigner"
	    );
	public static final SlimefunItemStack TELEPORT = new SlimefunItemStack("TELEPORT",
	        Material.GOLD_BLOCK,
	        "TELEPORT",
	        ""
	    );
	public static final SlimefunItemStack BLOCK_ASSIGNER = new SlimefunItemStack("BLOCK_ASSIGNER",
	        Material.DIAMOND_BLOCK,
	        "BLOCK_ASSIGNER",
	        "",
	        LoreBuilder.machine(MachineTier.AVERAGE, MachineType.MACHINE),
	        LoreBuilder.powerPerSecond(12),
	        LoreBuilder.powerBuffer(512)
	    );
	public static final ItemStack[] recipe_TEST_ITEM= {
			null,null,null,
			null,new ItemStack(Material.PINK_WOOL),null,
			null,null,null
	};
}
