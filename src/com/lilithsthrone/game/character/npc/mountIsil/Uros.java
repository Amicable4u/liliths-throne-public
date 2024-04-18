package com.lilithsthrone.game.character.npc.mountIsil;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.MountIsilPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.UrosDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.ParserTarget;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class Uros extends MountIsilNpc {

	public Uros() {
		this(false);
	}
	
	public Uros(boolean isImported) {
		super(
			isImported,
			new NameTriplet("Uros", "Uroth", "Urileth"),
			"Lithnaga",
			"The object of worship for worshippers that visit Mount Isil, Uros is used to being adored for his rare physical appearance and stunning, midnight-black scales. He is also accustomed to dealing with those who refuse to bow down to his majesty.",
			22, Month.JANUARY,
			20,
			26,
			Gender.M_P_MALE,
			MountIsilNpc.SPECIES,
			RaceStage.LESSER,
			new CharacterInventory(30),
			// TODO (mark): place Uros somewhere
			WorldType.EMPTY,
			PlaceType.GENERIC_HOLDING_CELL,
			// WorldType.MOUNT_ISIL_OVERLOOK,
			// MountIsilPlaces.UROS,
			true
		);
	}

	// static {
	// 	ParserTarget.addAdditionalParserTarget("uros", Main.game.getNpc(Uros.class));
	// }

	public static boolean hasEncountered() {
		return MountIsilNpc.hasEncountered(Uros.class);
	}

	public static boolean isDefeated() {
		return MountIsilNpc.isDefeated(Uros.class);
	}

	public static boolean hasDommedPlayer() {
		return MountIsilNpc.hasDommedPlayer(Uros.class);
	}

	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		// if(Main.isVersionOlderThan(Game.loadingVersion, "0.5.0")) {
		// 	resetBodyAfterVersion_5_0();
		// }
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.LEWD_KNOWLEDGE);
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		this.addSpecialPerk(Perk.FEROCIOUS_WARRIOR);
		this.addSpecialPerk(Perk.JOB_NPC_MOUNT_ISIL_GUARDIAN); // TODO (mark)
		this.addSpecialPerk(Perk.OBJECT_OF_DESIRE);
		this.addSpecialPerk(Perk.PHYSICAL_BASE);
		this.addSpecialPerk(Perk.PHYSIQUE_BOOST);
		this.addSpecialPerk(Perk.PHYSICAL_DAMAGE);
		this.addSpecialPerk(Perk.PHYSICAL_DEFENCE);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_MOUNT_ISIL_GUARDIAN); // TODO (mark)
	
			this.addFetish(Fetish.FETISH_PENIS_GIVING);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_NON_CON_DOM);
			this.addFetish(Fetish.FETISH_DENIAL);
			this.addFetish(Fetish.FETISH_INCEST);
			
		}
		
		// Body:

		// Core:
		this.setHeight(180);
		this.setFemininity(Femininity.MASCULINE.getMedianFemininity());
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.getBodyCoveringTypeFromId("NoStepOnSnek_snake_eye"), PresetColour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.SNAKE_SCALES, PresetColour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_PALE), true);
		// TODO (mark)
		// this.setSkinCovering(new Covering(BodyCoveringType.HARPY_SKIN, PresetColour.COVERING_GREEN_DARK), false);

		// TODO (mark)
		// this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, PresetColour.COVERING_BLACK), true);
		// this.setHairLength(HairLength.THREE_SHOULDER_LENGTH);
		// this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		// TODO (mark)
		// this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HARPY, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		// this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED_DARK));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_AMBER));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.TWO_TIGHT, true);
		// Throat settings and modifiers
		// TODO (mark)
		
		// Tongue modifiers
		this.setTongueLength(TongueLength.TWO_VERY_LONG.getMedianValue());
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.POINTY);
		this.setNippleSize(NippleSize.ONE_SMALL);
		this.setAreolaeSize(AreolaeSize.ONE_SMALL);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.THREE_NORMAL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.TWO_FIRM.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(30);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(300);
		this.fillCumToMaxStorage();
		this.setTesticleCount(2);
		
		// Vagina:
		// No vagina
		// this.setVaginaVirgin(false);
		// this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		// this.setVaginaLabiaSize(LabiaSize.ONE_SMALL);
		// this.setVaginaSquirter(false);
		// this.setVaginaCapacity(Capacity.TWO_TIGHT, true);
		// this.setVaginaWetness(Wetness.THREE_WET);
		// this.setVaginaElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		// this.setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		
		// Feet
		this.setFootStructure(FootStructure.NONE);
		// TODO (mark)
		// this.setLegType(LegType.getLegTypeFromId("SNAKE_MORPH"));
		this.setLegConfiguration(LegConfiguration.TAIL_LONG, false);
		// default 5x height
		// this.setLegTailLengthAsPercentageOfHeight(500);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		// TODO (mark)

		this.unequipAllClothingIntoVoid(true, true);

		// this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_BLACK, false), true, this);
		// this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, this);
		// this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_PLUNGE_DRESS, PresetColour.CLOTHING_BLACK, false), true, this);

		// this.setPiercedEar(true);
		// this.setPiercedLip(true);
		// this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_SILVER, false), true, this);
		// this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", PresetColour.CLOTHING_SILVER, false), true, this);

	}

	@Override
	public String getSpeechColour() {
		// TODO (mark)
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#C13350";
			
		} else {
			return "#D7567D";
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_RESISTING;
	}

	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", UrosDialogue.VICTORY) {
				@Override
				public void effects() {
					// TODO (mark)
					// Main.game.getDialogueFlags().values.add(DialogueFlagValue.dominantPacified);
					// Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.HARPY_MATRIARCH_DOMINANT_PERFUME), false, true));
					
					// if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_ONE) {
					// 	Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_TWO));
						
					// } else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_TWO) {
					// 	Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_THREE));
						
					// } else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_THREE) {
					// 	Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_REWARD));
					// }
				}
			};
		} else {
			return new Response("", "", UrosDialogue.LOSS);
		}
	}
}