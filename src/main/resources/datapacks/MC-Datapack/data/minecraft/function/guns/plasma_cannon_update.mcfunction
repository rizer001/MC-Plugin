# Стрельба
execute as @a[scores={Plasma_Cannon=1..}] if items entity @s[tag=!Coldown] weapon.mainhand minecraft:warped_fungus_on_a_stick[minecraft:custom_data={isPlasmaCannon:true}] if items entity @s weapon.offhand echo_shard at @s run function guns/plasma_cannon_shot
execute as @a[scores={Plasma_Cannon=1..}] if items entity @s[tag=Coldown] weapon.mainhand minecraft:warped_fungus_on_a_stick[minecraft:custom_data={isPlasmaCannon:true}] if items entity @s weapon.offhand echo_shard at @s run title @s actionbar [{"text":"Инструмент в перезарядке!","color":"red"}]
# Движение снарядов
execute as @e[type=snowball,tag=Plasma_Bullet] at @s run function guns/plasma_bullet_update
# Сброс
execute as @a[scores={Plasma_Cannon=1..}] at @s run scoreboard players set @a Plasma_Cannon 0