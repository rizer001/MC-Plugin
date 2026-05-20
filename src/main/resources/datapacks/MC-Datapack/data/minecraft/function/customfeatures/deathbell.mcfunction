
execute as @a[scores={BellUse=1..}] at @s run summon lightning_bolt ~ ~ ~
execute as @a[scores={BellUse=1..}] run scoreboard players remove @s Radiation 100
execute as @a[scores={BellUse=1..}] run scoreboard players set @s BellUse 0