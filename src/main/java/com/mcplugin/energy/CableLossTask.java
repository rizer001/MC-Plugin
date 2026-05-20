package com.mcplugin.energy;

import com.mcplugin.Main;

import com.mcplugin.cable.CableNetwork;
import com.mcplugin.cable.CableNode;
import com.mcplugin.cable.NodeType;

import org.bukkit.Material;

import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class CableLossTask extends BukkitRunnable {

    @Override
    public void run() {

        FileConfiguration config =
                Main.getInstance().getConfig();

        // =========================
        // ENABLE CHECK
        // =========================
        if (!config.getBoolean(
                "energy.cable.enabled",
                true
        )) {
            return;
        }

        int loss =
                config.getInt(
                        "energy.cable.loss_per_tick",
                        1
                );

        boolean log =
                config.getBoolean(
                        "energy.cable.log",
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
        // ENERGY LOSS
        // =========================
        for (CableNode node : nodes) {

            if (node == null) {
                continue;
            }

            // =========================
            // SKIP BATTERIES
            // =========================
            if (node.getType() == NodeType.BATTERY) {
                continue;
            }

            Material type =
                    node.getLocation()
                            .getBlock()
                            .getType();

            int energy =
                    node.getEnergy();

            if (energy <= 0) {
                continue;
            }

            int finalLoss;

            // =========================
            // LIGHTNING ROD
            // =========================
            if (type == Material.WAXED_LIGHTNING_ROD) {

                finalLoss = loss;
            }

            // =========================
            // CHISELED COPPER
            // =========================
            else if (type == Material.WAXED_CHISELED_COPPER) {

                finalLoss =
                        Math.max(
                                1,
                                loss / 2
                        );
            }

            // =========================
            // UNKNOWN BLOCK
            // =========================
            else {

                finalLoss = loss;
            }

            // =========================
            // SAFE LOSS
            // =========================
            finalLoss =
                    Math.min(
                            finalLoss,
                            energy
                    );

            if (finalLoss <= 0) {
                continue;
            }

            node.removeEnergy(finalLoss);

            // =========================
            // SQLITE SAVE
            // =========================
            CableNetwork.saveNode(node);

            // =========================
            // LOG
            // =========================
            if (log) {

                Main.getInstance()
                        .getLogger()
                        .info(
                                "[CableLoss] Removed "
                                        + finalLoss
                                        + " energy from "
                                        + node.getLocation()
                        );
            }
        }
    }
}