package com.thedeq.thedequhc;

import org.bukkit.ChatColor;

public class ChatUtill {

    public static String format(String str) {
        TheDeQUHC plugin = TheDeQUHC.getPlugin(TheDeQUHC.class);
        return ChatColor.translateAlternateColorCodes('&', str.replace("%prefix%", plugin.getConfig().getString("prefix")));
    }
}
