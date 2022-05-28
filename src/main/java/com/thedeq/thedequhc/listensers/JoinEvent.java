package com.thedeq.thedequhc.listensers;

import com.thedeq.thedequhc.TheDeQUHC;
import com.thedeq.thedequhc.modes.GameState;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinEvent implements Listener {

    TheDeQUHC plugin  = TheDeQUHC.getPlugin(TheDeQUHC.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.gameManager.getGameState() == GameState.LOBBY) {
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().clear();
            if (plugin.gameManager.getSpectators().contains(player)) {
                plugin.gameManager.removeSpectator(player);
            }
        } else if (plugin.gameManager.getGameState() == GameState.STARTING) {
            //TODO: Teleport player inside of border
        }else if (plugin.gameManager.getGameState() == GameState.GRACE_PERIOD) {
            plugin.gameManager.addSpectator(player);
        }else if (plugin.gameManager.getGameState() == GameState.PVP) {
            plugin.gameManager.addSpectator(player);
        }else if (plugin.gameManager.getGameState() == GameState.DEATHMATCH) {
            plugin.gameManager.addSpectator(player);
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!(plugin.gameManager.getGameState() == GameState.LOBBY) && !(plugin.gameManager.getSpectators().contains(player))) {
            player.setHealth(0);
            plugin.gameManager.addSpectator(player);
        }
    }
}
