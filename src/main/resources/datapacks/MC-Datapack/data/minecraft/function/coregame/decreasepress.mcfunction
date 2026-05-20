execute if score V Core1Press matches 100000..199999 run particle campfire_signal_smoke ~ ~ ~ 0 0 0 0.1 32 normal
execute if score V Core1Press matches 100000..199999 run scoreboard players add @a[distance=..4] Radiation 200
execute if score V Core1Press matches 200000..299999 run particle campfire_signal_smoke ~ ~ ~ 0 0 0 0.1 64 normal
execute if score V Core1Press matches 200000..299999 run scoreboard players add @a[distance=..4] Radiation 300
execute if score V Core1Press matches 300000..399999 run particle campfire_signal_smoke ~ ~ ~ 0 0 0 0.1 128 normal
execute if score V Core1Press matches 300000..399999 run scoreboard players add @a[distance=..4] Radiation 400
execute if score V Core1Press matches 400000..499999 run particle campfire_signal_smoke ~ ~ ~ 0 0 0 0.1 256 normal
execute if score V Core1Press matches 400000..499999 run scoreboard players add @a[distance=..4] Radiation 500
execute if score V Core1Press matches 500000.. run particle campfire_signal_smoke ~ ~ ~ 0 0 0 0.1 512 normal
execute if score V Core1Press matches 500000.. run scoreboard players add @a[distance=..4] Radiation 600
scoreboard players operation V Core1Press /= V Core1Temp