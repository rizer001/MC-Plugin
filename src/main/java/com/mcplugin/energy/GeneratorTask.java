package com.mcplugin.energy;

import com.mcplugin.Main;
import com.mcplugin.cable.CableNetwork;
import com.mcplugin.cable.CableNode;
import com.mcplugin.util.LocationUtil;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public class GeneratorTask extends BukkitRunnable {

    @Override
    public void run() {

        FileConfiguration cfg = Main.getInstance().getConfig();

        if (!cfg.getBoolean("energy.generator.enabled", true)) {
            return;
        }

        int energyPerFuel =
                cfg.getInt("energy.generator.energy_per_fuel", 100);

        boolean log =
                cfg.getBoolean("energy.generator.log", false);

        Set<CableNode> nodes = Set.copyOf(CableNetwork.getAllNodes());

        for (CableNode node : nodes) {

            Location nodeLoc = node.getLocation();

            for (Location nearby : LocationUtil.getNeighbors(nodeLoc)) {

                Location furnaceLoc = LocationUtil.normalize(nearby);
                Block block = furnaceLoc.getBlock();
                Material type = block.getType();

                if (type != Material.FURNACE
                        && type != Material.BLAST_FURNACE
                        && type != Material.SMOKER) {
                    continue;
                }

                if (!LocationUtil.isFullyConnected(nodeLoc, furnaceLoc)) {
                    continue;
                }

                BlockState state = block.getState();

                if (!(state instanceof Furnace furnace)) {
                    continue;
                }

                ItemStack fuel = furnace.getInventory().getFuel();

                // =========================
                // SAFE BLOCK DATA (NO CAST)
                // =========================
                org.bukkit.block.data.type.Furnace data =
                        (org.bukkit.block.data.type.Furnace) block.getBlockData();

                if (fuel == null || fuel.getType().isAir()) {
                    data.setLit(false);
                    block.setBlockData(data);
                    continue;
                }

                if (!fuel.getType().isFuel()) {
                    data.setLit(false);
                    block.setBlockData(data);
                    continue;
                }

                data.setLit(true);
                block.setBlockData(data);

                // =========================
                // SAFE FUEL COPY FIX
                // =========================
                ItemStack newFuel = fuel.clone();
                int amount = newFuel.getAmount() - 1;

                if (amount <= 0) {
                    furnace.getInventory().setFuel(null);
                } else {
                    newFuel.setAmount(amount);
                    furnace.getInventory().setFuel(newFuel);
                }

                // =========================
                // ENERGY OUTPUT
                // =========================
                node.addEnergy(energyPerFuel);

                if (log) {
                    Main.getInstance().getLogger().info(
                            "[GENERATOR] +" + energyPerFuel +
                                    " energy at " + nodeLoc
                    );
                }
            }
        }
    }
}