package me.CAPS123987.cargo;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.CAPS123987.items.Items;
import me.CAPS123987.smallspace.Calculator;
import me.CAPS123987.smallspace.ETInventoryBlock;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class SpaceInterface extends SimpleSlimefunItem<BlockTicker> implements ETInventoryBlock{
	public final static int[] inputs = {9,10,11,18,19,20,27,28,29};
	public final static int[] outputs = {15,16,17,24,25,26,33,34,35};
	public final int[] border = {0,1,2,3,4,5,6,7,8,13,22,31,36,37,38,39,40,41,42,43,44};
	public final int[] inputBorder = {12,21,30};
	public final int[] outputBorder = {14,23,32};
	
	public SpaceInterface() {
		super(Items.smallSpace, Items.SPACE_INTERFACE, RecipeType.ENHANCED_CRAFTING_TABLE, Items.recipe_TEST_ITEM);
		createPreset(this, this::constructMenu);
		addItemHandler(onBreak(), BlockPlaceHandler());
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[] getInputSlots() {
		// TODO Auto-generated method stub
		return inputs;
	}

	@Override
	public int[] getOutputSlots() {
		// TODO Auto-generated method stub
		return outputs;
	}

	@Override
	public BlockTicker getItemHandler() {
		// TODO Auto-generated method stub
		return new BlockTicker() {

			@Override
			public boolean isSynchronized() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void tick(Block b, SlimefunItem item, Config data) {
				// TODO Auto-generated method stub
				
			}
	
		};
	}
	public BlockPlaceHandler BlockPlaceHandler() {
		return new BlockPlaceHandler(false) {

			@Override
			public void onPlayerPlace(BlockPlaceEvent e) {
				Block b = e.getBlock();
				Player p = e.getPlayer();
				
				if(!b.getType().equals(Material.PLAYER_WALL_HEAD)) {
					BlockStorage.clearBlockInfo(b);
					e.setCancelled(true);
				}
				Directional bmeta = (Directional) b.getBlockData();
				Vector v = Calculator.fac(bmeta.getFacing().toString()) ;
				Location loc = b.getLocation().add(v);
				
				p.sendMessage(v.toString()+" "+ loc.getBlock().getType().toString());
				
				
			}
			
		};
	}
	
	
	public BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }
            }
        };
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void constructMenu(BlockMenuPreset preset) {
	    	
	    	
	        for (int i : border) {
	            preset.addItem(i, new CustomItemStack(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), " "),
	                ChestMenuUtils.getEmptyClickHandler());
	        }
	        for (int i : inputBorder) {
	            preset.addItem(i, new CustomItemStack(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), " "),
	                ChestMenuUtils.getEmptyClickHandler());
	        }
	        for (int i : outputBorder) {
	            preset.addItem(i, new CustomItemStack(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), " "),
	                ChestMenuUtils.getEmptyClickHandler());
	        }
	        
	
	        for (int i : getOutputSlots()) {
	            preset.addMenuClickHandler(i, new ChestMenu.AdvancedMenuClickHandler() {
	
	                @Override
	                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
	                    return false;
	                }
	
	                @Override
	                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor,
	                                       ClickAction action) {
	                    return cursor == null || cursor.getType() == Material.AIR;
	                }
	            });
	       }
    }
}
