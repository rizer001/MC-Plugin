execute as @a[scores={ShUse=1}] run effect give @s slowness 1 255 true
execute as @a[scores={ShUse=1}] run scoreboard players set @s ShUse 0
schedule function customfeatures/shieldslowness 5t replace