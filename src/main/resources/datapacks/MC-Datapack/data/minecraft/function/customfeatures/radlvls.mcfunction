#Уровень 1
execute as @a[scores={Radiation=200..399}] run effect give @s hunger 2 0 true
execute as @a[scores={Radiation=200..399}] run effect give @s slowness 2 0 true
#Уровень 2
execute as @a[scores={Radiation=400..799}] run effect give @s hunger 2 1 true
execute as @a[scores={Radiation=400..799}] run effect give @s slowness 2 1 true
execute as @a[scores={Radiation=400..799}] run effect give @s nausea 2 0 true
#Уровень 3
execute as @a[scores={Radiation=800..1599}] run effect give @s hunger 2 2 true
execute as @a[scores={Radiation=800..1599}] run effect give @s slowness 2 2 true
execute as @a[scores={Radiation=800..1599}] run effect give @s nausea 2 0 true
execute as @a[scores={Radiation=800..1599}] run effect give @s poison 2 1 true
#Уровень 4
execute as @a[scores={Radiation=1600..3199}] run effect give @s hunger 2 4 true
execute as @a[scores={Radiation=1600..3199}] run effect give @s slowness 2 4 true
execute as @a[scores={Radiation=1600..3199}] run effect give @s nausea 2 0 true
execute as @a[scores={Radiation=1600..3199}] run effect give @s poison 2 4 true
execute as @a[scores={Radiation=1600..3199}] run effect give @s wither 2 1 true
#Уровень 5
execute as @a[scores={Radiation=3200..6399}] run effect give @s hunger 2 8 true
execute as @a[scores={Radiation=3200..6399}] run effect give @s slowness 2 8 true
execute as @a[scores={Radiation=3200..6399}] run effect give @s nausea 2 0 true
execute as @a[scores={Radiation=3200..6399}] run effect give @s poison 2 8 true
execute as @a[scores={Radiation=3200..6399}] run effect give @s wither 2 2 true
execute as @a[scores={Radiation=3200..6399}] run effect give @s instant_damage 2 0 true
#Уровень 6
execute as @a[scores={Radiation=6400..}] run effect give @s hunger 2 16 true
execute as @a[scores={Radiation=6400..}] run effect give @s slowness 2 16 true
execute as @a[scores={Radiation=6400..}] run effect give @s nausea 2 0 true
execute as @a[scores={Radiation=6400..}] run effect give @s poison 2 16 true
execute as @a[scores={Radiation=6400..}] run effect give @s wither 2 4 true
execute as @a[scores={Radiation=6400..}] run effect give @s instant_damage 2 1 true
schedule function customfeatures/radlvls 10t replace