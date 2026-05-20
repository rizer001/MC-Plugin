
execute as @a unless items entity @s weapon.mainhand * if score @s GlassBreak matches 1 run damage @s 5 cactus
execute as @a unless items entity @s weapon.mainhand * if score @s GlassBreak matches 1 run title @s actionbar [{"text":"Не ломайте стекло рукой!","color":"red"}]
execute as @a unless items entity @s weapon.mainhand * if score @s GlassPaneBreak matches 1 run damage @s 5 cactus
execute as @a unless items entity @s weapon.mainhand * if score @s GlassPaneBreak matches 1 run title @s actionbar [{"text":"Не ломайте стекло рукой!","color":"red"}]
execute as @a run scoreboard players reset @s GlassBreak
execute as @a run scoreboard players reset @s GlassPaneBreak