# Сбрасываем счётчики
scoreboard players set @s bullet_ricochet 0

# Определяем направление движения (сохраняем в счётчик)
execute store result score @s bullet_tmp run data get entity @s Motion[0] 1000
execute store result score @s bullet_tmp run data get entity @s Motion[1] 1000
execute store result score @s bullet_tmp run data get entity @s Motion[2] 1000

# Проверяем 6 направлений и устанавливаем тип рикошета
# 1 = удар спереди/сзади (ось Z)
# 2 = удар слева/справа (ось X)
# 3 = удар сверху/снизу (ось Y)

# Проверка спереди (Z+)
execute positioned ^ ^ ^0.5 unless block ~ ~ ~ #minecraft:replaceable run scoreboard players set @s bullet_ricochet 1

# Проверка сзади (Z-)
execute positioned ^ ^ ^-0.5 unless block ~ ~ ~ #minecraft:replaceable run scoreboard players set @s bullet_ricochet 1

# Проверка справа (X+)
execute positioned ^0.5 ^ ^ unless block ~ ~ ~ #minecraft:replaceable run scoreboard players set @s bullet_ricochet 2

# Проверка слева (X-)
execute positioned ^-0.5 ^ ^ unless block ~ ~ ~ #minecraft:replaceable run scoreboard players set @s bullet_ricochet 2

# Проверка сверху (Y+)
execute positioned ^ ^0.5 ^ unless block ~ ~ ~ #minecraft:replaceable run scoreboard players set @s bullet_ricochet 3

# Проверка снизу (Y-)
execute positioned ^ ^-0.5 ^ unless block ~ ~ ~ #minecraft:replaceable run scoreboard players set @s bullet_ricochet 3

# Вызываем соответствующий рикошет
execute if score @s bullet_ricochet matches 1 run function guns/plasma_bullet_ricochet_z
execute if score @s bullet_ricochet matches 2 run function guns/plasma_bullet_ricochet_x
execute if score @s bullet_ricochet matches 3 run function guns/plasma_bullet_ricochet_y