## TODO (this fork)

* More cheat menu options:
  * add exp
  * apply cheats to other present characters
  * transform
  * change fetishes
  * change time
  * get item (spawn in inventory with ItemType.getAllItems())
* new items
  * bit gags
  * cock (chastity) rings
  * urethral plugs
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
    getItemUseEffects()
  * hit with crop
  * Tease chastity during sex
* Mount Isil:
  * 4 defenders worship the lamia male "Uros", who turns out to be their brother.
  * One can't be hit with physical (must tease). On second encounter, can't be hit with spells either. Glycor. snake girl with pitch black scales
  * One always has the best tease (anything player is weak to). Sileniss. snake herm
  * One can only be hit by characters with their anus plugged, or covered in cum, or with larger dick than her. Vissea. snake shemale
  * One swaps weapons with the player (trades them an arcane feather duster). On second encounter, strips player. Cyrephses. snake girl 
  * To ascend, have to collect 4 keys
  * First key is any key you own (key to own chastity or slave's chastity/collar)
  * Second key requires player (or their slave) to wear opposite gender clothing
  * Third key require signing a piece of paper with a kiss (heavy makeup lip, or anal kiss)
  * Fourth key requires a feat of strength / arcane (deal physical damage, or have enough physique, or cast a leveled-up spells)
  * Other key: perform one of your fetishes (if player is alone, maybe masturbation fetish or exhibitionism, self bondage, denial, otherwise can use companions / slaves)
  * The first plateau is a square surrounded by shrines. The player can inspect the shrines, and either face combat, get a reward (steal offerings), fight a defender, solve a trial, or get a random melusine transformation item.
  * upon defeating all four defenders, and presenting all four keys, the player gets to fight Lissenthe. They start of constricted by her tail. Alternatively, can choose to worship her, get tail fucked, and if they don't die, turn the tables on her.
  * Lissenthe has a weakness - her cock is so corrupted that she needs to masturbate every third round, but if the player gets too close they are covered in her lust-inducing seed (or perhaps suffer the musk debuff)
  * Upon victory, 

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
* different text when doms use "Offer" SexActions
* work out how to add references to the play / other named NPCs in NPC descriptions
* SlaveForSale NPCs sometimes end up wearing two collars at once (the player has two keys) due to equipClothing override
* work out how SlaveJobFlags work, and why only "experience gain" appears to be showing up in the job management menu right now. (Check TooltipInformationEventListener)
* apparently it is possible for characters to have 0 legs? Make a new util and reword some "between her legs" type sentences, as well as adding some special dialogue for legless creatures
* fix bug where character descriptions are saved to XML as parsed text (rather than with the original commands)

## TODO (main)

See [todo_list.txt](src/com/lilithsthrone/res/doc/todo_list.txt)