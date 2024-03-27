package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

public class DenyCock {

  private static String actionTitle = "Deny cock";

  public static SexAction sexAction = new SexAction(
		SexActionType.SPEECH,
		ArousalIncrease.ONE_MINIMUM,
		ArousalIncrease.THREE_NORMAL,
		CorruptionLevel.ONE_VANILLA,
		Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
		SexParticipantType.NORMAL
	) {
    @Override
    public boolean isPhysicallyPossible() {
      return DenyCock.isPhysicallyPossible(Main.sex.getCharacterTargetedForSexAction(this));
    }
  
    @Override
    public boolean isBaseRequirementsMet() {
      // GameCharacter teaser = Main.sex.getCharacterPerformingAction();
      return DenyCock.isBaseRequirementsMet(Main.sex.getCharacterTargetedForSexAction(this));
    }
  
    @Override
    public String getActionTitle() {
      return DenyCock.actionTitle;
    }
  
    @Override
    public String getActionDescription() {
      return "Reach down and play with [npc2.namePos] chastely secured, [npc2.cock+].";
    }
  
    @Override
    public String getDescription() {
      UtilText.nodeContentSB.setLength(0);
  
      // String caged = UtilText.returnStringAtRandom("caged", "chastened");
      
      switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
        case DOM_GENTLE:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
              "Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] caged [npc2.cock+], letting out a playful [npc.laugh] as [npc.she] [npc.verb(start)] slowly tapping and prodding [npc2.namePos] cage.",
  
              "[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs], and, taking [npc2.her] caged [npc2.dick+] in her grip, [npc.she] [npc.verb(pull)] down firmly on the device, tugging the [npc2.race]'s [npc2.cock+] as the cage tightens around [npc2.herHim].",
  
              "Teasing [npc.her] [npc.fingers] around the base of [npc2.namePos] chastely secured [npc2.cock+], [npc.name] [npc.verb(smile)] and [npc.moan] softly, savouring how hard [npc.she] is beneath the cage as [npc.she] [npc.verb(start)] gently stroking the little area of exposed and throbbing flesh that isn't completely locked away."));
          break;
        case DOM_NORMAL:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
            "Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] caged [npc2.cock+], letting out a snicker as [npc.she] [npc.verb(start)] tapping and prodding [npc2.namePos] cage, watching the poor [npc2.race]'s [npc2.cock+] throbs painfully.",
  
            "[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs], and, [npc.verb(take)] a strong grip on [npc2.her] caged [npc2.dick+]. [npc.She] [npc.verb(pull)] down firmly on the device, tugging the [npc2.race]'s [npc2.cock+] as the cage tightens around [npc2.herHim], making blood vessels swell in futile arousal.",
  
