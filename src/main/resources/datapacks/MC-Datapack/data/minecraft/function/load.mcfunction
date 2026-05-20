#Подготовка к загрузке датапака. Это очень важно!
#Сообщение о НАЧАЛЕ перезапуска (Только игроки с тегом Operator увидят это сообщение!)
tellraw @a[tag=Operator] [{"text":"СЕРВЕР ","color":"dark_red"},{"text":"» ","color":"dark_gray"},{"text":"Загрузка датапака сервера...","color":"white"}]
#Создание задач для выполнения функций. (Для работы датапака)
scoreboard objectives add GlassPaneBreak minecraft.mined:minecraft.glass_pane
scoreboard objectives add GlassBreak minecraft.mined:minecraft.glass
scoreboard objectives add ItemsCount dummy
scoreboard objectives add BellUse minecraft.custom:minecraft.bell_ring
scoreboard objectives add KillItems dummy
scoreboard objectives add Radiation dummy
scoreboard objectives add Suicide trigger
scoreboard objectives add CodePanel trigger
scoreboard objectives add AntiRad minecraft.custom:minecraft.eat_cake_slice
scoreboard objectives add UseMace minecraft.used:minecraft.mace
scoreboard objectives add PlKills minecraft.custom:minecraft.player_kills
scoreboard objectives add MobKills minecraft.custom:minecraft.mob_kills
scoreboard objectives add Deaths deathCount
scoreboard objectives add MobHP dummy
scoreboard objectives add TrUse minecraft.used:minecraft.trident
scoreboard objectives add ElUse minecraft.used:minecraft.elytra
scoreboard objectives add ShUse minecraft.custom:minecraft.damage_blocked_by_shield
scoreboard objectives add Core1ShInt dummy
scoreboard objectives add Core1Temp dummy
scoreboard objectives add RecipeTime dummy
scoreboard objectives add RcDone dummy
scoreboard objectives add Core1Press dummy
scoreboard objectives add Core1CaseInt dummy
scoreboard objectives add Core1CasePress dummy
scoreboard objectives add Core1CaseTemp dummy
scoreboard objectives add RandomMelt dummy
scoreboard objectives add SelfDestruct dummy
scoreboard objectives add SdText dummy
scoreboard objectives add SdLock dummy
scoreboard objectives add TntCount dummy
scoreboard objectives add KillTnt dummy
scoreboard objectives add Ender_Chest minecraft.mined:minecraft.ender_chest
scoreboard objectives add Plasma_Cannon minecraft.used:minecraft.warped_fungus_on_a_stick
scoreboard objectives add Shoker minecraft.used:minecraft.warped_fungus_on_a_stick
#Сброс всех задач игроков и тегов. (Для избежания багов)
scoreboard players reset @a GlassPaneBreak
scoreboard players reset @a GlassBreak
scoreboard players reset @a ItemsCount
scoreboard players reset @a BellUse
scoreboard players reset @a KillItems
scoreboard players reset @a Radiation
scoreboard players reset @a Suicide
scoreboard players reset @a AntiRad
scoreboard players reset @a UseMace
scoreboard players reset @a PlKills
scoreboard players reset @a MobKills
scoreboard players reset @a Deaths
scoreboard players reset @a MobHP
scoreboard players reset @a TrUse
scoreboard players reset @a ElUse
scoreboard players reset @a ShUse
scoreboard players reset @a SsUse
#Выставление на нужные значения задач и тегов
scoreboard players set @a Radiation 0
scoreboard players set V SdText 1
scoreboard players set V SelfDestruct 0
scoreboard players set V Core1CaseInt 100
scoreboard players set V SdLock 0
scoreboard players set V Core1CasePress 0
scoreboard players set V Core1ShInt 100
scoreboard players set V RandomMelt 1
scoreboard players set V RecipeTime 0
scoreboard players set V Core1CaseTemp 0
scoreboard players set V Core1Temp 0
scoreboard players set V RcDone 0
scoreboard players set V Core1Press 0
recipe give @a *
#Запуск функций с таймером
function customfeatures/dragoneggspawn
function coregame/reactorsound
function coregame/strvalidation
function coregame/decreasepress
function customfeatures/entitylocator
function customfeatures/waypoint
function customfeatures/attributes
function customfeatures/terracotaspeed
function customfeatures/shieldslowness
function customfeatures/boostedcobweb
function customfeatures/blockdmg
function customfeatures/radlvls
function coregame/pressupdate
function coregame/decreaseintensity
function coregame/increaseintensity
function coregame/recipetimeadd
function guns/delete_coldown_tag
#Сообщение о ОКОНЧАНИИ перезапуска (Только игроки с тегом Operator увидят это сообщение!)
#Белый список игроков, которые его видят.
tag MrCotik337 add ChangeMode
tag F3nk1nsk9521 add ChangeMode
tag MrCotik337 add Operator
tag F3nk1nsk9521 add Operator
#Само сообщение перезапуска
tellraw @a[tag=Operator] [{"text":"СЕРВЕР ","color":"dark_red"},{"text":"» ","color":"dark_gray"},{"text":"Датапак сервера загружен!","color":"white"}]