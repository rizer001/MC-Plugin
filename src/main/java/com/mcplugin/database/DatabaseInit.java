package com.mcplugin.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInit {

    public static void init() {

        try (Connection con = DatabaseManager.getConnection();
             Statement st = con.createStatement()) {

            // =========================
            // ⚡ CABLES
            // =========================
            st.execute("""
                CREATE TABLE IF NOT EXISTS cables (
                    world TEXT NOT NULL,
                    x INTEGER NOT NULL,
                    y INTEGER NOT NULL,
                    z INTEGER NOT NULL,
                    energy INTEGER DEFAULT 0,
                    type TEXT DEFAULT 'CABLE',
                    PRIMARY KEY(world, x, y, z)
                );
            """);

            st.execute("""
                CREATE INDEX IF NOT EXISTS idx_cables_world
                ON cables(world);
            """);

            // =========================
            // 🔌 CABLE CONNECTIONS
            // =========================
            st.execute("""
                CREATE TABLE IF NOT EXISTS cable_connections (
                    world TEXT NOT NULL,
                    x INTEGER NOT NULL,
                    y INTEGER NOT NULL,
                    z INTEGER NOT NULL,

                    to_world TEXT NOT NULL,
                    to_x INTEGER NOT NULL,
                    to_y INTEGER NOT NULL,
                    to_z INTEGER NOT NULL
                );
            """);

            st.execute("""
                CREATE INDEX IF NOT EXISTS idx_connections_from
                ON cable_connections(world, x, y, z);
            """);

            st.execute("""
                CREATE INDEX IF NOT EXISTS idx_connections_to
                ON cable_connections(to_world, to_x, to_y, to_z);
            """);

            // =========================
            // 🛠 WORKBENCHES
            // =========================
            st.execute("""
                CREATE TABLE IF NOT EXISTS workbenches (
                    world TEXT NOT NULL,
                    x INTEGER NOT NULL,
                    y INTEGER NOT NULL,
                    z INTEGER NOT NULL,
                    PRIMARY KEY(world, x, y, z)
                );
            """);

            st.execute("""
                CREATE INDEX IF NOT EXISTS idx_workbenches_world
                ON workbenches(world);
            """);

            // =========================
            // ⚡ GENERATORS
            // =========================
            st.execute("""
                CREATE TABLE IF NOT EXISTS generators (
                    world TEXT NOT NULL,
                    x INTEGER NOT NULL,
                    y INTEGER NOT NULL,
                    z INTEGER NOT NULL,
                    fuel INTEGER DEFAULT 0,
                    energy INTEGER DEFAULT 0,
                    PRIMARY KEY(world, x, y, z)
                );
            """);

            st.execute("""
                CREATE INDEX IF NOT EXISTS idx_generators_world
                ON generators(world);
            """);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}