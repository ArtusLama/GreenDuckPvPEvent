package de.artus.greenduckevent.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class warpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.isOp()){
            if (args.length == 1){

                Player player = (Player) sender;
                Bukkit.createWorld(new WorldCreator("EVENTMAP_" + args[0]));
                player.teleport(new Location(Bukkit.getWorld("EVENTMAP_" + args[0]), -351, 102, -154));
                player.getInventory().clear();
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.teleport(new Location(Bukkit.getWorld("EVENTMAP_" + args[0]), -351, 102, -154));
                    p.getInventory().clear();
                    p.setGameMode(GameMode.ADVENTURE);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Bitte gebe eine Map ID an!");
            }
        }


        return false;
    }
}
