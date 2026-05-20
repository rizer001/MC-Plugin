# Урон
damage @s 18 electron_shockwave
# Эффекты попадания
particle minecraft:explosion ~ ~ ~ 0 0 0 1 3 force
playsound minecraft:entity.generic.explode player @a ~ ~ ~ 1.2 2
# Дополнительный эффект
effect give @s glowing 5 0 true
# Убиваем снаряд
kill @e[type=snowball,tag=Plasma_Bullet,distance=..2]