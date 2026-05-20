package com.mcplugin.cp;

import com.mcplugin.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CodePanelSession {

    private static final Map<UUID, StringBuilder> input = new HashMap<>();

    // attempts + lock storage
    private static final Map<UUID, Integer> attempts = new HashMap<>();
    private static final Map<UUID, Long> lockedUntil = new HashMap<>();

    // =========================
    // INPUT
    // =========================
    public static StringBuilder get(UUID uuid) {
        if (!isSafe()) {
            clearAll();
            return new StringBuilder();
        }
        return input.computeIfAbsent(uuid, k -> new StringBuilder());
    }

    public static void reset(UUID uuid) {
        input.put(uuid, new StringBuilder());
    }

    public static String getCode(UUID uuid) {
        return get(uuid).toString();
    }

    public static void add(UUID uuid, String digit) {
        if (digit == null) return;
        get(uuid).append(digit);
    }

    // =========================
    // ATTEMPTS
    // =========================
    public static int getAttempts(UUID uuid) {
        return attempts.getOrDefault(uuid, 0);
    }

    public static int addAttempt(UUID uuid) {
        int value = getAttempts(uuid) + 1;
        attempts.put(uuid, value);
        return value;
    }

    public static void resetAttempts(UUID uuid) {
        attempts.remove(uuid);
    }

    // =========================
    // LOCK
    // =========================
    public static boolean isLocked(UUID uuid) {
        return lockedUntil.getOrDefault(uuid, 0L) > System.currentTimeMillis();
    }

    public static long getLockEnd(UUID uuid) {
        return lockedUntil.getOrDefault(uuid, 0L);
    }

    public static void setLockEnd(UUID uuid, long time) {
        if (time <= 0) {
            lockedUntil.remove(uuid);
        } else {
            lockedUntil.put(uuid, time);
        }
    }

    // ✅ ДОБАВЛЕННЫЙ МЕТОД (нужен твоему Command)
    public static long getRemainingLock(UUID uuid) {
        long end = lockedUntil.getOrDefault(uuid, 0L);
        return Math.max(0, end - System.currentTimeMillis());
    }

    // =========================
    // CLEAN
    // =========================
    public static void clearAll() {
        input.clear();
        attempts.clear();
        lockedUntil.clear();
    }

    // =========================
    // SAFE CHECK
    // =========================
    private static boolean isSafe() {
        return Main.getInstance() != null && Main.getInstance().getConfig() != null;
    }
}