execute in overworld as @e[type=item_frame] at @s if blocks ~-2 ~-5 ~-2 ~2 ~-1 ~2 29999979 100 29999979 all run tag @s add Core1StrValid
execute in overworld as @e[type=item_frame,tag=Core1StrValid] at @s unless blocks ~-2 ~-5 ~-2 ~2 ~-1 ~2 29999979 100 29999979 all run tag @s remove Core1StrValid
schedule function coregame/strvalidation 1s replace