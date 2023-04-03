package me.CAPS123987.tabCompleater;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabC implements TabCompleter{
	
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args){

    	if(args.length == 1) {
    			List<String> options = new ArrayList<>();
    			options.add("tptoId");
    			options.add("blockId");
    			options.add("unblockId");
    			options.add("teleportRemove");
    			return options;
    	
    	}
    	if(args.length == 2) {
			List<String> options = new ArrayList<>();
			options.add("<ID>");
			return options;
	
	}
    	return null;
   }
}