package com.mcplugin.listeners;

import com.mcplugin.cable.CableBlock;
import com.mcplugin.cable.CableNetwork;
import com.mcplugin.cable.CableNode;
import com.mcplugin.cable.NodeType;

import com.mcplugin.energy.crafting.EnergyWorkbenchManager;

import com.mcplugin.util.LocationUtil;

import org.bukkit.Location;
import org.bukkit.Material;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        Location loc =
                LocationUtil.normalize(
                        e.getBlock().getLocation()
                );

        Material type =
                e.getBlock().getType();

        // =========================
        // 🛠 ENERGY WORKBENCH
        // =========================
        if (type == Material.CRAFTING_TABLE) {

            EnergyWorkbenchManager.add(loc);

            return;
        }

        // =========================
        // ⚡ CABLE / BATTERY
        // =========================
        if (!CableBlock.isCable(e.getBlock())) {
            return;
        }

        // create node
        CableNetwork.addNode(loc);

        CableNode node =
                CableNetwork.getNode(loc);

        if (node == null) {
            return;
        }

        // =========================
        // NODE TYPE
        // =========================
        if (type == Material.WAXED_COPPER_GRATE) {

            node.setType(NodeType.BATTERY);

        } else {

            node.setType(NodeType.CABLE);
        }

        // =========================
        // SQLITE SAVE
        // =========================
        CableNetwork.saveNode(node);

        // =========================
        // AUTO CONNECT
        // =========================
        autoConnect(loc, node);

        // =========================
        // SAVE AFTER CONNECTIONS
        // =========================
        CableNetwork.saveNode(node);
    }

    // =========================
    // AUTO CONNECT
    // =========================
    private void autoConnect(
            Location loc,
            CableNode node
    ) {

        if (node == null) {
            return;
        }

        for (Location nearby :
                LocationUtil.getNeighbors(loc)) {

            Location norm =
                    LocationUtil.normalize(nearby);

            CableNode neighbor =
                    CableNetwork.getNode(norm);

            if (neighbor == null) {
                continue;
            }

            // =========================
            // NO BATTERY ↔ BATTERY
            // =========================
            if (node.getType() == NodeType.BATTERY
                    && neighbor.getType() == NodeType.BATTERY) {
                continue;
            }

            // =========================
            // CONNECTION VALIDATION
            // =========================
            if (!LocationUtil.isFullyConnected(loc, norm)) {
                continue;
            }

            // =========================
            // CONNECT BOTH WAYS
            // =========================
            node.connect(norm);

            neighbor.connect(loc);

            // =========================
            // SQLITE SAVE
            // =========================
            CableNetwork.saveNode(node);
            CableNetwork.saveNode(neighbor);
        }
    }
}