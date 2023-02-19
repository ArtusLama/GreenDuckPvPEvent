package de.artus.greenduckevent.resetSystem;

import org.bukkit.World;

public interface GameMap {

    boolean load(String name);
    void unload();
    boolean restoreFromSource(String name);

    boolean isLoaded();
    World getWorld();
}
