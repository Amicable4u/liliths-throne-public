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
  * Begin on the plateau. To ascend, have to collect 4 keys
  * The first plateau is a square surrounded by shrines. The player can inspect the shrines, and either face combat, get a reward (steal offerings), solve a puzzle, complete a key trial, or get a random snake transformation item.
  * First key is any key you own (key to own chastity or slave's chastity/collar). You get it back after defeating Uros.
  * Second key requires player (or their companion) to wear opposite gender clothing
  * Third key require signing a piece of paper with a kiss (heavy makeup lip, or anal kiss)
  * Fourth key requires a feat of strength / arcane (deal enough physical damage, or cast a leveled-up spell)
  * Other key: perform one of your fetishes If player is alone, maybe masturbation fetish or exhibitionism, self bondage, denial, otherwise can use companions / slaves. Perhaps the shrine contains a dick statue that the player can give blowjob / ride.
  * 4 defenders worship the lamia male "Uros", who turns out to be their brother.
  * Glycor - snake girl with pitch black scales: Can't be hit with physical (must tease). On second encounter, can't be hit with spells either.
  * Silenis - snake herm: Always has the best tease (anything player is weak to).
  * Vissea - snake shemale: Can only be hit by characters with their anus plugged, or covered in cum, or with larger dick than her.
  * Cyrephses - snake girl: Swaps weapons with the player (trades them an arcane feather duster). On second encounter, strips player. 
  * upon defeating all four defenders, and presenting all four keys, the player gets to fight Uros. They start of constricted by his tail. Alternatively, can choose to worship him, get tail fucked, and if they don't die, turn the tables on him.
  * Uros has a weakness - his cock is so corrupted that he needs to masturbate every third round, but if the player gets too close they are covered in his lust-inducing seed (or perhaps suffer the musk debuff)
  * Upon victory, Uros offers to let you go around with one of his sisters in exchange for sparing him. His sisters aren't happy about this though, and tell you they will gladly help you take what you want from their brother instead. Options to accept his offer, accept their offer, claim sisters for yourself (Uros isn't allowed to breed any more), claim Uros for yourself (romance Uros, you decide if he is allowed to fuck his sisters)
* Add slave jobs for enslavable named characters
  * Hyorlyss, Fyrsia, Jhortrax
  * Must take care to change ImpFortressDialogue.resetFortress
* "Get Bathed" option (if slave is in party) (this kind of already exists?)
* Add dialogue for slaves being apprehensive while being led to the milking room
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
* Add more ways to clean companions (use soothing water on them?)
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