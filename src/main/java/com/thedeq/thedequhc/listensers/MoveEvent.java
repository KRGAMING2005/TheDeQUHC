package com.thedeq.thedequhc.listensers;

import com.thedeq.thedequhc.TheDeQUHC;
import com.thedeq.thedequhc.modes.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    TheDeQUHC plugin = TheDeQUHC.getPlugin(TheDeQUHC.class);

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (plugin.gameManager.getGameState() == GameState.STARTING) {
            event.setTo(event.getFrom());
        }
    }

}
