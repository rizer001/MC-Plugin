package com.mcplugin.energy.visual;

import com.mcplugin.cable.CableNetwork;
import com.mcplugin.cable.CableNode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.LightningRod;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class CableVisualTask extends BukkitRunnable {

    private final Random random = new Random();

    @Override
    public void run() {

        for (CableNode node : CableNetwork.getAllNodes()) {

            Location loc = node.getLocation();
            Block block = loc.getBlock();

            if (block.getType() != Material.WAXED_LIGHTNING_ROD) continue;

            BlockData raw = block.getBlockData();

            if (!(raw instanceof LightningRod data)) continue;

            int energy = node.getEnergy();

            // =========================
            // OFF STATE
            // =========================
            if (energy <= 0) {
                if (data.isPowered()) {
                    data.setPowered(false);
                    block.setBlockData(data, false);
                }
                continue;
            }

            // =========================
            // NORMALIZED ENERGY LEVEL
            // =========================
            double level = Math.min(energy, 1000) / 1000.0;

            double flickerChance;

            if (level < 0.3) {
                flickerChance = 0.1;
            } else if (level < 0.6) {
                flickerChance = 0.2;
            } else if (level < 0.85) {
                flickerChance = 0.3;
            } else {
                flickerChance = 0.5;
            }

            boolean powered = random.nextDouble() < flickerChance;

            // =========================
            // ONLY UPDATE IF CHANGED
            // =========================
            if (data.isPowered() != powered) {
                data.setPowered(powered);
                block.setBlockData(data, false);
            }
        }
    }
}