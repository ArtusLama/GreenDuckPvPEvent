package de.artus.greenduckevent.commands;

import de.artus.greenduckevent.resetSystem.GameMap;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class stopCommand implements CommandExecutor {

    GameMap map;
    public stopCommand(GameMap gameMap){
        this.map = gameMap;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.isOp()){

            map.unload();
            for (Player player : Bukkit.getOnlinePlayers()){
                player.teleport(new Location(Bukkit.getWorld("world"), -351, 102, -154));
                player.getInventory().clear();
                player.setGameMode(GameMode.ADVENTURE);
            }

        }
        return false;
    }
}
