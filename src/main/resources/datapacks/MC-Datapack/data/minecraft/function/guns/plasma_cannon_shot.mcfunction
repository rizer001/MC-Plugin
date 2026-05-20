# Эффекты выстрела
playsound minecraft:block.respawn_anchor.deplete player @a ~ ~ ~ 1.5 2
particle minecraft:end_rod ~ ~1.2 ~ 0.3 0.3 0.3 0.1 15

# Расходуем эхо-осколок
clear @s echo_shard 1

# Теги
tag @a[tag=Sender] remove Sender
tag @s add Sender
tag @s add Immortal
tag @s add Coldown
schedule function guns/delete_immortal_tag 2s replace

# Создаём снаряд перед игроком
execute positioned ^ ^1.5 ^1 run summon snowball ~ ~ ~ {Tags:["Plasma_Bullet"],NoGravity:1b,Invulnerable:1b}

# Задаём направление через Motion (смотрим туда же, куда игрок)
execute as @e[type=snowball,tag=Plasma_Bullet,limit=1,sort=nearest] at @s facing entity @p eyes run tp @s ~ ~ ~ ~ ~

# Устанавливаем скорость вперёд
execute as @e[type=snowball,tag=Plasma_Bullet,limit=1,sort=nearest] store result entity @s Motion[0] double 0.001 run data get entity @s Rotation[1] 1
execute as @e[type=snowball,tag=Plasma_Bullet,limit=1,sort=nearest] store result entity @s Motion[2] double 0.001 run data get entity @s Rotation[0] 1