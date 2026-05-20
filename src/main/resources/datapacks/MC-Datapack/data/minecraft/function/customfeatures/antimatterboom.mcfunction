summon creeper ~ ~ ~ {powered:1,Fuse:0,ExplosionRadius:5,ignited:1}
particle campfire_signal_smoke ~ ~ ~ 0 0 0 0.1 64 normal
scoreboard players add @a[distance=..10] Radiation 1600
execute as @a[distance=..2] at @s run advancement grant @s only minecraft:datapack/react_with_antimatter
execute as @a[distance=..2] at @s run damage @s 8192 vaporisation
execute as @a[distance=..10] at @s run damage @s 18 antimatter
execute as @a[distance=..20] at @s run damage @s 9 antimatter_shockwave
summon area_effect_cloud ~ ~ ~ {Radius:1,RadiusPerTick:1,Duration:20,Tags:["Rad_Clud"],custom_particle:{type:ash},potion_contents:{potion:instant_damage,custom_color:0,custom_effects:[{id:instant_damage,duration:20,amplifier:9,show_particles:0b}]}}
kill @s