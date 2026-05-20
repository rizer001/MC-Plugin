execute as @a at @s if block ~ ~ ~ pointed_dripstone run damage @s 1 cactus
execute as @a at @s if block ~ ~-0.1 ~ end_rod[facing=up] run damage @s 1 cactus
schedule function customfeatures/blockdmg 5t replace