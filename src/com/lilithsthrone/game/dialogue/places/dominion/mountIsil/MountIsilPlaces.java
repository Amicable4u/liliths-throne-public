package com.lilithsthrone.game.dialogue.places.dominion.mountIsil;

import java.util.List;

import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.MountIsilDialogue;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.population.Population;
import com.lilithsthrone.world.population.PopulationDensity;
import com.lilithsthrone.world.population.PopulationType;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.Darkness;

/**
 * @since 0.4.9
 * @version 0.4.9
 * @author Mark
 */
public class MountIsilPlaces {

	public static final AbstractPlaceType ENTRANCE = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Entrance",
			"mountIsilEntrance text",
			"mountIsil/stairs",
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
		"mountIsil/stairs",
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
			"Mount Isil",
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
			"mountIsil/shrine",
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
			"mountIsil/treasure",
			PresetColour.BASE_GOLD,
			MountIsilDialogue.TREASURE,
			Darkness.ALWAYS_LIGHT,
			null,
			"on Mount Isil") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GUARDIAN = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Mount Isil guardian",
			"Mount Isil guardian text",
			"mountIsil/guardian",
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

	public static final AbstractPlaceType Uros = new AbstractPlaceType(
			WorldRegion.MOUNT_ISIL,
			"Inner Sanctum",
			"Mount Isil uros text",
			"mountIsil/guardian", // TODO (mark)
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
