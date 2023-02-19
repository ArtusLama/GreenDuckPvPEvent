package de.artus.greenduckevent.resetSystem;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LocalGameMap implements GameMap{

    private final File sourceWorldFolder;
    private File activeWorldFolder;

    private World bukkitWorld;

    public LocalGameMap(File worldFolder, String worldName, boolean loadOnInit) {
        this.sourceWorldFolder = new File(worldFolder, worldName);

        if (loadOnInit) load("");
    }

    @Override
    public boolean load(String worldName) {

        String name;
        if (Objects.equals(worldName, "")){
            name = sourceWorldFolder.getName() + "_active_" + System.currentTimeMillis();
        } else {
            name = "EVENTMAP_" + worldName;
        }

        this.activeWorldFolder = new File(Bukkit.getWorldContainer().getParentFile(), name);

        try {
            FileUtil.copy(sourceWorldFolder, activeWorldFolder);
        } catch (IOException e){
            Bukkit.getLogger().severe("Failed to reset the map!");
            e.printStackTrace();
            return false;
        }

        this.bukkitWorld = Bukkit.createWorld(new WorldCreator(activeWorldFolder.getName()));
        if (bukkitWorld != null) this.bukkitWorld.setAutoSave(true);
        return isLoaded();
    }

    @Override
    public void unload() {
        if (bukkitWorld != null) Bukkit.unloadWorld(bukkitWorld, false);
        if (activeWorldFolder != null) FileUtil.delete(activeWorldFolder);

        bukkitWorld = null;
        activeWorldFolder = null;
    }

    @Override
    public boolean restoreFromSource(String name) {
        if (Objects.equals(name, "")){
            unload();
        }
        return load(name);
    }

    @Override
    public boolean isLoaded() {
        return getWorld() != null;
    }

    @Override
    public World getWorld() {
        return this.bukkitWorld;
    }
}
