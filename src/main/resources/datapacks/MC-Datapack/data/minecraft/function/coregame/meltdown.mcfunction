execute positioned ~ ~-3 ~ run advancement grant @a[distance=..1] only datapack/one_time_heater
advancement grant @a[distance=..100] only minecraft:datapack/explode_dfc
setblock ~ ~-1 ~ air destroy
setblock ~ ~-5 ~ air destroy
summon creeper ~ ~-3 ~ {powered:1,Fuse:0,ExplosionRadius:10,ignited:1}
particle campfire_signal_smoke ~ ~-3 ~ 0 0 0 0.1 64 normal
scoreboard players add @a[distance=..20] Radiation 3200
execute positioned ~ ~-3 ~ run scoreboard players add @a[distance=..1.5] Radiation 6400
scoreboard players set V SelfDestruct 0
scoreboard players set V SdLock 0
scoreboard players set V RecipeTime 0
scoreboard players set V Core1CaseTemp 0
scoreboard players set V Core1CasePress 0
scoreboard players set V Core1CaseInt 10000
scoreboard players set V Core1ShInt 10000
scoreboard players set V Core1Temp 0