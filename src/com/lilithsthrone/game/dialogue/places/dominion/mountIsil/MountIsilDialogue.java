package com.lilithsthrone.game.dialogue.places.dominion.mountIsil;

import java.util.HashMap;
import java.util.Map;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.mountIsil.MountIsilAttacker;
import com.lilithsthrone.game.character.npc.mountIsil.MountIsilNpc;
import com.lilithsthrone.game.character.npc.mountIsil.Silenis;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.AbstractDialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class MountIsilDialogue {

	public static final DialogueNode EXTERIOR = new DialogueNode("Mount Isil", "Ascend the steps to Mount Isil.", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			// return UtilText.parse("""
							
			// """);
			return """
				<p>
					At the out limits of the city, houses and businesses give way to a small set of hills, and as you approach, you see that steps hewn from grey stone have been set into the hillside, leading upwards at a gentle slope into the crisp mountain fog.
				</p>
				<p>
					Along the path and at the base are various totems and offerings. Some people have left incense burning, or lit candles in small, stone holders meant as votive offerings. It is obvious that even if they had wanted to, no one has been careless enough to leave any donations or valuable offerings.
				</p>
			""";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Ascend steps", "Ascend up to Mount Isil.", MountIsilDialogue.EXIT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MOUNT_ISIL_PLATEAU, MountIsilPlaces.EXIT);
						Main.game.getTextStartStringBuilder().append(MountIsilPlaces.EXIT.getDialogue(false).getContent());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode EXIT = new DialogueNode("", "Descend the steps down to the streets of Dominion.", false) {
		@Override
		public String getLabel() {
			if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
				return "To Dominion";
			} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
				return "To plateau";
			}
			return "Mount Isil exit";
		}

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
				return """
					<p>As you climb the last of the steps, you step out into an area that has been flattened out. Rough, square stones are arranged perfectly evenly to create a square around which modest shrines have been constructed.</p>
					<p>Below, the fog clears to reveal a set of stone stairs leading down into Dominion.</p>
				""";
			} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
				return """
					<p>Climbing more steps brings you to a set of winding paths that lead to different areas around the peak of Mount Isil. The terrain hasn't been levelled out like the plateau below, and the fog is dense enough at the altitude to make it difficult to see the edge in places.</p>
					<p>Every now and then, you think you can hear the sound of something slithering.</p>
					<p>Behind you, a set of stone stairs leads down onto the plateau.</p>
				""";
			}
			return "Mount Isil exit content";
		}

		@Override
		public String getDescription() {
			if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
				return "Descend the steps down to the streets of Dominion.";
			} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
				return "Descend down to the plateau below.";
			}
			return "Mount Isil exit tooltip";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
					return new Response(
						"Dominion", "Descend the steps down to Dominion", MountIsilDialogue.EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(MountIsilDialogue.EXTERIOR.getContent());
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_MOUNT_ISIL_ENTRANCE, false);
						}
					};
				} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
					return new Response(
						"Plateau", "Descend down to the plateau below.", MountIsilDialogue.ENTRANCE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(MountIsilDialogue.EXTERIOR.getContent());
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_MOUNT_ISIL_ENTRANCE, false);
						}
					};
				}
				// else if (getWorldType().equals(WorldType.MOUNT_ISIL_MONASTERY)) {
				// 	return new Response("Emerge", "Leave the dimly lit monastery, and return to the bright mountaintop.", MountIsilDialogue.EXIT) {
				// 		@Override
				// 		public void effects() {
				// 			Main.game.getTextStartStringBuilder().append(MountIsilDialogue.ENTRANCE.getContent());
				// 			Main.game.getPlayer().setLocation(WorldType.MOUNT_ISIL_OVERLOOK, MountIsilPlaces.ENTRANCE, false);
				// 		}
				// 	};
				// }
				throw new IllegalStateException("Unknown world type: " + Main.game.getActiveWorld().getWorldType().toString());
			}

			return null;
		}
	};

	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public String getLabel() {
			if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
				return "To Mount Isil overlook";
			} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
				return "To Mount Isil monastery";
			}
			return "Mount Isil entrance label";
		}
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
				return "Above, the terrain seems more winding and precarious, but the hand-lain path seems safe enough.";
			} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
				return "An imposing, stone door blocks the way to the monastery.";
			}
			return "Mount Isil entrance content";
		}

		@Override
		public String getDescription() {
			if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
				return "Follow the path higher.";
			} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
				return "Enter the monastery.";
			}
			return "Mount Isil entrance tooltip";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
					return new Response("Ascend", "Ascend the winding steps to the area overlooking the plateau.", MountIsilDialogue.EXIT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(MountIsilDialogue.EXIT.getContent());
							Main.game.getPlayer().setLocation(WorldType.MOUNT_ISIL_OVERLOOK, MountIsilPlaces.EXIT);
						}
					};
				} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
					return new Response("Enter", "Enter the dimly lit halls of the mountaintop monastery.", MountIsilDialogue.EXIT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(MountIsilDialogue.EXIT.getContent());
							// TODO (mark)
							// Main.game.getPlayer().setLocation(WorldType.MOUNT_ISIL_MONASTERY, MountIsilPlaces.ENTRANCE);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PATH = new DialogueNode("Mount Isil", "", false) {
		@Override
		public String getLabel() {
			return MountIsilDialogue.getLabelFromCurrentWorld();
		}

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return "A thin fog hangs just above hand-hewn stone paving the way, lending the mountain an air of tranquillity.";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SHRINE = new DialogueNode("A Shrine", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return """
				<p>
					A small shrine stands on its own. Candles set into alcoves flicker in the slight wind, and there is a basin carved out in the center. There is some barely legible text carved into a plaque under the basin.
				</p>		
			""";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Investigate", "", MountIsilDialogue.INVESTIGATE_SHRINE);
			}
			return null;
		}
	};

	public static final DialogueNode INVESTIGATE_SHRINE = new DialogueNode("A Shrine", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			// TODO (mark):
			return SHRINE.getContent() + "<p>Location: "
				+ Main.game.getPlayerCell().getLocation().toString() +
				"</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark):
			return null;
		}
	};

	public static final DialogueNode GUARDIAN = new DialogueNode("", "", false) {
		@Override
		public String getLabel() {
			return Guardian.get().getNpc().getName();
			// return "Guardian: " + Guardian.get().getNpc().getName() + " " + Main.game.getPlayerCell().getLocation().toString();
		}

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return Guardian.get().exterior.getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Guardian.get().exterior.getResponse(responseTab, index);
		}
	};

	private static Response leaveTreasure = new Response("Leave", "", MountIsilDialogue.PATH) {
		@Override
		public void effects() {
			Main.game.getTextStartStringBuilder().append( hasSearchedTreasure()
				? "Having already searched this area, you turn back the way you came."
				: "You decide whatever valuables might be hidden around this area, they aren't worth your trouble."
			);
			
			Main.game.getPlayer().setNearestLocation(WorldType.MOUNT_ISIL_OVERLOOK, MountIsilPlaces.PATH, false);
		}
	};

	private static AbstractDialogueFlagValue getTreasureFlag() {
		Vector2i location = Main.game.getPlayer().getLocation();
		return DialogueFlagValue.getDialogueFlagValueFromId("mark_treasure_overlook_" + location.getX() + "_" + location.getY());
	}

	private static boolean hasSearchedTreasure() {
		return Main.game.getDialogueFlags().hasFlag(getTreasureFlag());
	}

	private static void markTreasureSearched() {
		Main.game.getDialogueFlags().values.add(getTreasureFlag());
	}

	private static DialogueNode TREASURE_VANITY = new DialogueNode("Relic", "", false) {
		@Override
		public String getContent() {
			return """
				<p>
					You reached into the old drawers, searching for anything that might be useful. With a start, you wrench your [pc.hand] back, and find it covered in a sticky, noxious liquid. You try to wipe it off, but no matter how much you scrub, an intimate smell lingers on your body. 
				</p>
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return leaveTreasure;
			}
			return null;
		}
	};

	private static DialogueNode TREASURE_BOOKSHELF = new DialogueNode("Relic", "", false) {
		@Override
		public String getContent() {
			return """
				<p>
					You trace your fingers down the spines of the curled volumes, passing over esoteric treatises on everything from mountain horticulture to font choices for hand scribed reproductions of famous books. Finally, you spot something that might be useful!
				</p>
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return leaveTreasure;
			}
			return null;
		}
	};

	private static DialogueNode TREASURE_FLAMES = new DialogueNode("Relic", "", false) {
		@Override
		public String getContent() {
			return "<p>The glimmer you saw turns out to be a small pile of flames.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return leaveTreasure;
			}
			return null;
		}
	};

	private static DialogueNode TREASURE_SILENIS_DIARY = new DialogueNode("Relic", "", true) {
		@Override
		public String getContent() {
			return """
				<p>
					You manage to pry the package free, and unwrap the protective cloth to reveal a small, leather-bound journal.
				</p>
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Read", "Just a quick look wouldn't hurt...", SILENIS_DIARY_READ) {
					@Override
					public void effects() {
						MountIsilNpc.addFlag(Silenis.class, "journal_opened");
					}
				};
			} else if (index == 2) {
				return new Response("Leave", "Resist the urge to pry - journals are private!", MountIsilDialogue.PATH) {
					@Override
					public void effects() {
						MountIsilNpc.addFlag(Silenis.class, "journal_unopened");
						Main.game.getTextStartStringBuilder().append(
							"You decide to respect the owner's privacy, and carefully replace the journal where you found it. No doubt its owner will appreciate your concern for their privacy."
						);
						
						Main.game.getPlayer().setNearestLocation(WorldType.MOUNT_ISIL_OVERLOOK, MountIsilPlaces.PATH, false);
					}
				};
			}
			return null;
		}
	};

	private static DialogueNode SILENIS_DIARY_READ = new DialogueNode("Guardian's Diary", "", false) {
		@Override
		public String getContent() {
			return """
				<p>
					The journal's contents are written in elegant, flowing script. It reads:
				</p>
				<p><i>
					Uros approached me today. I have known what he and my sisters have been up to for some time now, but I didn't think he would just come up to me and say it. I have to admit, the thought of getting on my belly and worshipping his big, delicious cock makes me wet. I would love to just feel his cock throbbing against my scales, coiling my body around his and milking him for hours with my body...
				</i></p>
				<p><i>
					Even though he would probably never allow it, I've also been starting to fantasize about just sneaking up behind him, wrapping him up in my tail, and fucking that haughty, arrogant ass of his until he forgets all about my sisters. He really has no idea what he is missing out on, the rest of them all know that I am the most skilled in bed. After all, I gave each of them the bet orgasm of their lives, one by one, just to make sure they knew...
				</i></p>
				<p><i>
					If only Uros would slither up behind me right now and pound me against this wall. Imagine he found this diary, I would die of embarrassment, but at least I could have that divine cock at last...
				</i></p>
				<p>
					The next part of the page is stained suspiciously. You decide you've read enough of the obscene diary, and replace it where you found it.
				</p>
			""";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "Leave, armed with your new, depraved knowledge about what the inhabitants of this place engage in.", MountIsilDialogue.PATH) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.MOUNT_ISIL_OVERLOOK, MountIsilPlaces.PATH, false);
					}
				};
			}
			return null;
		}
	};

	// Map coordinates in Mount Isil Overlook to dialogue nodes
	private static Map<Vector2i, DialogueNode> treasures = new HashMap<>() {{
		put(new Vector2i(8, 5), new DialogueNode("Relic", "", true) {
			@Override
			public String getContent() {
				return """
					<p>
						In a long forgotten dressing room, you find a somewhat dusty vanity. Kiss marks, outlined in bold, red lipstick, have been left all over the mirror, and the drawers are all open. You can tell just by the smell in the air that the many makeup bottles strewn about the desk possess the lingering tinge of the arcane.
					</p>
				""";
			}

			@Override
			public Response getResponse(int responseTab, int index) {
				if (index == 1) {
					return new Response("Search", "See what you can find in the disused drawers and bottles.", TREASURE_VANITY) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(
								Main.game.getPlayer().setLust(Main.game.getPlayer().getLust() + 10)
							);
						}
					};
				} else if (index == 2) {
					return leaveTreasure;
				}
				return null;
			}
		});

		put(new Vector2i(12, 5), new DialogueNode("Relic", "", true) {
			@Override
			public String getContent() {
				return """
					<p>
						The alcove was so cramped that you almost missed it, but following the narrow path leads you to a tiny room containing a small bookshelf. You can smell the years on the pages, and you wonder whether the leathery tomes might contain some useful knowledge about this place.
					</p>
				""";
			}

			@Override
			public Response getResponse(int responseTab, int index) {
				if (index == 1) {
					return new Response("Search", "See what you can find in the dusty bookshelf.", TREASURE_BOOKSHELF) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(
								Main.game.getPlayer().addItem(
									Main.game.getItemGen().generateItem(
										ItemType.getLoreBook(MountIsilNpc.SPECIES)
									)
								)
							);
						}
					};
				} else if (index == 2) {
					return leaveTreasure;
				}
				return null;
			}
		});

		put(new Vector2i(8, 3), new DialogueNode("Relic", "", true) {
			@Override
			public String getContent() {
				return """
					<p>
						You see something glimmer on the path ahead, but this part of the mountain receives so little light that you can't tell what it is. 
					</p>
				""";
			}

			@Override
			public Response getResponse(int responseTab, int index) {
				if (index == 1) {
					NPC attacker = MountIsilAttacker.initialiseAttacker();
					return new Response("Search", "Prepare yourself, and peer ahead.", attacker.getEncounterDialogue()) {
						@Override
						public void effects() {
							markTreasureSearched();
						}
					};
				} else if (index == 2) {
					return leaveTreasure;
				}
				return null;
			}
		});

		put(new Vector2i(12, 3), new DialogueNode("Relic", "", true) {
			@Override
			public String getContent() {
				return """
					<p>
						You see something glimmer on the path ahead, but this part of the mountain receives so little light that you can't tell what it is.
					</p>
				""";
			}

			@Override
			public Response getResponse(int responseTab, int index) {
				if (index == 1) {
					return new Response("Search", "Prepare yourself, and peer ahead.", TREASURE_FLAMES) {
						@Override
						public void effects() {
							markTreasureSearched();
							Main.game.getTextEndStringBuilder().append(
								Main.game.getPlayer().incrementMoney(
									(int) Math.round(100 + 200 * Math.random())
								)
							);
						}
					};
				} else if (index == 2) {
					return leaveTreasure;
				}
				return null;
			}
		});

		put(new Vector2i(8, 1), new DialogueNode("Relic", "", true) {
			@Override
			public String getContent() {
				return """
					<p>
						You see something glimmer on the path ahead, but this part of the mountain receives so little light that you can't tell what it is.
					</p>
				""";
			}

			@Override
			public Response getResponse(int responseTab, int index) {
				if (index == 1) {
					return new Response("Search", "Prepare yourself, and peer ahead.", TREASURE_FLAMES) {
						@Override
						public void effects() {
							markTreasureSearched();
							Main.game.getTextEndStringBuilder().append(
								Main.game.getPlayer().incrementMoney(
									(int) Math.round(150 + 300 * Math.random())
								)
							);
						}
					};
				} else if (index == 2) {
					return leaveTreasure;
				}
				return null;
			}
		});

		put(new Vector2i(12, 1), new DialogueNode("Relic", "", true) {
			@Override
			public String getContent() {
				return """
					<p>
						You take a moment to survey the surrounding area, and you are just about to conclude that there is nothing of interest here. However, at the last moment you spot a small package nestled in the cleft of two stones.
					</p>
				""";
			}

			@Override
			public Response getResponse(int responseTab, int index) {
				if (index == 1) {
					return new Response("Search", "Try to fish out the wedged package.", TREASURE_SILENIS_DIARY) {
						@Override
						public void effects() {
							markTreasureSearched();
						}
					};
				} else if (index == 2) {
					return leaveTreasure;
				}
				return null;
			}
		});
	}};

	private static DialogueNode getTreasure() {
		return treasures.get(Main.game.getPlayerCell().getLocation());
	}

	public static final DialogueNode TREASURE = new DialogueNode("", "", false) {
		@Override
		public String getLabel() {
			if (getTreasure() == null) {
				return "Relic " + Main.game.getPlayerCell().getLocation().toString();
			}
			return getTreasure().getLabel();
		}

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			if (getTreasure() == null) {
				return "<p>Location: " + Main.game.getPlayerCell().getLocation().toString() + "</p>";
			}
			return getTreasure().getContent();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (getTreasure() == null) {
				return null;
			}
			if (hasSearchedTreasure()) {
				if (index == 1) {
					return new Response("Search", "[style.colourBad(You have already searched this location.)]", null);
				} else if (index == 2) {
					return leaveTreasure;
				}
				return null;
			}
			return getTreasure().getResponse(responseTab, index);
		}
	};

	private static AbstractWorldType getWorldType() {
		return Main.game.getActiveWorld().getWorldType();
	}

	private static String getLabelFromCurrentWorld() {
		if (getWorldType().equals(WorldType.MOUNT_ISIL_PLATEAU)) {
			return "Mount Isil plateau";
		} else if (getWorldType().equals(WorldType.MOUNT_ISIL_OVERLOOK)) {
			return "Mount Isil overlook";
		}
		return "Mount Isil label";
	}
}
