# Урон
damage @s 6 high_voltage by @p

# Мощное отбрасывание
tp @s ~ ~ ~ facing entity @p
effect give @s levitation 1 0 true
effect give @s slowness 5 255 true

# Дополнительные частицы
particle minecraft:electric_spark ~ ~ ~ 1 1 1 0 100 normal
particle minecraft:explosion ~ ~ ~ 0 0 0 0 10 normal

# Звук
playsound terf:explosion.tau_cannon_shoot master @a[distance=..5] ~ ~ ~ 100 0 1