# Эффекты
playsound terf:taser master @a[distance=..5] ~ ~ ~ 100 0 1

# Урон + Отбрасывание
execute as @e[distance=0.1..2,sort=nearest] run function shoker/shoker_hit

# Тег
tag @s add Coldown

# Расходуем стержень
clear @s breeze_rod 1