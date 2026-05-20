package com.mcplugin.guns;

import org.bukkit.scheduler.BukkitRunnable;

public class PlasmaProjectileTask extends BukkitRunnable {

    @Override
    public void run() {
        PlasmaProjectile.tickAll();
    }
}