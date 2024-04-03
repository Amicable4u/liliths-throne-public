package com.lilithsthrone.game.dialogue.places.dominion.harpyNests;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominant;
import com.lilithsthrone.game.character.npc.dominion.HarpyDominantCompanion;
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
public class UrosDialogue extends GuardianDialogue {

	private UrosDialogue() {
		// no instantiation
	}

	private class UrosNode extends DialogueNode {
		public UrosNode() {
			super("", "", true);
		}		

		@Override
		public String getLabel() {
			return "Inner Sanctum";
		}
	}

	public static final DialogueNode EXTERIOR = new UrosNode() {
		@Override
		public String getContent() {
			return """
				<p>
					[uros.namePos] chambers are in the innermost section of the monastery.
				</p>
			""";
		}
	};

	public static final DialogueNode LOSS = new UrosNode() {
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
			// if (index == 0) {
			// 	return leaveResponse;
			// }
			return null;
		}
	};

	public static final DialogueNode VICTORY = new UrosNode() {
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
			// if (index == 0) {
			// 	return leaveResponse;
			// }
			return null;
		}
	};
}
