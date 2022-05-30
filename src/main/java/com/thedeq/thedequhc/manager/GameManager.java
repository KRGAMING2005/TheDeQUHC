package com.thedeq.thedequhc.manager;

import com.thedeq.thedequhc.ChatUtill;
import com.thedeq.thedequhc.TheDeQUHC;
import com.thedeq.thedequhc.modes.GameState;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GameManager {

    public GameState gameState;
    public TheDeQUHC plugin = TheDeQUHC.getPlugin(TheDeQUHC.class);

    private ArrayList<Player> spectators = new ArrayList<Player>();
    private ArrayList<Player> alive = new ArrayList<Player>();

    public void setGameState(GameState gameState) {
        if (this.gameState == gameState) {
            return;
        }

        switch (gameState) {
            case LOBBY:
                System.out.println("Lobby");
                this.gameState = gameState;
                setupGameWorld();
                //TODO: Pre generate?
                break;
            case STARTING:
                System.out.println("Starting");
                this.gameState = gameState;
                teleportPlayers(1000 / 2, Math.subtractExact(1000 / 2, 1000));
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getWorld().getName().equalsIgnoreCase("GAMEWORLD") && !spectators.contains(player)) {
                        addAlivePlayer(player);
                        player.setHealth(player.getMaxHealth());
                        player.setBedSpawnLocation(new Location(Bukkit.getWorld("GAMEWORLD"), 0, 100, 0), true);
                    }
                }
                new BukkitRunnable() {
                    int i = 21;
                    @Override
                    public void run() {
                        i--;
                        if (i > 0) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fStartar om &7" + i));
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                            }
                        }
                        if (i == 0) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fStartar nu"));
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 0);
                            }
                            setGameState(GameState.GRACE_PERIOD);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 20L);
                break;
            case GRACE_PERIOD:
                System.out.println("Grace Period");
                this.gameState = gameState;
                new BukkitRunnable() {
                    int i = 601;
                    @Override
                    public void run() {
                        i--;
                        if (i == 600) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras om &710 &fminut(er)"));
                        }
                        if (i == 300) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras om &75 &fminut(er)"));
                        }
                        if (i == 60) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras om &71 &fminut(er)"));
                        }
                        if (i == 30) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras om &730 &fsekunder"));
                        }
                        if (i == 20) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras om &720 &fsekunder"));
                        }
                        if (i == 10) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras om &710 &fsekunder"));
                        }
                        if (i < 5) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras om &7" + i + " &fsekund(er)"));
                        }
                        if (i == 0) {
                            setGameState(GameState.PVP);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 20L/* * 60*/);
                break;
            case PVP:
                System.out.println("PVP");
                this.gameState = gameState;
                Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fPVP aktiveras nu"));
                new BukkitRunnable() {
                    int i = 2401;
                    @Override
                    public void run() {
                        i--;
                        if (i == 2400) {
                            // 40 minuter
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &740 &fminuter"));
                        }
                        if (i == 1800) {
                            // 30 minuter
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &730 &fminuter"));
                        }
                        if (i == 1200) {
                            // 20 minuter
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &720 &fminuter"));
                        }
                        if (i == 600) {
                            // 10 minuter
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &710 &fminuter"));
                        }
                        if (i == 300) {
                            // 5 minuter
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &75 &fminuter"));
                        }
                        if (i == 120) {
                            // 2 minuter
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &72 &fminuter"));
                        }
                        if (i == 60) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &71 &fminuter"));
                        }
                        if (i == 30) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &7" + i + " &fsekunder"));
                        }
                        if (i == 10) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &7" + i + " &fsekunder"));
                        }else if (i < 5 && i > 0) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch om &7" + i + " &fsekunder"));
                        }else if (i == 0) {
                            Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fDeathmatch aktiveras nu"));
                            setGameState(GameState.DEATHMATCH);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin, 0L, 20L);
                break;
            case DEATHMATCH:
                System.out.println("Deathmatch");
                this.gameState = gameState;
                teleportPlayers(50 / 2, Math.subtractExact(50 / 2, 50));
                World world = Bukkit.getWorld("GAMEWORLD");
                WorldBorder worldBorder = world.getWorldBorder();
                worldBorder.setSize(50);
                worldBorder.setSize(11, 600);

                new BukkitRunnable() {
                    int i = 601;
                    @Override
                    public void run() {
                        i--;
                        if (i == 0) {
                            setGameState(GameState.SUDDEN_DEATH);
                        }
                    }
                }.runTaskTimer(plugin, 0L, 20L);
                break;
            case SUDDEN_DEATH:
                //TODO: Start limit penalties.
                break;
            case WINNER:
                System.out.println("Winner");
                this.gameState = gameState;
                Bukkit.broadcastMessage(ChatUtill.format("&2The&aDeQ &2UHC &7>> &fVinnaren är &7" + plugin.gameManager.alive.get(0).getName()));
                new BukkitRunnable() {
                    int i = 11;
                    @Override
                    public void run() {
                        i--;
                        if (i == 0) {
                            setGameState(GameState.ENDING);
                        }
                    }
                }.runTaskTimer(plugin, 0L, 20L);
                break;
            case ENDING:
                System.out.println("Ending");
                this.gameState = gameState;
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stop");
                break;
        }
    }

    public GameState getGameState() {
        if (gameState == null) {
            setGameState(GameState.LOBBY);
        }
        return gameState;
    }

    public ArrayList<Player> getSpectators() {
        return spectators;
    }

    public void addSpectator(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        spectators.add(player);
        removeAlivePlayer(player);
    }

    public void removeSpectator(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        spectators.remove(player);
        addAlivePlayer(player);
    }

    public ArrayList<Player> getAlive() {
        return alive;
    }

    public void addAlivePlayer(Player player) {
        alive.add(player);
    }

    public void removeAlivePlayer(Player player) {
        alive.remove(player);
    }

    public void setupGameWorld() {
        removeWorld();
        createWorld();
    }
    public void createWorld() {
        WorldCreator worldCreator = new WorldCreator("GAMEWORLD");
        World world = worldCreator.createWorld();
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(0, 0);
        worldBorder.setSize(1000);
        worldBorder.setDamageAmount(2);
        worldBorder.setDamageBuffer(0);
    }
    public void removeWorld() {
        File worldFolder = new File(Bukkit.getWorldContainer().getParentFile(), "GAMEWORLD");
        Bukkit.unloadWorld("GAMEWORLD", false);
        delete(worldFolder);
    }
    public void delete(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) return;
            for (File child : files) {
                delete(child);
            }
        }
        file.delete();
    }

    public void teleportPlayers(int max, int min) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!spectators.contains(player)) {
                player.teleport(findSafeLocation(max, min));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 1, true, true));
            }
        }
    }

    public Location findSafeLocation(int max, int min) {

        Location randomLocation = generateLocation(max, min);

        while (!isLocationSafe(randomLocation)) {
            randomLocation = generateLocation(max, min);
        }
        return randomLocation;
    }

    public Location generateLocation(int max, int min) {
        World world = Bukkit.getWorld("GAMEWORLD");

        int x = 0;
        int z = 0;
        int y = 0;

        x = (int) (Math.random() * Math.subtractExact(max, min) + 1) + min;
        z = (int) (Math.random() * Math.subtractExact(max, min) + 1) + min;
        y = 319;

        Location randomLocation = new Location(world, x, y, z);
        y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

        return randomLocation;
    }

    public boolean isLocationSafe(Location location) {

        ArrayList<Material> bad_blocks = new ArrayList<>();
        bad_blocks.add(Material.WATER);
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.MAGMA_BLOCK);
        bad_blocks.add(Material.CACTUS);

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //Get instances of the blocks around where the player would spawn
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        //Check to see if the surroundings are safe or not
        return !(bad_blocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }

    public void sendMessageToDiscord(String name) {
        String url = "";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
