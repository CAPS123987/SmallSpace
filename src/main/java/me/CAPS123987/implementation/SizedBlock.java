package me.CAPS123987.implementation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.api.events.BlockPlacerPlaceEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.EnhancedCraftingTable;
import me.CAPS123987.dimension.SmallSpaceDim;
import me.CAPS123987.items.Items;
import me.CAPS123987.smallspace.Calculator;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.hover.content.Item;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import org.bukkit.event.player.PlayerEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;

public class SizedBlock extends SimpleSlimefunItem<BlockTicker> implements EnergyNetComponent{
	private final int Tier;
	public SizedBlock(int i, SlimefunItemStack item, ItemStack[] recipe) {
		super(Items.smallSpace, item, RecipeType.ENHANCED_CRAFTING_TABLE,
				recipe);
		addItemHandler(BlockPlaceHandler());
		addItemHandler(BlockBreakHandler());
		addItemHandler(BlockUseHandler());
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
	
	
	public BlockUseHandler BlockUseHandler() {
		return new BlockUseHandler() {

			@Override
			public void onRightClick(PlayerRightClickEvent e) {
				
				
				Location loc = Calculator.getLoc(BlockStorage.getLocationInfo(e.getClickedBlock().get().getLocation(), "name"));
				String srt = BlockStorage.getLocationInfo(e.getClickedBlock().get().getLocation(), "Tier");
				int value = Integer.parseInt(srt.replaceAll("[^0-9]", ""));
				World world = Bukkit.getWorld("SmallSpace");
				Location newloc = new Location(world,loc.getX()+0.5,loc.getY(),loc.getZ()+0.5);
				Location newloc2 = new Location(world,loc.getX()+0.5,loc.getY()+1,loc.getZ()+0.5);
				Location newloc3 = new Location(world,loc.getX()+0.5,loc.getY()-1,loc.getZ()+0.5);
				
				if(e.getPlayer().isSneaking()) {
					if(loc.getBlock().getType() == Material.BEDROCK) {
						e.getPlayer().sendMessage(ChatColor.RED+"Your space was never initialized try right click");
						return;
					}
					if(loc.getBlock().getType() == Material.BARRIER) {
						e.getPlayer().sendMessage(ChatColor.DARK_RED+"Sorry your space is disabled");
						return;
					}
					
					newloc2.getBlock().setType(Material.AIR);
					e.getPlayer().sendMessage("Blocks were removed");
				}else {
					if(loc.getBlock().getType() == Material.BEDROCK) {
						switch(value) {
						case 1:
							Calculator.setRegi(loc,3.0);
							break;
						case 2:
							Calculator.setRegi(loc,6.0);
							break;
						case 3:
							Calculator.setRegi(loc,9.0);
							break;
						case 4:
							Calculator.setRegi(loc,12.0);
							break;
						default:
							break;
						}
						world.getBlockAt(newloc3).setType(Material.GOLD_BLOCK);
						
						BlockStorage.store(newloc3.getBlock(), "TELEPORT");
						
						BlockStorage.addBlockInfo(newloc3.getBlock(), "tpX",String.valueOf(e.getClickedBlock().get().getLocation().getX()));
						BlockStorage.addBlockInfo(newloc3.getBlock(), "tpY", String.valueOf(e.getClickedBlock().get().getLocation().getY()));
						BlockStorage.addBlockInfo(newloc3.getBlock(), "tpZ", String.valueOf(e.getClickedBlock().get().getLocation().getZ()));
						BlockStorage.addBlockInfo(newloc3.getBlock(), "world", String.valueOf(e.getClickedBlock().get().getLocation().getWorld().getName()));
						
					}
					
					if(newloc.getBlock().getType()==Material.BARRIER) {
						e.getPlayer().sendMessage(ChatColor.DARK_RED+"Sorry your space is disabled");
						return;
					}
					if(newloc2.getBlock().getType()==Material.AIR) {
						e.getPlayer().teleport(newloc);
					}else {
						e.getPlayer().sendMessage(ChatColor.RED+"Spawn Location in your space is obstructed! To remove Blocks press Shift and right click this again");
						return;
					}
					
				}
			}

			
		};
	}

	
	public BlockBreakHandler BlockBreakHandler() {
		return new BlockBreakHandler(false, false) {

			@Override
			public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
				Block b = e.getBlock();
				String name = BlockStorage.getLocationInfo(b.getLocation(), "name");
				if(name == "null") return;
				ItemStack item2 =SizedBlock.this.getItem().clone();
				e.setDropItems(false);
				
				List<String> lore = new ArrayList<String>();
				lore.add(name);
				ItemMeta im=item2.getItemMeta();
				im.setLore(lore);
				item2.setItemMeta(im);
				
				e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item2);
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	
	public BlockPlaceHandler BlockPlaceHandler() {
		return new BlockPlaceHandler(false) {

			@Override
			public void onPlayerPlace(BlockPlaceEvent e) {
				// TODO Auto-generated method stub
				BlockStorage.addBlockInfo(e.getBlock().getLocation(), "Tier", String.valueOf(getTier())) ;
				if(e.getItemInHand().getItemMeta().getLore().get(0).equals("§4Put to Block Assigner")) {
					BlockStorage.addBlockInfo(e.getBlock().getLocation(), "name","null");
					return;
					}
					BlockStorage.addBlockInfo(e.getBlock().getLocation(), "name", e.getItemInHand().getItemMeta().getLore().get(0)) ;
				}
			};
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
