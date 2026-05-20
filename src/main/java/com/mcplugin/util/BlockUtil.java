package com.mcplugin.util;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtil {

    // =========================
    // ⚡ IS ENERGY CABLE
    // =========================
    public static boolean isCable(Block block) {

        Material type = block.getType();

        return type == Material.WAXED_LIGHTNING_ROD
                || type == Material.WAXED_CHISELED_COPPER;
    }

    // =========================
    // 🔌 CAN CONNECT IN NETWORK
    // =========================
    public static boolean isConnectable(Block block) {

        Material type = block.getType();

        // в будущем сюда можно добавить коннекторы, батареи и т.д.
        return type == Material.WAXED_LIGHTNING_ROD
                || type == Material.WAXED_CHISELED_COPPER;
    }

    // =========================
    // ⚡ IS STRAIGHT CABLE (ROD ONLY)
    // =========================
    public static boolean isStraightCable(Block block) {
        return block.getType() == Material.WAXED_LIGHTNING_ROD;
    }

    // =========================
    // 🔁 IS JUNCTION / TURN
    // =========================
    public static boolean isJunction(Block block) {
        return block.getType() == Material.WAXED_CHISELED_COPPER;
    }
}