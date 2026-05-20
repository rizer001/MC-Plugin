# Частицы при полёте
particle minecraft:electric_spark ~ ~ ~ 0.3 0.3 0.3 0 6
particle minecraft:dust{color:[0.0,0.8,1.0],scale:1} ~ ~ ~ 0.1 0.1 0.1 0 6

# Проверка попадания в сущности
execute positioned ~-0.5 ~-0.5 ~-0.5 as @e[type=!marker,type=!snowball,dx=0.1,dy=0.1,dz=0.1,tag=!Immortal] run function guns/plasma_bullet_hit

# Проверка столкновения с блоком (проверяем перед снарядом)
execute positioned ^ ^ ^0.9 unless block ~ ~ ~ #minecraft:replaceable run function guns/plasma_bullet_ricochet

# Удаление если застрял в блоке (защита от багов)
execute unless block ~ ~ ~ #minecraft:replaceable run function guns/plasma_bullet_hit

# Движение вперёд
execute positioned ^ ^ ^10 run summon marker ~ ~ ~ {Tags:["temp_direction"]}
tp @s ~ ~ ~ facing entity @e[type=marker,tag=temp_direction,limit=1,sort=nearest] feet
kill @e[type=marker,tag=temp_direction]
tp @s ^ ^ ^-0.1