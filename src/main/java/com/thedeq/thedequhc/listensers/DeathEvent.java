package com.thedeq.thedequhc.listensers;

import com.thedeq.thedequhc.ChatUtill;
import com.thedeq.thedequhc.TheDeQUHC;
import com.thedeq.thedequhc.modes.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    TheDeQUHC plugin = TheDeQUHC.getPlugin(TheDeQUHC.class);

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        Player killer = player.getKiller();

        if (event.getEntity().getKiller() != null) {
            if (event.getDeathMessage().contains("was shot by")) {
                event.setDeathMessage(ChatUtill.format("&c" + player.getName() + "&6" + " fick en pil i foten av " + "&c" + player.getKiller().getName()));
            } else if (event.getDeathMessage().contains("was slain by")) {
                event.setDeathMessage(ChatUtill.format("&c" + player.getName() + "&6" + " dog av en " + "&c" + player.getKiller().getName()));
            }
        }

        if (event.getDeathMessage().contains("was poked to death by a sweet berry bush")) {
            event.setDeathMessage(ChatUtill.format("&c" + player.getName() + "&6" + " trampade på lego"));
        } else if (event.getDeathMessage().contains("fell")) {
            event.setDeathMessage(ChatUtill.format("&c" + player.getName() + "&6" + " bröt sin fot och dog"));
        } else if (event.getDeathMessage().contains("went up in flames")) {
            event.setDeathMessage(ChatUtill.format("&c" + player.getName() + "&6" + " blev till aska"));
        } else if (event.getDeathMessage().contains("tried to swim in lava")) {
            event.setDeathMessage(ChatUtill.format("&c" + player.getName() + "&6" + " fick varma fötter"));
        } else if (event.getDeathMessage().contains("was shot by")) {
            event.setDeathMessage(ChatUtill.format("&c" + player.getName() + " &6fick en pil i foten"));
        } else if (event.getDeathMessage().contains("was slain by")) {
            event.setDeathMessage(ChatUtill.format("&c" + player.getName() + " &6dog"));
        }
        for (Player players : Bukkit.getOnlinePlayers()) {
            players.playSound(players.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 0);
        }
        plugin.gameManager.addSpectator(player);

        System.out.println("" + plugin.gameManager.getAlive().size());
        if (plugin.gameManager.getAlive().size() == 1) {
            plugin.gameManager.setGameState(GameState.WINNER);
        }

    }

}