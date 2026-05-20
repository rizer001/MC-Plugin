data modify block ~ ~-4 ~-3 front_text.messages[0] set value {"text":"Данные ядра","color":"white"}
execute if score V Core1CaseTemp matches ..4999 run data modify block ~ ~-4 ~-3 front_text.messages[1] set value {"text":"T: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1Temp"}},{"text":" C*"}]}
execute if score V Core1CaseTemp matches 5000.. run data modify block ~ ~-4 ~-3 front_text.messages[1] set value {"text":"T: ","color":"dark_red","extra":[{"score":{"name":"V","objective":"Core1Temp"}},{"text":" C*"}]}
data modify block ~ ~-4 ~-3 front_text.messages[2] set value {"text":"P: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1Press"}},{"text":" kPa"}]}
execute if score V Core1ShInt matches 100.. run data modify block ~ ~-4 ~-3 front_text.messages[3] set value {"text":"I: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1ShInt"}},{"text":" %"}]}
execute if score V Core1ShInt matches ..99 run data modify block ~ ~-4 ~-3 front_text.messages[3] set value {"text":"I: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1ShInt"}},{"text":" %"}]}
data modify block ~-1 ~-4 ~-3 front_text.messages[0] set value {"text":"Данные корпуса","color":"white"}
data modify block ~-1 ~-4 ~-3 front_text.messages[1] set value {"text":"T: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1CaseTemp"}},{"text":" C*"}]}
data modify block ~-1 ~-4 ~-3 front_text.messages[2] set value {"text":"P: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1CasePress"}},{"text":" kPa"}]}
execute if score V Core1CaseInt matches 100.. run data modify block ~-1 ~-4 ~-3 front_text.messages[3] set value {"text":"I: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1CaseInt"}},{"text":" %"}]}
execute if score V Core1CaseInt matches ..99 run data modify block ~-1 ~-4 ~-3 front_text.messages[3] set value {"text":"I: ","color":"white","extra":[{"score":{"name":"V","objective":"Core1CaseInt"}},{"text":" %"}]}
data modify block ~1 ~-4 ~-3 front_text.messages[0] set value {"text":"Данные рецепта","color":"white"}
execute if score V Core1ShInt matches 100.. run data modify block ~1 ~-4 ~-3 front_text.messages[1] set value {"text":"P: ","color":"white","extra":[{"score":{"name":"V","objective":"RecipeTime"}},{"text":" %"}]}
execute if score V RecipeTime matches 0 run data modify block ~1 ~-4 ~-3 front_text.messages[2] set value {"text":"S: Бездействует","color":"white"}
execute if score V RecipeTime matches 1..9999 run data modify block ~1 ~-4 ~-3 front_text.messages[2] set value {"text":"S: Готовится","color":"white"}
execute if score V RecipeTime matches 10000 run data modify block ~1 ~-4 ~-3 front_text.messages[2] set value {"text":"S: Завершён","color":"white"}
execute if block ~ ~-3 ~2 gold_block run data modify block ~1 ~-4 ~-3 front_text.messages[3] set value {"text":"F: Есть","color":"white"}
execute unless block ~ ~-3 ~2 gold_block run data modify block ~1 ~-4 ~-3 front_text.messages[3] set value {"text":"F: Нету","color":"white"}
execute if score V Core1Temp matches 5010 if block ~-1 ~-2 ~-3 lever[powered=true] run tellraw @a[distance=..15] [{"text":"Р.Т.С ","color":"dark_red"},{"text":"» ","color":"dark_gray"},{"text":"Температура ядра критическая! Обнаружена деградация сдерж. поля...","color":"white"}]
execute if score V RecipeTime matches 100.. run tellraw @a[distance=..15] [{"text":"Р.Т.С ","color":"dark_red"},{"text":"» ","color":"dark_gray"},{"text":"Рецепт слияния готов! Отключение реактора...","color":"white"}]
execute if score V RecipeTime matches 100.. run playsound block.note_block.pling master @a[distance=..5] ~ ~-2.6 ~ 100 0 1
execute if score V Core1Temp matches 1000.. run playsound block.beacon.power_select master @a[distance=..5] ~ ~-2.6 ~ 100 0 1