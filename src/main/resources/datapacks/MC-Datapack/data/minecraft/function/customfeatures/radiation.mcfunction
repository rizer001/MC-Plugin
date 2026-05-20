execute as @a if items entity @s weapon.mainhand clock[minecraft:custom_data={isDosimeter:true}] run title @s actionbar [{"text":"Доза радиации: ","color":"white"},{"score":{"name":"@s","objective":"Radiation"},"color":"gray"},{"text":" мЗв"}]
execute as @a if items entity @s inventory.* ancient_debris run scoreboard players add @s Radiation 2
execute as @a if items entity @s hotbar.* ancient_debris run scoreboard players add @s Radiation 2
execute as @a at @s if biome ~ ~ ~ basalt_deltas run scoreboard players add @s Radiation 2
execute as @a[scores={Radiation=..-1}] run scoreboard players set @s Radiation 0
execute as @a[scores={Radiation=200..,AntiRad=1}] run scoreboard players remove @s Radiation 100
execute as @a[scores={AntiRad=1}] run scoreboard players remove @s AntiRad 1
execute as @a[scores={UseMace=1}] run scoreboard players add @s Radiation 50
execute as @a[scores={UseMace=1}] run scoreboard players set @s UseMace 0
execute as @a[scores={TrUse=1}] run scoreboard players add @s Radiation 50
execute as @a[scores={TrUse=1}] run scoreboard players set @s TrUse 0
execute as @a[scores={ElUse=1}] run scoreboard players add @s Radiation 50
execute as @a[scores={ElUse=1}] run scoreboard players set @s ElUse 0
execute as @a[predicate=can_see_sky,predicate=in_the_end] run scoreboard players add @s Radiation 2 
execute as @a if items entity @s weapon.mainhand shield[minecraft:custom_data={isLeadShield:true}] run scoreboard players remove @s Radiation 2
execute as @a[scores={Radiation=200..,PlKills=1}] run scoreboard players remove @s Radiation 100
execute as @a[scores={PlKills=1}] run scoreboard players remove @s PlKills 1
execute as @a[scores={Radiation=200..,MobKills=1}] run scoreboard players remove @s Radiation 100
execute as @a[scores={MobKills=1}] run scoreboard players remove @s MobKills 1
execute as @a[scores={Deaths=1}] run scoreboard players set @s Radiation 0
execute as @a[scores={Deaths=1}] run scoreboard players set @s Deaths 0
execute as @a[scores={Radiation=1..}] run scoreboard players remove @s Radiation 1