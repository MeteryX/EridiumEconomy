package at.mtxframe.eridiumeconomy.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.HashSet;
import java.util.Set;

public class ErzAderDetector {
    private Set<Location> visitedBlocks = new HashSet<>();
    private Set<Location> oreAderBlocks = new HashSet<>();
    private World world;

    public ErzAderDetector(World world) {
        this.world = world;
    }

    public Set<Location> findOreAder(Block block) {
        visitedBlocks.clear();
        oreAderBlocks.clear();
        detectOreAder(block.getLocation(), block.getType());
        return oreAderBlocks;
    }

    private void detectOreAder(Location location, Material oreType) {
        if (!visitedBlocks.contains(location)) {
            visitedBlocks.add(location);
            Block block = location.getBlock();

            if (block.getType() == oreType) {
                oreAderBlocks.add(location);

                for (BlockFace face : BlockFace.values()) {
                    detectOreAder(location.clone().add(face.getModX(), face.getModY(), face.getModZ()), oreType);
                }
            }
        }
    }
}
