execute store result score C ItemsCount if entity @e[type=item]
execute if score C ItemsCount matches 6400.. run scoreboard players add K KillItems 1
execute if score K KillItems matches 1 run tellraw @a[tag=Operator] [{"text":"СЕРВЕР ","color":"dark_red"},{"text":"» ","color":"dark_gray"},{"text":"Кол-во предметов: ","color":"white"},{"score":{"name":"C","objective":"ItemsCount"},"color":"red"},{"text":" слишком большое, они будут удалены, для предотвращения лагов!","color":"white"}]
execute if score K KillItems matches 1 run kill @e[type=item]
execute if score K KillItems matches 1 run scoreboard players reset K KillItems