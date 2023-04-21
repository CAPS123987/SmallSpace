package me.CAPS123987.implementation;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.CAPS123987.items.Items;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class Teleporterss extends SlimefunItem{

	public Teleporterss() {
		
		super(Items.smallSpace, Items.TELEPORT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[0]);
		// TODO Auto-generated constructor stub
		this.setHidden(true);
		addItemHandler(BlockUseHandler());
		addItemHandler(BlockBreakHandler());
	}

	public ItemHandler getItemHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public BlockUseHandler BlockUseHandler() {
		return new BlockUseHandler() {

			@Override
			public void onRightClick(PlayerRightClickEvent e) {
				// TODO Auto-generated method stub
				Location loc = e.getClickedBlock().get().getLocation();
				Player p = e.getPlayer();
				String x = BlockStorage.getLocationInfo(loc, "tpX");
				String y = BlockStorage.getLocationInfo(loc, "tpY");
				String z = BlockStorage.getLocationInfo(loc, "tpZ");
				World w =  Bukkit.getWorld(BlockStorage.getLocationInfo(loc,"world"));
				double X =	Double.valueOf(x)+0.5;
				double Y =	Double.valueOf(y);
				double Z =	Double.valueOf(z)+0.5;
				Location tp = new Location(w,X,Y,Z);
				p.teleport(tp);
				
			}
			
		};
		
	}
	public BlockBreakHandler BlockBreakHandler() {
		return new BlockBreakHandler(false, false) {

			@Override
			public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
				e.setCancelled(true);
				
			}
		};
	}

}
