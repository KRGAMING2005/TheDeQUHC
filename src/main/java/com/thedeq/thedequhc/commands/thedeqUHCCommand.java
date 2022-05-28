package com.thedeq.thedequhc.commands;

import com.thedeq.thedequhc.ChatUtill;
import com.thedeq.thedequhc.TheDeQUHC;
import com.thedeq.thedequhc.modes.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class thedeqUHCCommand implements CommandExecutor, TabCompleter {
    TheDeQUHC plugin = TheDeQUHC.getPlugin(TheDeQUHC.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(ChatUtill.format(plugin.getConfig().getString("help")));
            }else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(ChatUtill.format(plugin.getConfig().getString("help")));
                }else if (args[0].equalsIgnoreCase("start")) {
                    plugin.gameManager.setGameState(GameState.STARTING);
                    sender.sendMessage("STARTING");
                }else if (args[0].equalsIgnoreCase("event")) {

                }else if (args[0].equalsIgnoreCase("stop")) {
                    plugin.gameManager.setGameState(GameState.LOBBY);
                    sender.sendMessage("EMERGENCY RESET");
                }else if (args[0].equalsIgnoreCase("gamestate")) {
                    if (args[1].equalsIgnoreCase("LOBBY")) {
                        plugin.gameManager.setGameState(GameState.LOBBY);
                    }
                    if (args[1].equalsIgnoreCase("STARTING")) {
                        plugin.gameManager.setGameState(GameState.STARTING);
                    }
                    if (args[1].equalsIgnoreCase("GRACE_PERIOD")) {
                        plugin.gameManager.setGameState(GameState.GRACE_PERIOD);
                    }
                    if (args[1].equalsIgnoreCase("PVP")) {
                        plugin.gameManager.setGameState(GameState.PVP);
                    }
                    if (args[1].equalsIgnoreCase("DEATHMATCH")) {
                        plugin.gameManager.setGameState(GameState.DEATHMATCH);
                    }
                    if (args[1].equalsIgnoreCase("SUDDEN_DEATH")) {
                        plugin.gameManager.setGameState(GameState.SUDDEN_DEATH);
                    }
                    if (args[1].equalsIgnoreCase("WINNER")) {
                        plugin.gameManager.setGameState(GameState.WINNER);
                    }
                    if (args[1].equalsIgnoreCase("ENDING")) {
                        plugin.gameManager.setGameState(GameState.ENDING);
                    }
                }
            }
        }else {
            sender.sendMessage(ChatUtill.format(plugin.getConfig().getString("playerOnly")));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("lobby");
        list.add("starting");
        list.add("grace_period");
        list.add("pvp");
        list.add("deathmatch");
        list.add("sudden_death");
        list.add("winner");
        list.add("ending");
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("gamestate")) {
                return list;
            }
        }
        return null;
    }
}
