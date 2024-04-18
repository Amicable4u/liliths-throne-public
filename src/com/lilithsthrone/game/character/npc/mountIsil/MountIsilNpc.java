package com.lilithsthrone.game.character.npc.mountIsil;

import java.time.Month;
import java.util.Map;
import java.util.Map.Entry;
import java.io.File;

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
import com.lilithsthrone.utils.Util;
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

	public static AbstractSubspecies SPECIES;

	// TODO (mark): this is an ugly hack to get around the modded bodies not being available at the time npcs are initialized
	// This should actually be fixed by ensuring that static block in Subspecies.java executes before the various NPC subclasses are initialized
	static {
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "subspecies", null);
		outer: for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
					String id = innerEntry.getKey().replaceAll("_race", "");
					if (id.equals("NoStepOnSnek_snake_subspecies_snake")) {
						try {
							AbstractSubspecies subspecies = new AbstractSubspecies(innerEntry.getValue(), entry.getKey(), true) {};
							SPECIES = subspecies;
							break outer;
						} catch(Exception ex) {
							System.err.println("Loading res/race subspecies failed at 'Subspecies'. File path: "+innerEntry.getValue().getAbsolutePath());
							System.err.println("Actual exception: ");
							ex.printStackTrace(System.err);
						}
					}
				}
			}
		}
		if (SPECIES == null) {
			System.err.println("Mount Isil subspecies 'NoStepOnSnek_snake_subspecies_snake' could not be found.");
			for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
				for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
					if(Util.getXmlRootElementName(innerEntry.getValue()).equals("subspecies")) {
						String id = innerEntry.getKey().replaceAll("_race", "");
						System.err.println("  " + id);
					}
				}
			}
		}
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

	private static AbstractDialogueFlagValue getDialogueFlagValue(Class npcClass, String flag) {
		return DialogueFlagValue.getDialogueFlagValueFromId("mark_" + npcClass.getName().toLowerCase() + "_" + flag);
	}
	
	public static boolean hasFlag(Class npcClass, String flag) {
		return Main.game.getDialogueFlags().hasFlag(getDialogueFlagValue(npcClass, flag));
	}

	public static void addFlag(Class npcClass, String flag) {
		Main.game.getDialogueFlags().values.add(getDialogueFlagValue(npcClass, flag));
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