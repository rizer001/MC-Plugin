execute if score V Core1Temp matches -273..999 if score V RecipeTime matches 1.. run scoreboard players remove V RecipeTime 1
execute if score V Core1Temp matches 1000..5000 if score V RecipeTime matches ..99 run scoreboard players add V RecipeTime 1
schedule function minecraft:coregame/recipetimeadd 5s replace