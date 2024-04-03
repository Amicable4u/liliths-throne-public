package com.lilithsthrone.game.dialogue.places.dominion.mountIsil;

import com.lilithsthrone.game.character.npc.dominion.mountIsil.Silenis;
import com.lilithsthrone.game.character.npc.dominion.mountIsil.Uros;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.SilenisDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.MountIsilPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
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
				return new Response("Ascend steps", "Ascend up to Mount Isil.", MountIsilDialogue.EXIT){
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MOUNT_ISIL_PLATEAU, MountIsilPlaces.EXIT, false);
						Main.game.getTextStartStringBuilder().append(MountIsilPlaces.EXIT.getDialogue(false).getContent());
					}
				};

			} else {
				return null;
			}
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
			return "Below, the fog clears to reveal a set of stone stairs leading down into Dominion.";
			// return UtilText.parseFromXMLFile("places/dominion/mountIsil/generic", "OUTSIDE");
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
				return new Response("Dominion", "Descend the steps down to Dominion", MountIsilDialogue.EXTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/mountIsil/generic", "EXIT"));
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_MOUNT_ISIL_ENTRANCE, false);
					}
				};

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

	public static final DialogueNode TREASURE = new DialogueNode("Relics", "", false) {
		@Override
		public String getLabel() {
			// TODO (mark):
			return "Relic: "
			+ Main.game.getPlayerCell().getLocation().toString();
		}

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			// TODO (mark):
			return "<p>Location: " + Main.game.getPlayerCell().getLocation().toString() + "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			// TODO (mark):
			return null;
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
