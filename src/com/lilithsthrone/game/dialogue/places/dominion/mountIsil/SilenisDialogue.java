package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
import com.lilithsthrone.game.character.npc.dominion.Silenis;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.GuardianDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class SilenisDialogue extends GuardianDialogue {

	private SilenisDialogue() {
		// no instantiation
	}

	private static final Response domSexResponse = new ResponseSex(
		"Sex",
		"Have dominant sex with [silenis.name].",
		true,
		false,
		new SMGeneric(
			Util.newArrayListOfValues(Main.game.getPlayer()),
			Util.newArrayListOfValues(Main.game.getNpc(Silenis.class)),
			null,
			null
		),
		AFTER_SEX,
		"""
			<p>
				It looks like you are going to have to remind [silenis.name] that [silenis.her], even though you aren't immune to seductive technique, you aren't just going to let [silenis.name] have [silenis.her] way with you. [silenis.speech(Yes, come closer. You can relieve all your stress right here...)] [silenis.she] says as you [pc.step] forwards, and you catch [silenis.her] tail trying to circle behind you. 
			</p>
			<p>
				Before [silenis.she] can gain the upper hand, you grab [silenis.her] questing tail and thrust it aside, exposing [silenis.namePos] already erect [silenis.penis] and [silenis.vaginaWetness] [silenis.vagina], ready to be breed the devious snake-herm. 
			</p>
			<p>
				[silenis.speech(Fuck... you know exactly how to treat me. Do whatever you want, I'm here to #IF(pc.hasPenis())please your delicious cock#ELSEbe your little lesbian toy#ENDIF.)] [silenis.She] moans as you bend [silenis.herHim] over and get ready to make love.
			</p>
		"""
	);

	private static final Response subSexResponse = new ResponseSex(
		"Submit",
		"",
		false,
		true,
		new SMGeneric(
			Util.newArrayListOfValues(Main.game.getNpc(Silenis.class)),
			Util.newArrayListOfValues(Main.game.getPlayer()),
			null,
			null
		),
		AFTER_SEX,
		"""
			<p>
				As [silenis.name] flicks [silenis.her] tail aside, exposing [silenis.namePos] already erect [silenis.penis] and [silenis.vaginaWetness] [silenis.vagina], you realise you have already succumbed to her seductive technique. [silenis.speech(Yes, come closer. You can relieve all your stress right here...)] [silenis.she] says as you [pc.step] forwards, and you barely notice [silenis.her] tail circling behind you. 
			</p>
			<p>
				Before you can lay your [pc.hands] on [silenis.her] perfect body, [silenis.name] crushes you in an almost unbearably strong grip, holding your [pc.bodyShape] body tight as you feel [silenis.her] cool scales slide tantalisingly #IF(pc.hasLegs())between your [pc.legs+]#ELSEdown your back and over your [pc.assSize] butt#ENDIF.
			</p>
			<p>
				[silenis.speech(Shh... I know exactly what you want. Now just hold still and I'll make you my little cocksleeve. Or struggle, I really don't mind.)] [silenis.She] hisses as [silenis.she] slithers around to press [silenis.herHim] cock up against your vulnerable rear and prepares to breed you silly.
			</p>
		"""
	) {
		@Override
		public String getTooltipText() {
			if (Silenis.hasDommedPlayer()) {
				return UtilText.parse("Let [silenis.name] take hold of your like [silenis.she] did before.");
			} else {
				return UtilText.parse("Let the beautiful snake-woman have [silenis.her] way with you.");
			}
		}
	};

	private static final Response leaveResponse = new Response("Leave", "Leave the naga alone, although [silenis.she] will no doubt be disappointed.", SilenisDialogue.EXTERIOR) {
		@Override
		public void effects() {
			Main.game.getTextStartStringBuilder().append("""
				<p>
						Deciding that now isn't the best time to be flirting with [silenis.name], you turn your back on the slithering [silenis.woman] before [silenis.she] can change your mind. You hear [silenis.name] hissing as you leave, [silenis.speech(So mean, leaving a woman all flustered like this. I'll be thinking of you...)]
				</p>
				<p>
					"Ignoring her words, you duck through the doors to [silenis.her] chamber, and exit into the vestibule.
				</p>
			""");
		}
	};

	private class SilenisNode extends DialogueNode {
		public SilenisNode() {
			super("", "", true);
		}		

		@Override
		public String getLabel() {
			return "[silenis.NamePos] chambers";
		}
	}

	public static final DialogueNode EXTERIOR = new SilenisNode() {
		@Override
		public String getContent() {
			// TODO (mark): is selenis present, does she have the required body
			return """
				<p>
					After passing rows of rooms lined with silks and scented with an animalistic, yet refined and pleasant perfume, you arrive at a small set of wooden doors, left wide open to display the glorious presence within.
				</p>
				<p>
					The [silenis.race] [silenis.name] lies on the bed, [silenis.her] long, serpentine body curled upon silken sheets. [silenis.Her] scales, manicured to a glossy sheen, glimmer invitingly in the otherwise dim room.
				</p>
				<p>
					You could either approach [silenis.her], or back away before [silenis.she] notices you.
				</p>
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				// return new ResponseCombat("Fight", "", Main.game.getNpc(Silenis.class));
				return new Response("Disturb", "Disrupt the slumbering serpent's peaceful repose.", TALK);
			} else if (index == 2) {
				if (Main.game.getPlayer().hasPerkAnywhereInTree(Perk.OBSERVANT)) {
					return new Response("Ambush", "[style.boldGood(due to your skills in observation)] you realise [silenis.name] is only pretending to be asleep, but you could use that to your advantage.", AMBUSH);
				}
				return new Response("Sneak in", "You may be able to get a closer look at the [silenis.racePos] sleek body, but <br/>[style.boldBad(it might end badly)] if [silenis.she] wakes up.", SNEAK_IN);
			}
			return null;
		}
	};

	public static final DialogueNode TALK = new SilenisNode() {
		@Override
		public String getContent() {

			// First encounter
			if (!Silenis.hasEncountered()) {
				// boolean likesMales = Main.game.getPlayer().getSexualOrientation().isAttractedToMasculine();

				return """
					<p>
						As you step over the boundary and prepare to announce yourself, you halt in your tracks as the serpent's body shifts and a [silenis.penisSize], [silenis.penisColour] [silenis.penis] and [silenis.vaginaWetness], [silenis.vaginaColour] [silenis.vagina]poke out from between [silenis.her] scales.
					</p>
					<p>
						[silenis.speech(How <i>daring</i> of you to invite yourself in like this.)]
						[silenis.she] hisses, raising [silenis.her] human half to display [silenis.her] especially well-shaped breasts as [silenis.she] looms over you.
						[silenis.speech(Ah, and you like what you see? Good, because I'd like to see a little more of you, too.')]
					</p>
					<p>
						It doesn't look like the snake-herm intends on violence. In fact, [silenis.she] beckons you to join [silenis.herHim] even as you stand across from [silenis.herHim].
					</p>
				""";
			} else {
				// TODO (mark)
				return """
					<p>
						[silenis.speech(Ah, you're back.)]
					</p>
					<p>
					</p>
					<p>
					</p>
				""";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {

			if (index == 1) {
				return new Response("Flirt", "[silenis.She] looks like [silenis.she] could use some company.", FLIRT);
			} else if (index == 2) {
				return new Response("Question", "Ask how the naga ended up living on Mount Isil.", QUESTION);
			} else if (index == 3) {
				return new Response("Insult", "Let this narcissistic snake-woman know what you think of her proposal.", INSULT);
			} else if (Silenis.isDefeated()) {
				if (index == 0) {
					return leaveResponse;
				}
				if (index == 4) {
					return domSexResponse;
				}
			} else if (Silenis.hasDommedPlayer()) {
				if (index == 4) {
					return subSexResponse;
				}
			} else {
				return leaveResponse;
			}
			return null;
		}
	};

	public static final DialogueNode SNEAK_IN = new SilenisNode() {

		@Override
		public String getContent() {
			// TODO (mark)
			return """
				<p>She grabs you up in her big, scaly tail and tries to shove her big, scaly cock in your ass. Mmm what a dream.</p>
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if (index == 1) {
				return new ResponseCombat(
					"Fight",
					"Try to break free of [silenis.namePos] coiling grip.",
					Main.game.getNpc(Silenis.class)
				);
			} else if (index == 2) {
				return subSexResponse;
			}
			return null;
		}
	};

	public static final DialogueNode FLIRT = new SilenisNode() {
		@Override
		public String getContent() {
			// TODO (mark)
			return """
					FLIRT content
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark)
			if (index == 0) {
				return leaveResponse;
			}
			return null;
		}
	};

	public static final DialogueNode AMBUSH = new SilenisNode() {
		@Override
		public String getContent() {
			// TODO (mark)
			return """
					AMBUSH content
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark)
			if (index == 0) {
				return leaveResponse;
			}
			return null;
		}
	};

	public static final DialogueNode QUESTION = new SilenisNode() {
		@Override
		public String getContent() {
			// TODO (mark)
			return """
					QUESTION content
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark)
			if (index == 0) {
				return leaveResponse;
			}
			return null;
		}
	};

	public static final DialogueNode INSULT = new SilenisNode() {
		@Override
		public String getContent() {
			// TODO (mark)
			return """
					INSULT content
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark)
			if (index == 0) {
				return leaveResponse;
			}
			return null;
		}
	};

	public static final DialogueNode LOSS = new SilenisNode() {
		@Override
		public String getContent() {
			// TODO (mark)
			return """
					LOSS content
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark)
			if (index == 0) {
				return leaveResponse;
			}
			return null;
		}
	};

	public static final DialogueNode VICTORY = new SilenisNode() {
		@Override
		public String getContent() {
			// TODO (mark)
			return """
					VICTORY content
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark)
			if (index == 0) {
				return leaveResponse;
			}
			return null;
		}
	};
}
