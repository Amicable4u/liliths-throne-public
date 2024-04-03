package com.lilithsthrone.game.dialogue.places.dominion.mountIsil;

import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class UrosDialogue {

	private UrosDialogue() {
		// no instantiation
	}

	private static class UrosNode extends DialogueNode {
		public UrosNode() {
			super("", "", true);
		}		

		@Override
		public String getLabel() {
			return "Inner Sanctum";
		}

		@Override
		public String getContent() {
			throw new Error("Expected getContent to be implemented in anonymous class");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			throw new Error("Expected getResponse to be implemented in anonymous class");
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
