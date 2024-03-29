## TODO (this fork)

* More cheat menu options:
  * add exp
  * apply cheats to other present characters
  * transform
  * change fetishes
  * change time
  * get item (spawn in inventory with ItemType.getAllItems())
* Pet play (allow to ride slaves with saddles, bit gags)
  * https://www.linearity.io/
  * https://inkscape.org/
  * https://boxy-svg.com/app
  * https://editor.method.ac
  * https://github.com/SVG-Edit/svgedit
  * https://www.janvas.com/create-draw-edit/en/svg-online-editor.html
  * https://www.svgator.com/
* Dialogue for playing with toys / bondage items
  * Buttplug tease (slightly pull out, prod)
  * Buttplug lick clean on unequip:

    applyUnequipClothingEffects()
  * hit with crop
  * Tease chastity during sex
* Add slave jobs for enslavable named characters
  * Hyorlyss, Fyrsia, Jhortrax
* "Get Bathed" option (if slave is in party)
* Pain play (impact, electro, sharps, slave job punching-bag)
  * sadistic foot+cock, foot+pussy actions (treading on)
  * sadistic hand+cock, hand+pussy actions (slapping)
* extended cum play (display in mouth, cum sharing)
* Watersports
* Hypno
* Allow NPCs to use "AskAction" during sex
* Allow sub to "role play as dom" (can perform any action, but must always answer yes to requests, and must ask before performing non self-penetrative actions)
* Slave job femdom
* Allow dom giving directions to other pairs during multi participant scenes (eat her pussy etc.)
* Slowly build attraction / obedience with multiple victories over an encountered NPC, resulting in mutually satisfying sex (and perhaps more influence if chastity devices are used)
* "Imposing" option: skip combat by intimidating the opposition, or showing off particular skills
* find ways to increase physique (enchantments)
* find ways to change an NPC's gender preferences (modify over time if eager during sex? player exception after enough affection?)
* Original body & kinks (see what the player has transformed their slaves into)
  * NPC who resets character to original
* "Cum blocking" items (urethral plugs, maybe an enchantment?)
  * Update dialogue for characters who naturally produce 0mL of cum
* Flesh out "Talk about background" (SlaveDialogue.java)
* Flesh out the bounty system (bar in Slaver's alley)
* Flesh out companions system
* slave permission for self-clothing
* Abstraction for referring to the other partner (pet names, dominant, submissive, gentle or rough terms)
* different copy when doms use "Offer" SexActions
* work out how to add references to the play / other named NPCs in NPC descriptions
* SlaveForSale NPCs sometimes end up wearing two collars at once (the player has two keys) due to equipClothing override
* work out how SlaveJobFlags work, and why only "experience gain" appears to be showing up in the job management menu right now. (Check TooltipInformationEventListener)
* apparently it is possible for characters to have 0 legs? Make a new util and reword some "between her legs" type sentences, as well as adding some special dialogue for legless creatures
* fix bug where character descriptions are saved to XML as parsed text (rather than with the original commands)

## TODO (main)

See [todo_list.txt](src/com/lilithsthrone/res/doc/todo_list.txt)