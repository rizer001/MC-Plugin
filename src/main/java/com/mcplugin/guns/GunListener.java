package com.mcplugin.guns;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GunListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent e) {

        Action action = e.getAction();

        // =========================
        // ONLY RIGHT CLICK
        // =========================
        if (action != Action.RIGHT_CLICK_AIR
                && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand() == null) return;

        if (p.getInventory().getItemInMainHand().getType()
                != Material.WARPED_FUNGUS_ON_A_STICK) return;

        PlasmaCannon.shoot(p);
    }
}