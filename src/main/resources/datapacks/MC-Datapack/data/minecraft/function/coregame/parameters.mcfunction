#Логистика блоков
execute if block ~1 ~ ~-2 waxed_copper_bulb[powered=true] if score V Core1Temp matches -270.. run scoreboard players remove V Core1Temp 3
execute if block ~-1 ~ ~-2 waxed_copper_bulb[powered=true] if score V Core1Temp matches -273..6000 run scoreboard players add V Core1Temp 3
execute if block ~1 ~ ~-2 waxed_copper_bulb[powered=true] if score V Core1Temp matches -271.. run scoreboard players remove V Core1CaseTemp 2
execute if block ~-1 ~ ~-2 waxed_copper_bulb[powered=true] if score V Core1Temp matches -273..8000 run scoreboard players add V Core1CaseTemp 2
execute if block ~-1 ~ ~-2 waxed_copper_bulb[powered=true] if score V Core1Temp matches -273..8000 run scoreboard players add V Core1CasePress 4
execute if block ~-1 ~ ~-2 waxed_copper_bulb[powered=true] if score V Core1Temp matches 0.. run particle end_rod ~ ~-1 ~ 0 -1.15 0 0.1 0 normal
execute if block ~1 ~ ~-2 waxed_copper_bulb[powered=true] if score V Core1Temp matches 0.. run particle end_rod ~ ~-3 ~ 0 1.15 0 0.1 0 normal
execute if score V Core1ShInt matches ..99 if block ~2 ~ ~-1 waxed_copper_bulb run setblock ~2 ~ ~-1 waxed_copper_bulb[lit=true] replace
execute if score V Core1ShInt matches 100 if block ~2 ~ ~-1 waxed_copper_bulb run setblock ~2 ~ ~-1 waxed_copper_bulb[lit=false] replace
execute if score V Core1CaseInt matches ..99 if block ~2 ~ ~1 waxed_copper_bulb run setblock ~2 ~ ~1 waxed_copper_bulb[lit=true] replace
execute if score V Core1CaseInt matches 100 if block ~2 ~ ~1 waxed_copper_bulb run setblock ~2 ~ ~1 waxed_copper_bulb[lit=false] replace
#Операции с давлением
scoreboard players operation V Core1Press += V Core1Temp
execute if score V Core1Press matches ..-1 run scoreboard players set V Core1Press 0
execute if score V Core1Temp matches 1000..5000 run scoreboard players remove V Core1Press 1
#Получшение достежений
execute if score V Core1Temp matches 1.. run advancement grant @a[distance=..10] only datapack/start_dfc
execute if score V RecipeTime matches 99.. run advancement grant @a[distance=..10] only datapack/complete_dfc_recipe
execute if score V SelfDestruct matches 1 run advancement grant @a[distance=..10] only datapack/dfc_self_destruct
execute if score V Core1ShInt matches ..99 run advancement grant @a[distance=..10] only datapack/dfc_unstable
execute if score V Core1Temp matches 1000.. positioned ~ ~-3 ~ run advancement grant @a[distance=..1.5] only datapack/inside_dfc
execute if score V Core1Temp matches 1000.. positioned ~ ~-3 ~ run advancement grant @a[distance=..1.5,scores={Radiation=6400..}] only datapack/burn_inside_dfc
#Протокол самоуничтожения
execute if score V SelfDestruct matches 0 if score V Core1Temp matches 1000..5000 run execute store result score V RandomMelt run random value 1..1000000
execute if score V SelfDestruct matches 0 if score V RandomMelt matches 1000000 run scoreboard players set V SelfDestruct 1
execute if score V SelfDestruct matches 1 run scoreboard players set V RandomMelt 0
execute if score V SelfDestruct matches 1 run function coregame/selfdestruct
#Операции с параметрами ядра
execute if score V Core1Temp matches 1000.. positioned ~ ~-3 ~ run scoreboard players add @a[distance=..1.5] Radiation 2
execute if score V Core1Temp matches -272.. run scoreboard players remove V Core1Temp 1
execute if score V Core1CasePress matches 1.. run scoreboard players remove V Core1CasePress 1
execute if score V Core1CaseTemp matches -272.. run scoreboard players remove V Core1CaseTemp 1
execute if score V RecipeTime matches 100.. run scoreboard players add V RcDone 1
#Условия деструкции
execute if score V Core1ShInt matches ..0 run function coregame/meltdown
execute if score V Core1CaseInt matches ..0 run function coregame/meltdown