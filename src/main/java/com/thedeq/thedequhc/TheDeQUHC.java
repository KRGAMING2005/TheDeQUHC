package com.thedeq.thedequhc;

import com.thedeq.thedequhc.commands.thedeqUHCCommand;
import com.thedeq.thedequhc.listensers.DamageEvent;
import com.thedeq.thedequhc.listensers.DeathEvent;
import com.thedeq.thedequhc.listensers.JoinEvent;
import com.thedeq.thedequhc.listensers.MoveEvent;
import com.thedeq.thedequhc.manager.GameManager;
import com.thedeq.thedequhc.modes.GameState;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheDeQUHC extends JavaPlugin {
    public GameManager gameManager;
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.gameManager = new GameManager();
        this.gameManager.setGameState(GameState.LOBBY);

        getCommand("theuhc").setExecutor(new thedeqUHCCommand());
        getCommand("theuhc").setTabCompleter(new thedeqUHCCommand());

        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
    }
    @Override
    public void onDisable() {
        Bukkit.unloadWorld("GAMEWORLD", false);
    }
}
