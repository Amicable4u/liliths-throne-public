package com.lilithsthrone.game.dialogue.places.dominion.mountIsil;

import java.util.HashMap;
import java.util.List;

import com.lilithsthrone.game.character.npc.mountIsil.MountIsilNpc;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.Darkness;
import com.lilithsthrone.world.population.Population;
import com.lilithsthrone.world.population.PopulationDensity;
import com.lilithsthrone.world.population.AbstractPopulationType;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class MountIsilPlaces {

	public static boolean shouldRegenerateWorld(String worldType, String loadingVersion) {
		// this function is just for future use
		// it is called during game initialisation when importing a save file
		// it should return true for mount isil world types if they have been changed in a certain version
		// and return false for all other world types
		
		// TODO (mark): use getId() (if possible?) instead of magic strings
		// if (worldType == WorldType.MOUNT_ISIL_PLATEAU.getId())
		if (worldType == "MOUNT_ISIL_PLATEAU") {
			return false;
		} else if (worldType == "MOUNT_ISIL_OVERLOOK") {
			return false;
		}

		return false;
	}

	private static AbstractPopulationType cultistPopulationType = new AbstractPopulationType("cultist", "cultists") {};
	public static Population cultistPopulation = new Population(
		true,
		cultistPopulationType,
		PopulationDensity.NUMEROUS,
		new HashMap<AbstractSubspecies, SubspeciesSpawnRarity>() {{
			put(MountIsilNpc.SPECIES, SubspeciesSpawnRarity.TEN);
		}}
	);

	public static final AbstractPlaceType ENTRANCE = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Entrance",
			"mountIsilEntrance text",
			"dominion/mountIsil/stairs",
			PresetColour.BASE_GREY_LIGHT,
			MountIsilDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null,
			"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType EXIT = new AbstractPlaceType(
		WorldRegion.MOUNT_ISIL,
		"Exit",
		"mountIsilExit text",
		"dominion/mountIsil/stairs",
		PresetColour.BASE_GREY_LIGHT,
		MountIsilDialogue.EXIT,
		Darkness.ALWAYS_LIGHT,
		null,
		"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType PATH = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Mount Isil Path",
			"mountIsilPath text",
			null,
			PresetColour.BASE_GREY_LIGHT,
			MountIsilDialogue.PATH,
			Darkness.ALWAYS_LIGHT,
			null,
			"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType SHRINE = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Mount Isil shrine",
			"A small shrine.",
			"dominion/mountIsil/shrine",
			PresetColour.BASE_GREY_LIGHT,
			MountIsilDialogue.SHRINE,
			Darkness.ALWAYS_LIGHT,
			null,
			"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType TREASURE = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Treasure",
			"mountIsilTreasure text",
			"dominion/mountIsil/treasure",
			PresetColour.BASE_GOLD,
			MountIsilDialogue.TREASURE,
			Darkness.ALWAYS_LIGHT,
			null,
			"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
				cultistPopulation
			);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GUARDIAN = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Mount Isil guardian",
			"Mount Isil guardian text",
			"dominion/mountIsil/guardian",
			PresetColour.BASE_GREY_LIGHT,
			MountIsilDialogue.GUARDIAN,
			Darkness.ALWAYS_LIGHT,
			null,
			"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType UROS = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Inner Sanctum",
			"Mount Isil uros text",
			"dominion/mountIsil/guardian", // TODO (mark)
			PresetColour.BASE_GREY_LIGHT,
			UrosDialogue.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues();
		}
	}.initWeatherImmune();
}
