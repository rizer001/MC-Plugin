package com.mcplugin.guns;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import java.util.*;

public class PlasmaProjectile {

    private static final List<PlasmaProjectile> PROJECTILES = new ArrayList<>();

    private final Player owner;

    private Location loc;
    private Vector dir;

    private int life = 0;

    // =========================
    // ⚡ SPEED SYSTEM
    // =========================
    private double speed = 0.7;

    // =========================
    // ⚠ OVERLOAD PROTECTION
    // =========================
    private static final double MAX_SAFE_SPEED = 50.0;

    // =========================
    // 🎯 INSTABILITY
    // =========================
    private static final double INSTABILITY = 0.01;

    // =========================
    // 💥 RICOCHET COUNTER
    // =========================
    private int ricochetCount = 0;

    // =========================
    // 🧠 HIT CACHE
    // =========================
    private final Set<UUID> hitEntities = new HashSet<>();

    private final Random random = new Random();

    public PlasmaProjectile(Player owner) {

        this.owner = owner;

        this.loc =
                owner.getEyeLocation().clone();

        this.dir =
                owner.getLocation()
                        .getDirection()
                        .normalize();
    }

    public static void spawn(Player player) {
        PROJECTILES.add(new PlasmaProjectile(player));
    }

    public static void tickAll() {

        Iterator<PlasmaProjectile> it =
                PROJECTILES.iterator();

        while (it.hasNext()) {

            PlasmaProjectile p = it.next();

            p.tick();

            if (p.life > 12000) {
                it.remove();
            }
        }
    }

    public void tick() {

        life++;

        // =========================
        // ⚠ OVERLOAD PROTECTION
        // =========================
        if (speed >= MAX_SAFE_SPEED) {

            loc.getWorld().spawnParticle(
                    Particle.EXPLOSION_EMITTER,
                    loc,
                    1
            );

            loc.getWorld().playSound(
                    loc,
                    Sound.ENTITY_GENERIC_EXPLODE,
                    2f,
                    0.5f
            );

            PROJECTILES.remove(this);

            return;
        }

        // =========================
        // RANDOM DRIFT
        // =========================
        applyInstability();

        Location next =
                loc.clone()
                        .add(dir.clone().multiply(speed));

        Block block =
                next.getBlock();

        // =========================
        // BLOCK HIT
        // =========================
        if (!block.getType().isAir()) {

            handleBlockCollision(block);

            return;
        }

        // =========================
        // ENTITY HIT
        // =========================
        for (Entity e : next.getWorld()
                .getNearbyEntities(
                        next,
                        0.6,
                        0.6,
                        0.6
                )) {

            if (e instanceof Player p
                    && p.equals(owner)) {
                continue;
            }

            if (hitEntities.contains(e.getUniqueId())) {
                continue;
            }

            hit(e);
        }

        loc = next;

        // =========================
        // TRAIL
        // =========================
        loc.getWorld().spawnParticle(
                Particle.ELECTRIC_SPARK,
                loc,
                2,
                0.1,
                0.1,
                0.1,
                0
        );

        loc.getWorld().spawnParticle(
                Particle.DUST,
                loc,
                1,
                0,
                0,
                0,
                new Particle.DustOptions(
                        Color.AQUA,
                        1.3f
                )
        );
    }

    // =========================
    // INSTABILITY SYSTEM
    // =========================
    private void applyInstability() {

        Vector randomDrift =
                new Vector(
                        (random.nextDouble() - 0.5) * INSTABILITY,
                        (random.nextDouble() - 0.5) * INSTABILITY,
                        (random.nextDouble() - 0.5) * INSTABILITY
                );

        dir.add(randomDrift);

        dir.normalize();
    }

    // =========================
    // BLOCK COLLISION SYSTEM
    // =========================
    private void handleBlockCollision(Block block) {

        Material type =
                block.getType();

        float hardness =
                type.getHardness();

        // =========================
        // 🛡 UNBREAKABLE BLOCKS
        // =========================
        if (hardness == -1f) {

            block.getWorld().playSound(
                    block.getLocation(),
                    Sound.BLOCK_ANVIL_PLACE,
                    1f,
                    0.5f
            );

            block.getWorld().spawnParticle(
                    Particle.CRIT,
                    block.getLocation().add(0.5, 0.5, 0.5),
                    15,
                    0.2,
                    0.2,
                    0.2,
                    0
            );

            handleRicochet();

            return;
        }

        // =========================
        // BREAK BLOCK
        // =========================
        if (speed >= hardness) {

            block.getWorld().playSound(
                    block.getLocation(),
                    Sound.BLOCK_STONE_BREAK,
                    1f,
                    1.2f
            );

            block.getWorld().spawnParticle(
                    Particle.BLOCK,
                    block.getLocation().add(0.5, 0.5, 0.5),
                    25,
                    0.3,
                    0.3,
                    0.3,
                    block.getBlockData()
            );

            // =========================
            // BREAK NATURALLY
            // =========================
            block.breakNaturally();

            // =========================
            // SPEED LOSS
            // =========================
            speed -= hardness;

            if (speed < 0.1) {
                speed = 0.1;
            }

            // =========================
            // CONTINUE FLIGHT
            // =========================
            loc.add(
                    dir.clone().multiply(0.5)
            );

            return;
        }

        // =========================
        // RICOCHET
        // =========================
        handleRicochet();
    }

    // =========================
    // ENTITY HIT SYSTEM
    // =========================
    private void hit(Entity e) {

        hitEntities.add(
                e.getUniqueId()
        );

        e.getWorld().spawnParticle(
                Particle.EXPLOSION,
                e.getLocation(),
                2
        );

        e.getWorld().playSound(
                e.getLocation(),
                Sound.ENTITY_GENERIC_EXPLODE,
                1.2f,
                2f
        );

        if (e instanceof LivingEntity le) {

            le.damage(
                    18,
                    owner
            );
        }

        // =========================
        // PENETRATION ENERGY LOSS
        // =========================
        speed *= 0.92;

        if (speed < 0.1) {
            speed = 0.1;
        }

        // =========================
        // CONTINUE FLIGHT
        // =========================
        loc.add(
                dir.clone().multiply(0.4)
        );
    }

    // =========================
    // RICOCHET SYSTEM
    // =========================
    private void handleRicochet() {

        ricochetCount++;

        // =========================
        // REFLECT
        // =========================
        dir = dir.multiply(-1);

        // =========================
        // PUSH FROM WALL
        // =========================
        loc.add(
                dir.clone().multiply(0.3)
        );

        // =========================
        // SPEED BOOST
        // =========================
        speed *= 1.1;

        // =========================
        // FX
        // =========================
        loc.getWorld().playSound(
                loc,
                Sound.BLOCK_AMETHYST_BLOCK_HIT,
                1f,
                1.5f
        );

        loc.getWorld().spawnParticle(
                Particle.ELECTRIC_SPARK,
                loc,
                10,
                0.3,
                0.3,
                0.3,
                0
        );
    }

    public static List<PlasmaProjectile> getAll() {
        return PROJECTILES;
    }
}