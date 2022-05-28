package com.thedeq.thedequhc.listensers;

import com.thedeq.thedequhc.TheDeQUHC;
import com.thedeq.thedequhc.modes.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent implements Listener {

    TheDeQUHC plugin = TheDeQUHC.getPlugin(TheDeQUHC.class);

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
                if (plugin.gameManager.getGameState() == GameState.GRACE_PERIOD || plugin.gameManager.getGameState() == GameState.LOBBY) {
                    event.setCancelled(true);
                }
            }
        }
        if (plugin.gameManager.getGameState() == GameState.LOBBY) {
            event.setCancelled(true);
        }
    }

}
