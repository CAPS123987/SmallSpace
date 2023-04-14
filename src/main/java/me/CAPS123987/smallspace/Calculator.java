package me.CAPS123987.smallspace;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class Calculator {
	public static Location getLoc(String id) {
		long x = -29999983;
		long y = -63;
		long z = -29999983;
		long value = Integer.parseInt(id.replaceAll("[^0-9]", ""));
		long tempcalc = value*24;
		
		y = tempcalc-62;
		while(y>250) {
			y = y-312;
			x = x + 32;
		}
		while(x>29999883) {
			x = x-29999883;
			z = z + 32;
		}
		while(z>29999883) {
			z = z-29999883;
			x = x + 32;
		}
		
		
		return new Location(Bukkit.getWorld("SmallSpace"),x,y,z);
	}
	public static void setRegi(Location l,double area) {
		
		World world = Bukkit.getWorld("SmallSpace");
		world.getBlockAt(l).setType(Material.AIR);
		world.loadChunk(l.getChunk());
		
		double oldx = l.getX();
		double oldy = l.getY();
		double oldz = l.getZ();
		
		for(double x = 0.0; x!=area; x=x+1.0) {
			for(double y = 0.0; y!=area; y=y+1.0) {
				for(double z = 0.0; z!=area; z=z+1.0) {
					
					Location loc = new Location(world,x+oldx,y+oldy,z+oldz);
					world.getBlockAt(loc).setType(Material.AIR);
				}
			}
		}
		
	}
	
	public static List<String> playersGet(String s) {
		List<String> options = new ArrayList<>();
		
		String text = "";
		for(int i = 0; s.length() != i; i++) {
			
			if(s.charAt(i) != ';') {
				text = text+s.charAt(i);
			}else {
				options.add(text);
				text = "";
			}
			
		}
		return options;
		
	}
}
