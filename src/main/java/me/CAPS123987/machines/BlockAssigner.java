package me.CAPS123987.machines;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.CAPS123987.implementation.SizedBlock;
import me.CAPS123987.items.Items;
import me.CAPS123987.smallspace.*;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class BlockAssigner extends SimpleSlimefunItem<BlockTicker> implements ETInventoryBlock,
EnergyNetComponent{
	private static final int ENERGY_CONSUMPTION = 512;
	private final int[] border = {0, 1, 2, 3, 5, 4, 6, 7, 8, 9, 18, 27,
	        31, 36, 37, 38, 39, 40, 41, 42, 43, 44, 22};
	private final int[] inputBorder = {19,21,10,11,12,28,29,30};
    private final int[] outputBorder = {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    private static final int statusSlot = 22;
    private static final int bar = 13;
    FileConfiguration cfg = SmallSpace.instance.getConfig();
    //Config cfg = new Config();
    
    
	public BlockAssigner() {
		super(Items.smallSpace, Items.BLOCK_ASSIGNER, RecipeType.ENHANCED_CRAFTING_TABLE, Items.recipe_TEST_ITEM);
		
		createPreset(this, this::constructMenu);
		addItemHandler(onBreak());
		// TODO Auto-generated constructor stub
	}
	
	
	
	public BlockTicker getItemHandler() {
        return new BlockTicker() {
        	public void tick(Block b, SlimefunItem sf, Config data) {
        		BlockMenu menu = BlockStorage.getInventory(b);
        		SlimefunItem sfitem;
        		try {
        			sfitem = SlimefunItem.getByItem(menu.getItemInSlot(20));
        		}catch(Exception e) {
        			return;
        		}
        		if(sfitem instanceof SizedBlock) {}else {return;}
        		if(1<menu.getItemInSlot(20).getAmount()) {return;}
        		
        		ItemMeta meta = menu.getItemInSlot(20).getItemMeta();
        		List<String> lore = new ArrayList<String>();
        		if(!meta.getLore().get(0).contains("Put to Block Assigner")) {return;}
        		
        		long max = cfg.getLong("max");
        		lore.add(String.valueOf(max));
        		cfg.set("max", max+1);
        		SmallSpace.instance.saveConfig();
        		
        		
        		meta.setLore(lore);
        		menu.getItemInSlot(20).setItemMeta(meta);
        	}

			@Override
			public boolean isSynchronized() {
				// TODO Auto-generated method stub
				return true;
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
        preset.addItem(bar, new CustomItemStack(new ItemStack(Material.NAME_TAG), " "),
                ChestMenuUtils.getEmptyClickHandler());
        //preset.addItem(statusSlot, new CustomItemStack(new ItemStack(Material.GUNPOWDER), " "),
        		//ChestMenuUtils.getEmptyClickHandler());
        
      
        

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
        /*for (int i : getInputSlots()) {
        	preset.addItem(i, new CustomItemStack(new ItemStack(Material.AIR)));
        }*/
    }
	
    @Override
    public int getCapacity() {
        return 512;
    }
    @Override
    public int[] getInputSlots() {
        return new int[] {20};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] {24, 25};
    }
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
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

}
