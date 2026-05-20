execute as @e[type=item_frame,tag=Core1StrValid] at @s if score V Core1ShInt matches ..99 run playsound block.note_block.bit master @a[distance=..15] ~ ~ ~ 100 2
execute as @e[type=item_frame,tag=Core1StrValid] at @s if score V Core1CaseInt matches ..99 run playsound block.note_block.bit master @a[distance=..15] ~ ~ ~ 100 2
schedule function coregame/reactorsound 10t replace