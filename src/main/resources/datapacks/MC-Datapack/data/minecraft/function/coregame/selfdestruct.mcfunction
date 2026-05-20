execute unless score V SdText matches 0 run scoreboard players set V SdText 1
execute if score V SdText matches 1 run tellraw @a[distance=..100] [{"text":"Р.Т.С ","color":"dark_red"},{"text":"» ","color":"dark_gray"},{"text":"Протокол самоуничтожения инициирован. Взрыв ядра неизбежен.","color":"red"}]
execute run scoreboard players set V SdText 0
execute run scoreboard players remove V Core1ShInt 2
execute if score V Core1ShInt matches 1.. run schedule function coregame/selfdestruct 1t replace