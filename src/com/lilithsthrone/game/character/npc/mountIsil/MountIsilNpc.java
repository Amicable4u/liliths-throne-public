package com.lilithsthrone.game.character.npc.dominion.mountIsil;

import java.time.Month;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.AbstractDialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public abstract class MountIsilNpc extends NPC {
	public MountIsilNpc(
		boolean isImported,
		NameTriplet nameTriplet,
		String surname,
		String description,
		int age,
		Month birthMonth,
		int birthDay,
		int level,
		Gender startingGender,
		AbstractSubspecies startingSubspecies,
		RaceStage stage,
		CharacterInventory inventory,
		AbstractWorldType worldLocation,
		AbstractPlaceType startingPlace,
		boolean addedToContacts,
		NPCGenerationFlag... generationFlags
	) {
		super(isImported, nameTriplet, surname, description, age, birthMonth, birthDay, level, startingGender, startingSubspecies, stage, inventory, worldLocation, startingPlace, addedToContacts, generationFlags);
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel() { }
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isExtendedWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	public int getEscapeChance() {
		return 25;
	}
	
	public static boolean hasFlag(Class npcClass, String flag) {
		return Main.game.getDialogueFlags().hasFlag(getDialogueFlagValue(npcClass, flag));
	}

	public static void addFlag(Class npcClass, String flag) {
		Main.game.getDialogueFlags().values.add(getDialogueFlagValue(npcClass, flag));
	}

	private static AbstractDialogueFlagValue getDialogueFlagValue(Class npcClass, String flag) {
		return DialogueFlagValue.getDialogueFlagValueFromId("mark_" + npcClass.getName().toLowerCase() + "_" + flag);
	}

	public static boolean hasEncountered(Class npcClass) {
		return hasFlag(npcClass, MountIsilFlag.ENCOUNTERED);
	}

	public static boolean isDefeated(Class npcClass) {
		return hasFlag(npcClass, MountIsilFlag.DEFEATED);
	}

	public static boolean hasDommedPlayer(Class npcClass) {
		return hasFlag(npcClass, MountIsilFlag.DOMMED_PLAYER);
	}
}