package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Mark
 */
public class CheatDialogue {
	public static final DialogueNode SPELL = new DialogueNode("Cheat menu", "Cheats", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("<p>Click the spell school to gain a spell upgrade point.</p>");
			
			return UtilText.nodeContentSB.toString();
		}
	
		@Override
		public Response getResponse(int responseTab, int index) {
			PlayerCharacter player = Main.game.getPlayer();
			if (index == 0) {
				return new Response("Back", "Return to the previous screen.", CheatDialogue.MENU);
			}
		
			if (index == 1) {
				return new Response("Earth", UtilText.parse(player, "View [npc.namePos] spells and upgrades in the school of Earth."), CheatDialogue.SPELL) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementSpellUpgradePoints(SpellSchool.EARTH, 1);
					}
				};
			} else if (index == 2) {
				return new Response("Water", UtilText.parse(player, "View [npc.namePos] spells and upgrades in the school of Water."), CheatDialogue.SPELL) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementSpellUpgradePoints(SpellSchool.WATER, 1);
					}
				};
			} else if (index == 3) {
				return new Response("Fire", UtilText.parse(player, "View [npc.namePos] spells and upgrades in the school of Fire."), CheatDialogue.SPELL) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementSpellUpgradePoints(SpellSchool.FIRE, 1);
					}
				};
			} else if (index == 4) {
				return new Response("Air", UtilText.parse(player, "View [npc.namePos] spells and upgrades in the school of Air."), CheatDialogue.SPELL) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementSpellUpgradePoints(SpellSchool.AIR, 1);
					}
				};
			} else if (index == 5) {
				return new Response("Arcane", UtilText.parse(player, "View [npc.namePos] spells and upgrades in the school of Arcane."), CheatDialogue.SPELL) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementSpellUpgradePoints(SpellSchool.ARCANE, 1);
					}
				};
			}
			// TODO: implement unlocking misc spells
			// else if (index == 6) {
			// 	return new Response("Misc", UtilText.parse(player, "View [npc.namePos] miscellaneous spells."), CheatDialogue.MISC_SPELL);
			// }

			return null;
		}
	};

	public static final DialogueNode MENU = new DialogueNode("Cheat menu", "Cheats", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			
			UtilText.nodeContentSB.append("<p>Here is the cheat menu. Make sure you only use these to do very dirty things.</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			// if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
			// 	if(index==0) {
			// 		return "Cheats";
			// 	} else if(index==1) {
			// 		return "[style.colourYellowLight(Eggs)]";
			// 	}
			// }
			return super.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response(
							"<span style='color:" + PresetColour.BASE_GOLD.toWebHexString() + ";'>Give 1k</span>",
							"Give the player 1000 money.", CheatDialogue.MENU){
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(1000);
						}
					};
				} else if (index == 2) {
					return new Response(
							"Get spell",
							"Gain a spell upgrade point.", CheatDialogue.SPELL);
				}
			} else if(responseTab==1) {
				// TODO
			}
			
			if (index == 0) {
				return new ResponseEffectsOnly("Back", "Exit the cheat menu.") {
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
}
