package com.lilithsthrone.game.character.effects;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;

/* 
 * The order of this enum defines the precedence used by GameCharacter.getMasturbationPreventedReason().
 * Also note that slaves are considered  (and thus do not have permission to alter their clothing)
 */
public enum ChasteReason {
  // Character has ARMS_HINDERED status (bound in chains, webbed etc.) and the character is not a taur, whose arms could not reach their genitals/anus anyway
  ARMS_HINDERED(BodyPartType.ARM, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      return c.isArmMovementHindered() && ChasteReason.IS_TAUR.appliesTo(c);
    }
  },

  // Character is a slave, and does not have the SEX_MASTURBATE permission
  SLAVE_FORBIDDEN(null, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      return c.isSlave() && !c.hasSlavePermissionSetting(SlavePermissionSetting.SEX_MASTURBATE);
    }
  },

  // Character's penis is in a chastity device
  PENILE_CHASTITY(BodyPartType.PENIS, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      return c.isErectionPreventedNonphysically() || c.isErectionPreventedPhysically();
    }
  },

  // Character's vagina is in a chastity device
  VAGINAL_CHASTITY(BodyPartType.VAGINA, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      for(AbstractClothing clothing : c.getClothingCurrentlyEquipped()) {
        if (clothing.getItemTags().contains(ItemTag.CHASTITY) && clothing.doesBlockSlot(c, clothing.getSlotEquippedTo(), InventorySlot.VAGINA)) {
          return true;
        }
      }
      return false;
    }
  },

  // Character's penis is covered by sealed clothing
  PENIS_SEALED(BodyPartType.PENIS, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      for(AbstractClothing clothing : c.getClothingCurrentlyEquipped()) {
        if (
          clothing.isSealed() && clothing.doesBlockSlot(c, clothing.getSlotEquippedTo(), InventorySlot.PENIS)
            // TODO (mark): they are a slave and are thus not permitted to remove the clothing
            // || (c.isSlave() && clothing.getSlotEquippedTo() == InventorySlot.PENIS)
        ) {
          return true;
        }
      }
      return false;
    }
  },

  // Character's vagina is covered by sealed clothing
  VAGINA_SEALED(BodyPartType.VAGINA, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      for(AbstractClothing clothing : c.getClothingCurrentlyEquipped()) {
        if (
          // (clothing.isSealed() && c.getInventory().getBlockingCoverableAreaClothingList(c, CoverableArea.VAGINA, false).contains(clothing))
          (clothing.isSealed() && clothing.doesBlockSlot(c, clothing.getSlotEquippedTo(), InventorySlot.VAGINA) && !clothing.getItemTags().contains(ItemTag.PLUGS_VAGINA))
            || clothing.getItemTags().contains(ItemTag.SEALS_VAGINA)
        ) {
          return true;
        }
      }
      return false;
    }
  },

  // Character has a toy in their vagina
  VAGINA_PLUGGED(BodyPartType.VAGINA, true) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      for(AbstractClothing clothing : c.getClothingCurrentlyEquipped()) {
        if (clothing.getSlotEquippedTo() == InventorySlot.VAGINA && clothing.getItemTags().contains(ItemTag.PLUGS_VAGINA)) {
          return true;
        }
      }
      return false;
    }
  },

  // Character's anus is in a chastity device, and they do not have a negative FetishDesire towards FETISH_ANAL_RECEIVING
  ANAL_CHASTITY(BodyPartType.ANUS, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      if (c.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
        return false;
      }

      for(AbstractClothing clothing : c.getClothingCurrentlyEquipped()) {
        if (clothing.getItemTags().contains(ItemTag.CHASTITY) && clothing.doesBlockSlot(c, clothing.getSlotEquippedTo(), InventorySlot.ANUS)) {
          return true;
        }
      }
      return false;
    }
  },

  // Character's anus is covered by sealed clothing, and they do not have a negative FetishDesire towards FETISH_ANAL_RECEIVING
  ANAL_SEALED(BodyPartType.ANUS, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      if (c.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
        return false;
      }

      for(AbstractClothing clothing : c.getClothingCurrentlyEquipped()) {
        if (
          (clothing.isSealed() && clothing.doesBlockSlot(c, clothing.getSlotEquippedTo(), InventorySlot.ANUS) && !clothing.getItemTags().contains(ItemTag.PLUGS_ANUS))
            || clothing.getItemTags().contains(ItemTag.SEALS_ANUS)
        ) {
          return true;
        }
      }
      return false;
    }
  },

  // Character has a toy in their anus, and they do not have a negative FetishDesire towards FETISH_ANAL_RECEIVING
  ANUS_PLUGGED(BodyPartType.ANUS, true) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      if (c.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
        return false;
      }
      
      for(AbstractClothing clothing : c.getClothingCurrentlyEquipped()) {
        // TODO (mark): equipped slot may be something else, but orifice still plugged?
        if (clothing.getSlotEquippedTo() == InventorySlot.ANUS && clothing.getItemTags().contains(ItemTag.PLUGS_ANUS)) {
          return true;
        }
      }
      return false;
    }
  },

  // Character's anus is free, but they have a negative FetishDesire towards FETISH_ANAL_RECEIVING
  DISLIKES_ANAL(BodyPartType.ANUS, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {

      // anus is blocked
      if (Stream.of(
        ChasteReason.ANAL_CHASTITY,
        ChasteReason.ANAL_SEALED,
        ChasteReason.ANUS_PLUGGED
      ).anyMatch(reason -> reason.appliesTo(c))) {
        return false;
      }

      return c.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative();
    }
  },
  
  // Character has a negative FetishDesire towards FETISH_MASTURBATION
  DISLIKES_MASTURBATION(null, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      return c.getFetishDesire(Fetish.FETISH_MASTURBATION).isNegative();
    }
  },

  // only returned by getGenitalMasturbationPreventedReason() as the highest precedence, when the character has no penis or vagina
  MISSING_BODY_PART(null, false) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      return !c.hasPenis() && !c.hasVagina();
    }
  },

  // Character is a taur, and cannot reach their genitals/anus with their arms
  IS_TAUR(BodyPartType.ASS, true) {
    @Override
    public boolean appliesTo(GameCharacter c) {
      return c.isTaur();
    }
  };

  public final BodyPartType affectedBodyPart;
  public final boolean permitsPleasure;

  private ChasteReason(BodyPartType affectedBodyPart, boolean permitsPleasure) {
    this.affectedBodyPart = affectedBodyPart;
    this.permitsPleasure = permitsPleasure;
  }

  public abstract boolean appliesTo(GameCharacter c);

  private static Set<ChasteReason> generalReasons = new HashSet<ChasteReason>() {{
    add(ChasteReason.ARMS_HINDERED);
    add(ChasteReason.SLAVE_FORBIDDEN);
    add(ChasteReason.DISLIKES_MASTURBATION);
    add(ChasteReason.IS_TAUR);
  }};

  private static Set<ChasteReason> penileReasons = new HashSet<ChasteReason>() {{
    addAll(generalReasons);
    add(ChasteReason.PENILE_CHASTITY);
    add(ChasteReason.PENIS_SEALED);
  }};

  public static ChasteReason getPenile(GameCharacter c) {
    for (ChasteReason reason : ChasteReason.values()) {
      if (penileReasons.contains(reason) && reason.appliesTo(c)) return reason;
    }
    return null;
  }

  private static Set<ChasteReason> vaginalReasons = new HashSet<ChasteReason>() {{
    addAll(generalReasons);
    add(ChasteReason.VAGINAL_CHASTITY);
    add(ChasteReason.VAGINA_SEALED);
    add(ChasteReason.VAGINA_PLUGGED);
  }};

  public static ChasteReason getVaginal(GameCharacter c) {
    for (ChasteReason reason : ChasteReason.values()) {
      if (vaginalReasons.contains(reason) && reason.appliesTo(c)) return reason;
    }
    return null;
  }

  private static Set<ChasteReason> genitalReasons = new HashSet<ChasteReason>() {{
    // will also include generalReasons
    addAll(penileReasons);
    addAll(vaginalReasons);
  }};

  public static ChasteReason getGenital(GameCharacter c) {
    if (ChasteReason.MISSING_BODY_PART.appliesTo(c)) {
      return ChasteReason.MISSING_BODY_PART;
    }

    for (ChasteReason reason : ChasteReason.values()) {
      if (genitalReasons.contains(reason) && reason.appliesTo(c)) return reason;
    }
    return null;
  }

  private static Set<ChasteReason> analReasons = new HashSet<ChasteReason>() {{
    addAll(generalReasons);
    add(ChasteReason.ANAL_CHASTITY);
    add(ChasteReason.ANAL_SEALED);
    add(ChasteReason.ANUS_PLUGGED);
    add(ChasteReason.DISLIKES_ANAL);
  }};

  public static ChasteReason getAnal(GameCharacter c) {
    for (ChasteReason reason : ChasteReason.values()) {
      if (analReasons.contains(reason) && reason.appliesTo(c)) return reason;
    }
    return null;
  }

  public static ChasteReason get(GameCharacter c) {
    for (ChasteReason reason : ChasteReason.values()) {
      if (reason.appliesTo(c)) return reason;
    }
    return null;
	}

  public static ChasteReason get(GameCharacter c, BodyPartType affectedBodyPart) {
    for (ChasteReason reason : ChasteReason.values()) {
      if (
        reason.appliesTo(c)
        && (
          (affectedBodyPart == null && reason.affectedBodyPart == null)
          || reason.affectedBodyPart.equals(affectedBodyPart)
        )
      ) {
        return reason;
      }
    }
    return null;
  }

	public static BodyPartType getPossibleMasturbation(GameCharacter c) {
    if (ChasteReason.get(c, null) != null) {
      return null; // some general reason is prevent all types of masturbation
    }

    ChasteReason penisReason = ChasteReason.get(c, BodyPartType.PENIS);
		if (c.hasPenis() && penisReason == null) {
			return BodyPartType.PENIS;
		}

    ChasteReason vaginaReason = ChasteReason.get(c, BodyPartType.VAGINA);
		if (c.hasVagina() && vaginaReason == null) {
			return BodyPartType.VAGINA;
		}

    ChasteReason anusReason = ChasteReason.get(c, BodyPartType.ANUS);
		if (anusReason == null) {
			return BodyPartType.ANUS;
		}

    if (ChasteReason.get(c, BodyPartType.ASS) == null) {
      if (
        penisReason.permitsPleasure
          && vaginaReason.permitsPleasure
          && anusReason.permitsPleasure
      ) {
        return BodyPartType.ASS; // Taur character might squat on something or rub against something
      }
		}
		return null;
	}
}
