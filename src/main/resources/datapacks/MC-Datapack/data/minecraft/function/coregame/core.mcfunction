execute at @s if score V Core1Temp matches 0..999 run particle dust{color:[0.5,0.5,0.5],scale:1.25} ~ ~-2.6 ~ 0 0 0 0 16 normal
execute at @s if score V Core1Temp matches 1000..1499 run particle dust{color:[0.5,0.0,0.0],scale:1.25} ~ ~-2.6 ~ 0 0 0 0 16 normal
execute at @s if score V Core1Temp matches 1500..1999 run particle dust{color:[1.0,0.0,0.0],scale:1.25} ~ ~-2.6 ~ 0 0 0 0 16 normal
execute at @s if score V Core1Temp matches 2000..2999 run particle dust{color:[1.0,0.5,0.0],scale:1.25} ~ ~-2.6 ~ 0 0 0 0 16 normal
execute at @s if score V Core1Temp matches 3000..3999 run particle dust{color:[1.0,1.0,0.0],scale:1.25} ~ ~-2.6 ~ 0 0 0 0 16 normal
execute at @s if score V Core1Temp matches 4000.. run particle dust{color:[1.0,1.0,1.0],scale:1.25} ~ ~-2.6 ~ 0 0 0 0 16 normal
execute at @s if score V Core1Temp matches 1000..4999 if block ~ ~-3 ~-2 diamond_block run particle end_rod ~ ~-2.5 ~ 0 0 -1.5 0.1 0 normal
execute at @s if score V Core1Temp matches 1000..4999 if block ~ ~-3 ~-2 diamond_block run particle lava ~ ~-2.5 ~-1.4 0 0 0 0 1 normal
execute at @s if score V Core1Temp matches 1000..4999 if block ~ ~-3 ~-2 diamond_block run particle scrape ~ ~-2.5 ~ 0 0 2 1 0 normal
execute at @s if score V Core1Temp matches 1000..4999 if block ~ ~-3 ~-2 diamond_block run particle copper_fire_flame ~ ~-2.5 ~1.4 0 0 0 0.01 1 normal