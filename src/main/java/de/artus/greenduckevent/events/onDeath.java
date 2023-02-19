package de.artus.greenduckevent.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeath implements Listener {


    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        String deathMsg = e.getDeathMessage();
        deathMsg = ChatColor.RED + deathMsg;
        for (Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(deathMsg);
        }

        e.getPlayer().setGameMode(GameMode.SPECTATOR);
        e.setCancelled(true);
    }

}