            "Teasing [npc.her] [npc.fingers] around the base of [npc2.namePos] chastely secured [npc2.cock+], [npc.name] [npc.verb(smile)] and [npc.moan] softly, savouring how hard [npc.she] is beneath the cage as [npc.she] [npc.verb(start)] squeezing the little area of exposed and throbbing flesh that isn't completely locked away."));
          break;
        case DOM_ROUGH:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
            "Reaching down between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] caged [npc2.cock+], letting out a cruel snicker as [npc.she] [npc.verb(start)] slapping [npc2.namePos] cage, watching the poor [npc2.race]'s [npc2.cock+] throbs painfully with each blow.",
  
            "[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs], and, [npc.verb(take)] a strong grip on [npc2.her] caged [npc2.dick+]. [npc.She] [npc.verb(pull)] down harshly on the device, tugging the [npc2.race]'s [npc2.cock+] as the cage tightens around [npc2.herHim], making blood vessels swell as [npc2.name] [npc2.verb(squirm)] in pain.",
  
            "Teasing [npc.her] [npc.fingers] around the base of [npc2.namePos] chastely secured [npc2.cock+], [npc.name] [npc.verb(smile)] and [npc.moan] softly, savouring how hard [npc.she] is beneath the cage as [npc.she] [npc.verb(start)] painfully choking [npc2.namePos] bound shaft, feeling the [npc2.race] throbbing painfully from being teased while remaining locked away."));
          break;
        case SUB_EAGER:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
              "[npc.name] eagerly reaches down between [npc2.namePos] [npc2.legs] and eagerly [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] caged [npc2.cock+], letting out a longing [npc.moan] as [npc.she] [npc.verb(start)] stroking and squeezing the cage as if it were [npc2.namePos] [npc2.dick+].",
  
              "[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs], and, taking [npc2.her] caged [npc2.dick+] in her grip, [npc.she] [npc.verb(pull)] and [npc.verb(tug)] excitedly on the device, doing [npc.her] best to stimulate the [npc2.race]'s [npc2.cock+] as the cage tightens around [npc2.herHim].",
  
              "Teasing [npc.her] [npc.fingers] around the base of [npc2.namePos] chastely secured [npc2.cock+], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently stroking the little bit of exposed and throbbing flesh that isn't covered by [npc2.namePos] cage, fingers searching for any gap in the enclosed device."));
          break;
        case SUB_NORMAL:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
            "[npc.name] reaches down between [npc2.namePos] [npc2.legs] and slowly [npc.verb(wrap)] [npc.her] [npc.fingers] around [npc2.her] caged [npc2.cock+], letting out a longing [npc.moan] as [npc.she] [npc.verb(start)] stroking and squeezing the cage as if it were [npc2.namePos] [npc2.dick+].",
  
            "[npc.Name] [npc.verb(drop)] one of [npc.her] [npc.hands] down between [npc2.namePos] [npc2.legs], and, taking [npc2.her] caged [npc2.dick+] in her grip, [npc.she] [npc.verb(pull)] and [npc.verb(tug)] gently on the device just enough to stimulate the [npc2.race]'s [npc2.cock+] as the cage tightens around [npc2.herHim].",
  
            "Snaking [npc.her] [npc.fingers] around the base of [npc2.namePos] chastely secured [npc2.cock+], [npc.name] [npc.verb(let)] out a soft [npc.moan] as [npc.she] [npc.verb(start)] gently stroking the little bit of exposed and throbbing flesh that isn't covered by [npc2.namePos] cage, letting [npc.her] fingers circle around [np2.namePos] shaft."));
          break;
        case SUB_RESISTING:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom("TODO (mark): sub resisting pace"))
        default:
          break;
      }
  
      // TODO (mark): improve the receiver dialogue (make giving and receiving dialogue match?)
  
      switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
        case DOM_GENTLE:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
              " Letting out a soft [npc2.moan], [npc2.name] [npc2.verb(start)] gently bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs inside [npc2.her] cage.",
  
              " With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] slowly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch, enjoying the feeling of having [npc2.her] cage played with as [npc2.her] [npc2.cock+] stiffens in contained arousal." ,
  
              " [npc2.Name] [npc2.verb(start)] slowly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], [npc2.moaning] softly as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] wrapping around the base of [npc2.her] [npc2.cock+]."));
          break;
        case DOM_NORMAL:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
            " Letting out a soft [npc2.moan], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs inside [npc2.her] cage.",
  
              " With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch, enjoying the feeling of having [npc2.her] cage played with as [npc2.her] [npc2.cock+] stiffens in contained arousal." ,
  
              " [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], [npc2.moaning] softly as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] wrapping around the base of [npc2.her] [npc2.cock+]."));
          break;
        case DOM_ROUGH:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
            " Letting out a soft [npc2.moan], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], growling out as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue playing with [npc2.her] cage as her [npc2.cock+] throbs needily.",
  
            " With [npc2.a_moan+], [npc2.name] [npc2.verb(start)] roughly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch, enjoying the feeling of having [npc.namePos] [npc.fingers] wrapped around [npc2.her] cage as [npc2.her] [npc2.cock+] stiffens in contained arousal. [npc2.She] growls and [npc2.verb(tell)] [npc.herHim] not to stop.",
  
            " [npc2.Name] [npc2.verb(start)] forcefully bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], [npc2.moaning+] as [npc2.she] [npc2.verb(order)] [npc.herHim] to continue squeezing the sensitive base of [npc2.her] caged [npc2.cock+]."));
          break;
        case SUB_EAGER:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
            " Letting out a soft [npc2.moan], [npc2.name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs inside [npc2.her] cage.",
  
            " With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] eagerly thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch, enjoying the feeling of having [npc2.her] cage played with as [npc2.her] [npc2.cock+] stiffens in contained arousal." ,
  
            " [npc2.Name] [npc2.verb(start)] eagerly bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], [npc2.moaning] softly as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] wrapping around the base of [npc2.her] [npc2.cock+]."));
          break;
        case SUB_NORMAL:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
            " Letting out a soft [npc2.moan], [npc2.name] [npc2.verb(start)] shamefully bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], focusing on the feeling of [npc2.her] [npc2.cock+] as it throbs inside [npc2.her] cage.",
  
            " With a soft [npc2.moan], [npc2.name] [npc2.verb(start)] shamefully thrusting [npc2.her] [npc2.hips] against [npc.namePos] touch, enjoying the feeling of having [npc2.her] cage played with as [npc2.her] [npc2.cock+] stiffens in contained arousal." ,
  
            " [npc2.Name] [npc2.verb(start)] shamefully bucking [npc2.her] [npc2.hips] against [npc.namePos] [npc.hand], [npc2.moaning] softly as [npc2.she] [npc2.verb(focus)] on the feeling of [npc.her] [npc.fingers+] wrapping around the base of [npc2.her] [npc2.cock+]."));
          break;
        case SUB_RESISTING:
          UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
              " [npc2.Name] [npc2.verb(try)] to pull [npc2.her] [npc2.hips] back from [npc.namePos] unwanted touch, [npc2.sobbing] and struggling as [npc2.her] throbbing, ensnared [npc2.cock] betrays [npc2.her] arousal.",
  
              " With [npc2.a_sob+], [npc2.name] [npc2.verb(try)] to pull away from [npc.name], struggling and protesting as [npc2.her] caged and hard [npc2.cock] betrays [npc2.her] arousal.",
  
              " [npc2.A_sob+] bursts out from between [npc2.namePos] [npc2.lips+] as [npc2.she] struggle back against [npc.namePos] unwanted touch, trying to hide [npc2.her] embarrassingly chaste [npc2.cock]."));
          break;
        default:
          break;
      }
  
      return UtilText.nodeContentSB.toString();
    }
  };

  public static boolean isPhysicallyPossible(GameCharacter teased) {
    return teased.isErectionPrevented();
  }

  public static List<AbstractClothing> getBlockingClothing(GameCharacter teased) {
    return teased
      .getBlockingCoverableAreaClothingList(CoverableArea.PENIS, false)
      .stream()
      .filter(c -> !c.getClothingType().getItemTags(InventorySlot.PENIS).contains(ItemTag.CHASTITY))
      .collect(Collectors.toList());
  }

  public static boolean isBaseRequirementsMet(GameCharacter teased) {
    // return teased.isCoverableAreaBlockedFromGroping(SexAreaPenetration.PENIS);
    // TODO (mark): Account for PREVENTS_ERECTION_OTHER (if these effects are ever added)
    List<AbstractClothing> blocking = getBlockingClothing(teased);
    return isPhysicallyPossible(teased) && blocking.isEmpty();
  }

  public static Response getBlockedResponse(GameCharacter teased) {
    List<AbstractClothing> blocking = getBlockingClothing(teased);
    String blockedString = "clothes are";
    if (blocking.isEmpty()) {
      System.err.println("Tried to getBlockedResponse for DENY_COCK when there are no blocking clothes");
    } else {
      blockedString = blocking.get(0).getName() + " " + (blocking.get(0).getClothingType().isPlural() ? "are" : "is");
    }
    
    return new Response(actionTitle, UtilText.parse(teased, "You cannot access [npc.namePos] [npc.cock] since [npc.her] " + blockedString + " in the way!"), null);
  }
}