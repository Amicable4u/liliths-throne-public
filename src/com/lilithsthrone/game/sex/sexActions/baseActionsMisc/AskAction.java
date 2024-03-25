package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class AskAction {

	private static GameCharacter asker;
	private static GameCharacter responder;
	private static SexType desiredSexType;
	
	public static final DialogueNode ASK_MENU = new DialogueNode("Request", "", true) {
		@Override
		public String getLabel() {
			// TODO (mark): add the name of the requested action?
			return "Request";
		}

		@Override
		public String getContent() {
			StringBuilder sb = UtilText.nodeContentSB;
			sb.setLength(0);

			sb.append("<div class='container-full-width'>");
				sb.append("<p style='text-align:center;'>How will you respond to ");
					sb.append(UtilText.parse(responder,
					"<span style=\"color:"+responder.getFemininity().getColour().toWebHexString()+";\">[npc.Name]"));
					sb.append("'s</span> request?");
				sb.append("</p>");
			sb.append("</div>");
		
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				boolean isPossible = true; // TODO (mark)
				boolean isPositionChangeRequired = false;
				return new ResponseEffectsOnly("Accept", isPositionChangeRequired ? "Move into position (may disrupt ongoing actions)." : "") {
					@Override
					public boolean isSexPositioningHighlight() {
						return isPositionChangeRequired;
					}

					@Override
					public void effects() {
						Main.mainController.openPhone();
						UtilText.nodeContentSB.setLength(0);

						if (isPositionChangeRequired) {
							if (Main.sex.isDom(asker)) {
								UtilText.nodeContentSB.append("You manoeuvre [npc.name] into [npc.her] new place, ready to satisfy [npc.her] wishes.");
							} else {
								UtilText.nodeContentSB.append("You manoeuvre yourself into your new place, ready to fulfil [npc.namePos] wishes.");
							}
							// TODO (mark): actually change the position and start the new action
	
							// SexActionUtility.POSITION_SELECTION.applyEffects();
							// Main.sex.endSexTurn(SexActionUtility.POSITION_SELECTION);
							// Main.game.setContent(new Response("", "", Main.sex.SEX_DIALOGUE));
						} else {
							if (Main.sex.isDom(asker)) {
								SexAreaInterface performingArea = desiredSexType.getPerformingSexArea();
								SexAreaInterface targetArea = desiredSexType.getTargetedSexArea();
								Main.sex.applyOngoingAction(responder, performingArea, asker, targetArea, true);
							} else {
								// Give the dom impetus to do it
								responder.generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(desiredSexType));
							}
						}

						UtilText.nodeContentSB.setLength(0);
						UtilText.nodeContentSB.append(
							Main.sex.isDom(asker)
								? "[npc.speech(Sounds like fun.)]"
								: "[npc2.speech(Mmm, I can't wait.)]"
						);
					}
				};
				
			} else if(index==2) {
				// TODO (mark): give dom a chance to override this preference, after the sub already asked
				return new ResponseEffectsOnly("Decline", "");
			}
			
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final SexAction PLAYER_ASK = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "Ask";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				return "Give [npc2.name] a questioning look, to ask what [npc2.he] wants you to do.";
			}
			return "Ask [npc2.name] what [npc2.he] wants you to do.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			// TODO (mark): Allow NPCs to ask
			return Main.sex.getCharacterPerformingAction().isPlayer() && Main.sex.getAllParticipants(false).size() > 0;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			asker = Main.sex.getCharacterPerformingAction();
			responder = Main.sex.getCharacterTargetedForSexAction(this);
			SexPace pace = Main.sex.getSexPace(responder);

			if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
				// TODO (mark): improve dialogue to account for blindfolds
				UtilText.nodeContentSB.append(
					UtilText.returnStringAtRandom(
						"As [npc.namePos] mouth is blocked, [npc.she] [npc.verb(look)] at [npc2.name] inquisitively, in an attempt to find out what [npc2.she] wants from [npc.herHim].",
						"With [npc.her] mouth being blocked, [npc.name] [npc.verb(fall)] back on giving [npc2.name] a suggestive look with [npc.her] [npc.eyes] in order to find out what [npc2.name] might want next."
					) +"<br/><br/>"
				);
			} else {
				switch(pace) {
					case DOM_GENTLE:
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(What do you want me to do next?)]",
								"[npc.speech(Is there something I can do to make you feel good?)]"
							) +"<br/><br/>"
						);
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(Please tell me what you want me to do!)]",
								"[npc.speech(I'll do anything you want, just tell me how you want to use me!)]"
							) +"<br/><br/>"
						);
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
							"You lean closer to [npc2.name] and "
							+ UtilText.returnStringAtRandom(
								"[npc.verb(ask)], [npc.speech(What do you want me to do, [npc2.name]?)]",
								"[npc.verb(say)], [npc.speech(Tell me what you want next.)]"
							) +"<br/><br/>"
						);
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(Speak up, you filthy whore! Beg for how you want to be used!)]",
								"[npc.speech(Tell me what you want, you fucking slut! And don't make me ask again!)]"
							) +"<br/><br/>"
						);
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(What turns you on? Tell me or you're going to find out what turns me on instead!)]",
								"[npc.speech(Tell me what you want, and don't make me ask again!)]"
							) +"<br/><br/>"
						);
						break;
					default:
						UtilText.nodeContentSB.append("Unknown ask pace.<br/><br/>");
						break;
				}
			}
			
			getAskResponse(pace);
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.mainController.openPhone(ASK_MENU);
		}
	};

	/**
	 * Appends the response to Ask action to UtilText.nodeContentSB
	 * @param asker character who asked what responder wanted
	 * @param responder character who asked what responder wanted
	 * @param sexPace sex pace (from responder's point of view)
	 * @return the responder's response as an enriched string
	 */
	private static void getAskResponse(SexPace sexPace) {
		// TODO (mark): separate rape play and SUB_RESISTING cases

		Map<SexType, Integer> preferences;
		if (Main.sex.isInForeplay(responder)) {
			preferences = responder.getForeplayPreferences(false, asker, null);
		} else {
			preferences = responder.getMainSexPreferences(false, asker, null);
		}

		// TODO (mark): just use desiredSexType instead of having a local as well
		SexType desired = Util.getRandomObjectFromWeightedMap(preferences);
		desiredSexType = desired;
		String pname = desired.getPerformingSexArea().getName(responder);
		String tname = desired.getTargetedSexArea().getName(asker);

		// Main.sex.isConsensual();
		// Main.sex.get

		if (desired.isPenetrating()) {
			// penetrative sex (tentacle, tail, penis, finger, tongue)
			// vagina, anus, mouth, spinneret, (in) nipples, (between) thighs, (between) breasts, (between) breasts_crotch, armpits
			SexAreaPenetration performingArea = (SexAreaPenetration)desired.getPerformingSexArea();
			SexAreaOrifice targetArea = (SexAreaOrifice)desired.getTargetedSexArea();

			String penetrationPreposition = switch (targetArea) {
				case ARMPITS -> "in";
				default -> targetArea.isPlural() ? "in" : "in-between";
			};

			// responder lick asker (armpits, ass-cheeks, thighs, breasts)
			if (!targetArea.isInternalOrifice() && performingArea == SexAreaPenetration.TONGUE) {

				String orifice = targetArea == SexAreaOrifice.ASS ? "ass" : targetArea.getName(asker, false);

				switch (sexPace) {
					case DOM_GENTLE, DOM_NORMAL -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Hmm, how about I lick your " + orifice + " up and down?)]",
								"[npc2.speech(Let me bury my tongue " + penetrationPreposition + " " + targetArea.getDemonstrativePronoun() + " " + tname + ".)]"
							) +"<br/><br/>"
						);
					case DOM_ROUGH -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Be a good bitch and give me your " + orifice +  " so I can get a good taste.)]",
								"[npc2.speech(I hope you're not ticklish, slut. I want to lick your " + orifice + " nice and slow.)]"
							)
						);
					case SUB_NORMAL -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Please, I wanna lick your " + orifice + "!)]",
								"[npc2.speech(Can I use my tongue to make your " + orifice + " feel good?)]"
							) +"<br/><br/>"
						);
						case SUB_EAGER -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Will you let me shove my tongue " + penetrationPreposition + " your " + orifice + "?)]",
								"[npc2.speech(Please use my worthless tongue to clean your " + orifice + ", I promise I'll make you feel [style.italicsMinorGood(really)] good!)]"
							) +"<br/><br/>"
						);
					case SUB_RESISTING -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.name] cries out as [npc2.she] tries to throw you off. [npc2.speech(Fine! But I'll only use my tongue. Will that satisfy you?)]",
								"[npc2.speech(Please, stop! If you're going to keep raping me, just let me lick your " + orifice + " and finish quickly!.)]"
							) +"<br/><br/>"
						);
					default -> UtilText.nodeContentSB.append("Unknown respond tongue penetrating pace.<br/><br/>");
				}
			} else {
				// responder penetrate asker
				String actionDescription = "my " + pname + " " + penetrationPreposition + " your " + tname;

				// TODO (mark): reword some of the "rubbing" type actions that are technically considered "penetration" (or move them down below)
				// if (!targetArea.takesPenileVirginity) {

				// }
	
				switch (sexPace) {
					case DOM_GENTLE, DOM_NORMAL -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Hmm, how about I put " + actionDescription + ")]",
								"[npc2.speech(I think we'd both enjoy it if I shoved " + actionDescription + ")]"
							) +"<br/><br/>"
						);
					case DOM_ROUGH -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Shut up! Maybe you'll be lucky enough to take " + actionDescription +  ".)]",
								"[npc2.speech(Aren't you an eager little whore? Want to take " + actionDescription + "?)]"
							)
						);
					case SUB_NORMAL -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Please, I wanna put " + actionDescription + "!)]",
								"[npc2.speech(Can I put " + actionDescription + "?)]"
							) +"<br/><br/>"
						);
					case SUB_EAGER -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Can this worthless slut put [npc2].her " + pname + " " + penetrationPreposition + " your " + tname + "?)]",
								"[npc2.speech(Please let me put " + actionDescription + ", I promise I'll make you feel [style.italicsMinorGood(really)] good!)]"
							) +"<br/><br/>"
						);
					case SUB_RESISTING -> UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc2.speech(Get off me, or I'll put " + actionDescription + "!)]",
								"[npc2.speech(Please, don't make me keep going! But if you're going to keep raping me, at least let me put " + actionDescription + ".)]"
							) +"<br/><br/>"
						);
					default -> UtilText.nodeContentSB.append("Unknown respond penetrating pace.<br/><br/>");
				}
			}
		} else if (desired.isBeingPenetrated()) {
			// penetrative sex (tentacle, tail, penis, finger, tongue, thighs)
			// vagina, anus, mouth, spinneret, (in) nipples, (between) thighs, (between) breasts, (between) breasts_crotch, armpits
			SexAreaOrifice performingArea = (SexAreaOrifice)desired.getPerformingSexArea();
			SexAreaPenetration targetArea = (SexAreaPenetration)desired.getTargetedSexArea();
			String penetrationPreposition = switch (performingArea) {
				case ARMPITS -> "in";
				default -> targetArea.isPlural() ? "in" : "in-between";
			};
			String actionDescription = "your " + tname + " " + penetrationPreposition + " my " + pname;

			switch (sexPace) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"[npc2.speech(You've been a good [npc2.girl], how about I let you fuck my " + pname + " with your " + tname + "?)]",
							"[npc2.speech(I want to feel " + actionDescription + ".)]"
						) +"<br/><br/>"
					);
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"[npc2.speech(You want to please me that badly, you whore? Fine, I'll let you put " + actionDescription + ", but you'd better do your best to please me.)]",
							"[npc2.speech(I'll give you the honour of putting " + actionDescription + ", you worthless slut.)]"
						)
					);
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"[npc2.speech(Please put " + actionDescription + "! It would be so hot...)]",
							"[npc2.speech(It would [style.italicsMinorGood(really)] turn me on if you fucked my " + pname + " with your " + tname + ".)]"
						) +"<br/><br/>"
					);
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"[npc2.speech(Fuck yeah! Shove " + actionDescription + ", make me your slut!)]",
							"[npc2.speech(What do I want? I want " + actionDescription + "! I want " + (targetArea.isPlural() ? "them" : "it") +  " so bad!)]"
						) +"<br/><br/>"
					);
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"[npc2.speech(Fuck off! At least put " + actionDescription + " if you're going to rape me like this!)]",
							"[npc2.speech(Fine! You can put your ugly " + tname + " " + penetrationPreposition + " my" + pname + " if it will get this over with faster.)]"
						) +"<br/><br/>"
					);
					break;
			
				default:
					break;
			}

		} else {
			// groping
			// footjob
			// grinding
			// masturbation
			// SPANK_ASS
      // SLAP_ASS
      // SLAP_FACE
      // SPIT_FACE
      // CHOKE
			// CARESS_CHEEK
			// KISS_CHEEK
			// STROKE_BELLY
			// PLAYER_SELF_GROW_PENIS
			// PLAYER_GET_PARTNER_TO_GROW_PENIS
			// GENERIC_DENY
			// PLAYER_STOP_ALL_PENETRATIONS
			// PLAYER_STOP_ALL_PENETRATIONS_SELF
			// ROPE_BOUND
			// CHAINS_BOUND
			// SPINNERET_SPIN_CONDOM_SELF
			// SPINNERET_SPIN_CONDOM_PARTNER
			// SPINNERET_SPIN_SEAL_VAGINA
			// SPINNERET_SPIN_SEAL_ANUS
			// SPINNERET_SPIN_SEAL_NIPPLES
			// SPINNERET_SPIN_SEAL_MOUTH
			// SPINNERET_COCOON_PARTNER
			// SPINNERET_COCOON_PARTNER_REMOVE
			// TENTACLES_RESTRICT_PARTNER
			// TENTACLES_RELEASE_PARTNER
			// TENTACLE_SQUEEZE
			// TENTACLE_MASSAGE
			// TENTACLE_BOUND
			// WITCH_SEAL_CAST
			// WITCH_SEALED
			// WITCH_SEAL_BREAK
			// TAIL_CONSTRICTION_RESTRICT_PARTNER
			// TAIL_CONSTRICTION_RELEASE_PARTNER
			// TAIL_SQUEEZE
			// TAIL_MASSAGE
			// TAIL_CONSTRICTED
			// OVIPOSITOR_PENIS_EGG_LAYING
			// OVIPOSITOR_CLIT_EGG_LAYING
			// OVIPOSITOR_TAIL_EGG_LAYING
			// OVIPOSITOR_TAIL_EGG_LAYING_SELF

			String actionDescription = "Can we use my " + pname + " and your " + tname + "?";
			UtilText.nodeContentSB.append(actionDescription);

			// switch (sexPace) {
			// 	case DOM_GENTLE:
			// 	case DOM_NORMAL:
			// 		UtilText.nodeContentSB.append(
			// 			UtilText.returnStringAtRandom(
			// 				"[npc.speech()]",
			// 				"[npc.speech()]"
			// 			) +"<br/><br/>"
			// 		);
			// 		break;
			// 	case DOM_ROUGH:
			// 		UtilText.nodeContentSB.append(
			// 			UtilText.returnStringAtRandom(
			// 				"[npc.speech()]",
			// 				"[npc.speech()]"
			// 			)
			// 		);
			// 		break;
			// 	case SUB_NORMAL:
			// 		UtilText.nodeContentSB.append(
			// 			UtilText.returnStringAtRandom(
			// 				"[npc.speech()]",
			// 				"[npc.speech()]"
			// 			) +"<br/><br/>"
			// 		);
			// 	case SUB_EAGER:
			// 		UtilText.nodeContentSB.append(
			// 			UtilText.returnStringAtRandom(
			// 				"[npc.speech()]",
			// 				"[npc.speech()]"
			// 			) +"<br/><br/>"
			// 		);
			// 		break;
			// 	case SUB_RESISTING:
			// 		UtilText.nodeContentSB.append(
			// 			UtilText.returnStringAtRandom(
			// 				"[npc.speech()]",
			// 				"[npc.speech()]"
			// 			) +"<br/><br/>"
			// 		);
			// 		break;
			
			// 	default:
			// 		break;
			// }
		}
	}	
}
