# Эффекты
playsound minecraft:block.amethyst_block.hit player @a ~ ~ ~ 1.0 1.5
particle minecraft:electric_spark ~ ~ ~ 0.3 0.3 0.3 0.5 10 force

# Отражаем: инвертируем рыскание (yaw) и разворачиваем на 180
# Это создаёт эффект отражения от боковых стен
tp @s ~ ~ ~ ~-180 ~

# Отодвигаем от блока
tp @s ^ ^ ^-1.0