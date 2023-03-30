package me.CAPS123987.dimension;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;
import org.bukkit.generator.ChunkGenerator.ChunkData;

public class SmallSpaceDim extends ChunkGenerator{
	World newWorld;// = Bukkit.createWorld(new WorldCreator("SmallSpace"));
	public void createWorld(String srt) {
		newWorld = Bukkit.createWorld(new WorldCreator(srt));
		//Bukkit.broadcastMessage(newWorld.toString()+"hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	}
	public World getWorld() {
		return newWorld;
	}
	
	@Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        chunk.setRegion(0, -64, 0, 16, -63, 16, Material.BEDROCK);
       	chunk.setRegion(0, 318, 0, 16, 319, 16, Material.BEDROCK);
        chunk.setRegion(0, -63, 0, 16, 318, 16, Material.STONE);

       	
        
        return chunk;
    }
	
}
