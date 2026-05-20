scoreboard players enable @a Suicide
execute as @a if score @s Suicide matches 1 run effect give @s instant_damage infinite 0 true
execute as @a if score @s Suicide matches 1 run scoreboard players set @s Suicide 0
