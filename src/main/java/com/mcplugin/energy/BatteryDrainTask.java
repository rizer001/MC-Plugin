package com.mcplugin.energy;

import com.mcplugin.Main;

import com.mcplugin.cable.CableNetwork;
import com.mcplugin.cable.CableNode;
import com.mcplugin.cable.NodeType;

import com.mcplugin.util.LocationUtil;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class BatteryDrainTask extends BukkitRunnable {

    @Override
    public void run() {

        FileConfiguration cfg =
                Main.getInstance().getConfig();

        // =========================
        // ENABLE CHECK
        // =========================
        if (!cfg.getBoolean(
                "energy.battery_drain.enabled",
                true
        )) {
            return;
        }

        int transferAmount =
                cfg.getInt(
                        "energy.battery_drain.transfer_per_tick",
                        5
                );

        boolean log =
                cfg.getBoolean(
                        "energy.battery_drain.log",
                        false
                );

        // =========================
        // SAFE COPY
        // =========================
        Set<CableNode> nodes =
                new HashSet<>(
                        CableNetwork.getAllNodes()
                );

        // =========================
        // BATTERY LOOP
        // =========================
        for (CableNode battery : nodes) {

            if (battery == null) {
                continue;
            }

            // =========================
            // ONLY BATTERIES
            // =========================
            if (battery.getType() != NodeType.BATTERY) {
                continue;
            }

            Location batteryLoc =
                    LocationUtil.normalize(
                            battery.getLocation()
                    );

            // =========================
            // CONNECTIONS
            // =========================
            for (Location fromLoc : battery.getConnections()) {

                if (fromLoc == null) {
                    continue;
                }

                Location normalizedFrom =
                        LocationUtil.normalize(fromLoc);

                CableNode from =
                        CableNetwork.getNode(normalizedFrom);

                if (from == null) {
                    continue;
                }

                // =========================
                // PREVENT BATTERY ↔ BATTERY
                // =========================
                if (from.getType() == NodeType.BATTERY) {
                    continue;
                }

                // =========================
                // REAL CONNECTION CHECK
                // =========================
                if (!LocationUtil.isFullyConnected(
                        normalizedFrom,
                        batteryLoc
                )) {
                    continue;
                }

                // =========================
                // DOUBLE LINK CHECK
                // =========================
                if (!from.isConnected(batteryLoc)
                        || !battery.isConnected(normalizedFrom)) {

                    continue;
                }

                int available =
                        from.getEnergy();

                if (available <= 0) {
                    continue;
                }

                int transfer =
                        Math.min(
                                transferAmount,
                                available
                        );

                if (transfer <= 0) {
                    continue;
                }

                // =========================
                // ENERGY TRANSFER
                // =========================
                from.removeEnergy(transfer);

                battery.addEnergy(transfer);

                // =========================
                // SQLITE SAVE
                // =========================
                CableNetwork.saveNode(from);

                CableNetwork.saveNode(battery);

                // =========================
                // LOG
                // =========================
                if (log) {

                    Main.getInstance()
                            .getLogger()
                            .info(
                                    "[BatteryDrain] "
                                            + transfer
                                            + " energy moved from "
                                            + normalizedFrom
                                            + " to battery "
                                            + batteryLoc
                            );
                }
            }
        }
    }
}