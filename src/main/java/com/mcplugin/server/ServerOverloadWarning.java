package com.mcplugin;

import com.mcplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerOverloadWarning extends BukkitRunnable {

    // =========================
    // ANTI SPAM
    // =========================
    private boolean warnedHigh = false;
    private boolean warnedCritical = false;

    @Override
    public void run() {

        double mspt = Bukkit.getServer().getAverageTickTime();

        // =========================
        // ☠ CRITICAL LOAD
        // 50+ MSPT
        // =========================
        if (mspt >= 50.0) {

            // сбрасываем high
            warnedHigh = false;

            if (!warnedCritical) {

                warnedCritical = true;

                String msg =
                        ChatColor.DARK_RED +
                                "☠ CRITICAL SERVER LOAD! " +
                                ChatColor.GRAY +
                                "(MSPT: " +
                                ChatColor.WHITE +
                                String.format("%.2f", mspt) +
                                ChatColor.GRAY +
                                ")";

                // console
                Main.getInstance()
                        .getLogger()
                        .severe(
                                "[PERFORMANCE] CRITICAL MSPT: " + mspt
                        );

                // players
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(msg);
                }
            }

            return;
        }

        // =========================
        // ⚠ HIGH LOAD
        // 40+ MSPT
        // =========================
        if (mspt >= 40.0) {

            // сбрасываем critical
            warnedCritical = false;

            if (!warnedHigh) {

                warnedHigh = true;

                String msg =
                        ChatColor.RED +
                                "⚠ HIGH SERVER LOAD! " +
                                ChatColor.GRAY +
                                "(MSPT: " +
                                ChatColor.WHITE +
                                String.format("%.2f", mspt) +
                                ChatColor.GRAY +
                                ")";

                // console
                Main.getInstance()
                        .getLogger()
                        .warning(
                                "[PERFORMANCE] HIGH MSPT: " + mspt
                        );

                // players
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(msg);
                }
            }

            return;
        }

        // =========================
        // NORMAL
        // =========================
        warnedHigh = false;
        warnedCritical = false;
    }
}