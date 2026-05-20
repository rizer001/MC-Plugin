package com.mcplugin.guns;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlasmaCannon {

    private static final ItemStack AMMO = new ItemStack(Material.ECHO_SHARD, 1);

    public static void shoot(Player player) {

        // cooldown (4 сек)
        if (player.hasCooldown(Material.WARPED_FUNGUS_ON_A_STICK)) {
            player.sendActionBar("§cИнструмент в перезарядке!");
            return;
        }

        // проверка предмета
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand == null || hand.getType() != Material.WARPED_FUNGUS_ON_A_STICK) {
            return;
        }

        if (!hand.hasItemMeta()) return;

        // =========================
        // AMMO CHECK
        // =========================
        if (!player.getInventory().contains(Material.ECHO_SHARD, 1)) {
            player.sendActionBar("§cНет патронов!");
            return;
        }

        // =========================
        // REMOVE 1 AMMO
        // =========================
        player.getInventory().removeItem(AMMO);

        player.setCooldown(Material.WARPED_FUNGUS_ON_A_STICK, 80);

        // spawn projectile
        PlasmaProjectile.spawn(player);

        // sound
        player.getWorld().playSound(
                player.getLocation(),
                Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE,
                1.5f,
                2f
        );
    }
}