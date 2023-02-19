package de.artus.greenduckevent.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -351, 102, -154));
        e.getPlayer().setGameMode(GameMode.ADVENTURE);
        e.getPlayer().getInventory().clear();
        e.setJoinMessage(ChatColor.GREEN + e.getPlayer().getName() + " ist dem Server beigetreten!");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(ChatColor.RED + e.getPlayer().getName() + " hat den Server verlassen!");
    }
}
