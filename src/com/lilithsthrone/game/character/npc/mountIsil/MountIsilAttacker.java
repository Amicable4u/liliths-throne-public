package com.lilithsthrone.game.character.npc.mountIsil;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.mountIsil.MountIsilNpc;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.mountIsil.MountIsilAttackerDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.MountIsilPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class MountIsilAttacker extends NPC {

	public MountIsilAttacker() {
		this(Gender.getGenderFromUserPreferences(Femininity.FEMININE), false);
	}
	
	public MountIsilAttacker(Gender gender) {
		this(gender, false);
	}
	
	public MountIsilAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public MountIsilAttacker(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				4,
				null, null, null,
				new CharacterInventory(10), WorldType.MOUNT_ISIL_OVERLOOK, MountIsilPlaces.TREASURE, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 10 to 20:
			setLevel(Util.random.nextInt(10) + 10);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
			
			for(Entry<AbstractSubspecies, SubspeciesSpawnRarity> entry : MountIsilPlaces.cultistPopulation.getSpecies().entrySet()) {
				if(entry.getKey().getRace()==Race.getRaceFromId("NoStepOnSnek_snake")) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*entry.getValue().getChanceMultiplier()), gender, entry.getKey(), subspeciesMap);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, subspeciesMap, true, false);

			if(Math.random()<Main.getProperties().halfDemonSpawnRate/100f) { // Half-demon spawn rate
				this.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(this, this.getGender(), this.getBody().getFleshSubspecies(), true), true);
			}
			
			setName(Name.getRandomTriplet(MountIsilNpc.SPECIES));
			this.setPlayerKnowsName(false);

			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			this.setHistory(Occupation.NPC_MOUNT_ISIL_CULTIST);
			
			// Add fetishes:
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			if(hasFetish(Fetish.FETISH_CUM_ADDICT) && Math.random() < 0.1) {
				Main.game.getCharacterUtils().applyDirtiness(this);
			}
			
			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void setBodyFromSubspeciesPreference(Gender gender, Map<AbstractSubspecies, Integer> subspeciesMap, boolean additionalSetups, boolean includeHumanChance) {
		if(gender.isFeminine()) {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		} else {
			for(Entry<AbstractSubspecies, FurryPreference> entry : Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().entrySet()) {
				if(entry.getValue() == FurryPreference.HUMAN) {
					subspeciesMap.remove(entry.getKey());
				}
			}
		}
		
		int total = 0;
		for(Integer i : subspeciesMap.values()) {
			total += i;
		}

		AbstractSubspecies species = MountIsilNpc.SPECIES;
		if(!subspeciesMap.isEmpty() && total>0) {
			species = Util.getRandomObjectFromWeightedMap(subspeciesMap);
		}
		setBody(gender, species, RaceStage.LESSER, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return UtilText.parse(this, "Having run afoul of the law, [npc.nameIsFull] now a slave, and is no more than [npc.her] owner's property.");
			
		} else if(this.getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_ONE_FRIENDLY)) {
			return UtilText.parse(this, "[npc.nameIsFull] furious that you are despoiling the sacred ground of [npc.her] deity. It seems as though [npc.sheIs] willing to remove you by any means possible, even if that leads to a fight...");
			
		} else {
			return UtilText.parse(this, "While your first encounter with [npc.name] was a hostile one, you've since managed to become friends with the devour [npc.race], and you're sure to always receive a warm welcome from [npc.herHim].");
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}

	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}

	public static NPC initialiseAttacker() {
		NPC attacker = new MountIsilAttacker();
		Main.game.setActiveNPC(attacker);
		// TODO (mark): should the attacker appear in the current location?
		// attacker.setLocation(Main.game.getPlayer(), true);
		try {
			Main.game.addNPC(Main.game.getActiveNPC(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return attacker;
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return MountIsilAttackerDialogue.CULTIST_ATTACK;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", MountIsilAttackerDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", MountIsilAttackerDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
}
