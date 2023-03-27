package me.CAPS123987.implementation;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.CAPS123987.items.Items;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;

public class SizedBlock extends SimpleSlimefunItem<BlockTicker> implements EnergyNetComponent{
	private final int Tier;
	public SizedBlock(int i, SlimefunItemStack item, ItemStack[] recipe) {
		super(Items.smallSpace, item, RecipeType.ENHANCED_CRAFTING_TABLE,
				recipe);
		this.Tier=i;
	}

	@Override
	public EnergyNetComponentType getEnergyComponentType() {
		// TODO Auto-generated method stub
		return EnergyNetComponentType.CONSUMER;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 2048;
	}

	@Override
	public BlockTicker getItemHandler() {
		return new BlockTicker() {
	    	
	        
	        public void tick(Block b, SlimefunItem sf, Config data) {
	        	if(getTier()*512<=getCharge(b.getLocation())) {
	        		removeCharge(b.getLocation(), getTier()*512);
	        	}
	        }

			@Override
			public boolean isSynchronized() {
				// TODO Auto-generated method stub
				return true;
			}
		};
	}
	
	public int getTier() {
		return Tier;
	}
}
