package com.mcplugin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class EmergencyEntitiesKill extends BukkitRunnable {

    private int overloadTicks = 0;

    private static final int MAX_OVERLOAD_TIME = 20 * 10; // 10 sec
    private static final double INSTANT_KILL_MSPT = 600.0;

    // =========================
    // NEW THRESHOLD
    // =========================
    private static final int ENTITY_LIMIT = 1000;

    @Override
    public void run() {

        double mspt = Bukkit.getServer().getAverageTickTime();

        // =========================
        // TOTAL ENTITY COUNT
        // =========================
        int totalEntities = 0;

        for (World world : Bukkit.getWorlds()) {
            totalEntities += world.getEntities().size();
        }

        boolean overloadByEntities = totalEntities >= ENTITY_LIMIT;

        // =========================
        // 🚨 INSTANT EMERGENCY
        // =========================
        if (mspt >= INSTANT_KILL_MSPT && overloadByEntities) {

            String msg =
                    "[OVERLOAD] MSPT="
                            + mspt
                            + " ENTITIES="
                            + totalEntities
                            + " will be deleted.";

            Main.getInstance().getLogger().severe(msg);

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(
                        "§7[§4OVERLOAD§7] §fMSPT §c"
                                + mspt
                                + " §fEntities §c"
                                + totalEntities
                                + "§f will be deleted."
                );
            }

            overloadTicks = 0;
            removeMostCommonEntities();
            return;
        }

        // =========================
        // NORMAL LOAD
        // =========================
        if (mspt < 60 || !overloadByEntities) {
            overloadTicks = 0;
            return;
        }

        overloadTicks += 20;

        String warningConsole =
                "[OVERLOAD] MSPT="
                        + mspt
                        + " ENTITIES="
                        + totalEntities
                        + " (" + (overloadTicks / 20) + "/10s)";

        Main.getInstance().getLogger().warning(warningConsole);

        String warningPlayers =
                "§7[§4OVERLOAD§7] §fMSPT: "
                        + mspt
                        + " §7| §fEntities: "
                        + totalEntities
                        + " §7(" + (overloadTicks / 20) + "§8/§e10s§7)";

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(warningPlayers);
        }

        // =========================
        // WAIT FULL TIME
        // =========================
        if (overloadTicks < MAX_OVERLOAD_TIME) {
            return;
        }

        overloadTicks = 0;
        removeMostCommonEntities();
    }

    // =========================
    // REMOVE MOST COMMON ENTITY
    // =========================
    private void removeMostCommonEntities() {

        Map<String, Integer> counts = new HashMap<>();

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {

                if (entity instanceof Player) continue;

                String type = entity.getType().name();
                counts.put(type, counts.getOrDefault(type, 0) + 1);
            }
        }

        if (counts.isEmpty()) {
            Main.getInstance().getLogger().severe("[OVERLOAD] No removable entities found.");
            return;
        }

        String topType = null;
        int max = 0;

        for (var entry : counts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topType = entry.getKey();
            }
        }

        if (topType == null) return;

        int removed = 0;

        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {

                if (entity instanceof Player) continue;

                if (entity.getType().name().equals(topType)) {
                    entity.remove();
                    removed++;
                }
            }
        }

        Main.getInstance().getLogger().severe(
                "[OVERLOAD] Removed " + removed + " " + topType
        );

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(
                    "§7[§4OVERLOAD§7] §fRemoved §e"
                            + removed
                            + " §fentities of type §c"
                            + topType
            );
        }
    }
}