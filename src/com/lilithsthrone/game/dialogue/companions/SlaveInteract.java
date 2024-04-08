package com.lilithsthrone.game.dialogue.companions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.effects.ChasteReason;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.DenyCock;
import com.lilithsthrone.main.Main;

public class SlaveInteract {

  public static String responseTabTitle = UtilText.parse("[style.colourArcane(Interact)]");

  private NPC slave;
  private DialogueNode previousDialogueNode;

  public SlaveInteract(NPC slave) {
    this.slave = slave;
  }

  private boolean enjoysDenial() {
    return slave.hasFetish(Fetish.FETISH_DENIAL_SELF);
  }

  private boolean isPentUp() {
    return slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)
      || slave.isErectionPreventedPhysically()
      || slave.hasStatusEffect(StatusEffect.CHASTITY_1)
      || slave.hasStatusEffect(StatusEffect.CHASTITY_2);
  }

  private boolean isVeryPentUp() {
    return slave.hasStatusEffect(StatusEffect.CHASTITY_3)
      || slave.hasStatusEffect(StatusEffect.CHASTITY_4);
  }



  private boolean enjoysSpanking() {
    return slave.getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive()
      || slave.getFetishDesire(Fetish.FETISH_SADOMASOCHIST).isPositive();
  }

  // private boolean enjoysReverseSpanking() {
  //   return slave.getFetishDesire(Fetish.FETISH_SADIST).isPositive()
  //     || slave.getFetishDesire(Fetish.FETISH_SADOMASOCHIST).isPositive();
  // }

  private boolean hatesSpanking() {
    return slave.getFetishDesire(Fetish.FETISH_MASOCHIST).isNegative();
  }

  private String getMasturbationDescription() {
    ChasteReason chasteReason = ChasteReason.get(slave);
    final String reach = slave.hasLegs() ? "down between [npc.her] [npc.legs]" : ("towards [npc.her] " + slave.getBodyMaterial().getSkinAdj() + " nethers");
    final String unableShort = "as [npc.she] pictures being spanked some more.";
    final String unable = ", so [npc.she] just groans " + unableShort;
    return switch(ChasteReason.getPossibleMasturbation(slave)) {
      case VAGINA -> "[npc.name] slowly [npc.verb(reach)] " + reach + " and [npc.verb(start)] touching [npc.her] hungry, [npc.pussyRace] [npc.pussy].";
      case PENIS -> "[npc.name] slowly [npc.verb(reach)] " + reach + " and [npc.verb(start)] touching [npc.her] slightly aroused, [npc.pussyRace] [npc.cock].";
      case ANUS -> "[npc.name] slowly [npc.verb(reach)] around behind [npc.her] back and [npc.verb(slip)] one finger discreetly between [npc.her] cheeks, moaning softly as [npc.she] traces [npc.her] [npc.finger] around the rim of [npc.her] [npc.anus+] before slipping it inside.";
      case ASS -> "[npc.name] desperately searches for a way to get [npc.herself] off, but being a taur, [npc.she] can't reach back there [npc.herself]. Instead, [npc.she] frantically starts rubbing [npc.her] rear-end up against a nearby piece of furniture in a desperate attempt to masturbate and finish what you started.";
      case null, default -> "[npc.name] desperately searches for a way to get [npc.herself] off, " + switch (chasteReason) {
        case ARMS_HINDERED -> "but [npc.her] [npc.arms] are so tightly bound that [npc.she] couldn't possibly reach " + reach + unable;
        case PENILE_CHASTITY, VAGINAL_CHASTITY, ANAL_CHASTITY -> "but the chastity device [npc.she] has been locked in prevents [npc.her] from feeling any pleasure" + unable;
        case PENIS_SEALED, VAGINA_SEALED, ANAL_SEALED -> "but being unable to remove the arcane seal on the items obstructing the way, [npc.she] can only clench [npc.her] fists in frustration " + unableShort;
        case VAGINA_PLUGGED -> "[npc.name] slowly [npc.verb(reach)] " + reach + " and [npc.verb(take)] hold of the toy inserted in [npc.her] hungry, [npc.pussyRace] [npc.pussy], moaning in pleasure as [npc.she] [npc.verb(work)] the " + slave.getClothingInSlot(InventorySlot.VAGINA).getName() + " around inside [npc.her].";
        case ANUS_PLUGGED -> "[npc.name] slowly [npc.verb(reach)] " + reach + " and [npc.verb(take)] hold of the toy inserted in [npc.her] [npc.anus+], moaning in pleasure as [npc.she] works the " + slave.getClothingInSlot(InventorySlot.ANUS).getName() + " around inside [npc.her].";
        case SLAVE_FORBIDDEN -> "but as a slave [npc.she] has been forbidden from masturbation, and no matter how turned on [npc.she] might be, [npc.she] cannot risk the ire of [npc.her] owner by touching [npc.herself] without permission" + unable;
        case DISLIKES_ANAL -> "but the only area [npc.she] can reach is right between [npc.her] sore cheeks, and [npc.name] doesn't like using [npc.her] butt to get off, so [npc.she] can only clench [npc.her] fists in frustration" + unableShort;
        case DISLIKES_MASTURBATION -> "but what [npc.she] really wants is for you to finish what you started. Unwilling to masturbate to achieve release, [npc.name] can only clench [npc.her] fists in frustration" + unableShort;
        case MISSING_BODY_PART -> "but without any sex organs at all, [npc.name] is powerless to achieve any kind of release. As usual, [npc.she] touches [npc.her] barren bump, aching to feel something, but it only makes the problem worse, and [npc.she] is forced to give up, even hornier than before.";
        default -> "but there is simply no way for [npc.her] to satisfy [npc.herself]" + unable;
      };
    };
  }

  private DialogueNode SPANK = new DialogueNode("", "", true) {
    @Override
    public DialogueNodeType getDialogueNodeType() {
      return DialogueNodeType.PHONE;
    }

    @Override
    public String getLabel() {
      return "Ouch!";
    }

    @Override
    public String getContent() {

      AffectionLevelBasic affection = AffectionLevelBasic.getAffectionLevelFromValue(slave.getAffection(Main.game.getPlayer()));
      ObedienceLevelBasic obedience = ObedienceLevelBasic.getObedienceLevelFromValue(slave.getObedienceValue());
      // Pair<AffectionLevel, ObedienceLevel> disposition = new Pair<>();

      UtilText.nodeContentSB.setLength(0);
      UtilText.nodeContentSB.append("<p>").append( // approach
        (slave.hasTail() && Math.random() > 0.5)
          ? UtilText.returnStringAtRandom( // approach tail
            "[pc.name] [pc.verb(catch)] a movement in the corner of [pc.her] eye, and notice [npc.namePos] [npc.tail] lifting itself out of the way to reveal [npc.her] beautiful butt, and [pc.sheYou] can't help moving closer.",
            "[npc.namePos] [npc.tail] swishes enticingly as [npc.she] turns around, flicking over [npc.her] butt and making [pc.name] move closer."
          ) : UtilText.returnStringAtRandom( // approach
            "When [npc.name] has [npc.her] back turned, [pc.name] [pc.verb(sneak)] up behind [npc.herHim], eyeing [npc.her] cute [npc.ass].",
            "[pc.name] [pc.verb(wait)] until [npc.name] [npc.verb(turn)] around, and then takes a moment to admire the perfect curves of [npc.herHis] [npc.ass+].",
            "[pc.name] [pc.verb(find)] [pc.himself] distracted as [npc.name] [npc.verb(bend)] over for a moment, giving [pc.name] a perfect view of [npc.her] [npc.ass+], and [pc.sheYou] [pc.verb(start)] to saunter over.",
            switch (obedience) {
              case DISOBEDIENT -> "[pc.You] command [npc.name] to bend [npc.herself] over and present [npc.her] [npc.butt] for [pc.yourHer] enjoyment - an order which [npc.she] again carries out as slowly as [npc.she] possibly can.";
              case NEUTRAL -> "[npc.Name] [npc.verb(let)] out a flustered gasp as [pc.youShe] [pc.verb(order)] [npc.herHim] to bend over, but [npc.she] reluctantly moves to obey, slowly leaning down and sighing, [npc.speech(Let's get this over quickly then, <i>[npc.pcName]</i>.)]";
              case OBEDIENT -> UtilText.returnStringAtRandom(
                "As [npc.name] [npc.verb(catch)] [pc.yourHer] gaze travelling down [npc.her back], [npc.she] slowly begins to stick out her [npc.ass]. [npc.speech(Is this what you wanted, [npc.pcName]?)] [npc.She] asks.",
                "[npc.name] [npc.verb(see)] you staring at [npc.her] [npc.ass+], and immediately starts [npc.walking] closer. Without having to be told, [npc.she] [npc.verb(lie)] down in [pc.yourHer] lap, presenting [npc.her] [npc.butt] for [pc.youHer]. [npc.speech(Is this to your pleasure, [npc.pcName]?)] [npc.She] asks."
              );
              default -> Arrays.toString(new Throwable().getStackTrace());
            }
          )
      )
      .append(" ")
      .append(
        (slave.hasTail() && Math.random() > 0.5)
          ? UtilText.returnStringAtRandom( // spank tail
            "[pc.name] playfully [pc.verb(grab)] the base of [npc.namePos] [npc.tail+] and [pc.verb(lift)] it up to expose [npc.her] pert rear. It lashes side to side, but it is held tight as [pc.name] raises [npc.her] [npc.ass+] up to a comfortable height before delivering a series of firm spanks to [npc.her] exposed cheeks.",
            "The first sign that tells [npc.name] something is happening is when [npc.she] feels [npc.her] [npc.tail] brush against something behind [npc.herHim]. [npc.She] [npc.verb(let)] out a surprised yelp as [pc.name] playfully pulls [npc.her] back into [pc.herHim], forcing [npc.herHim] to push [npc.her] [npc.ass+] up against [pc.herHis] body as [pc.name] firmly spanks [npc.her] exposed cheeks."
          ) : UtilText.returnStringAtRandom( // spank
            "[pc.name] [pc.verb(reach)] down and [pc.verb(grab)] [npc.namePos] waist, using one [pc.hand(true)] to hold [npc.herHim] still, while using [pc.her] other to deliver a series of firm spanks to [npc.her] [npc.ass+]."
          )
      )
      .append("</p><p>");

      String dislikePunishment = UtilText.returnStringAtRandom(
        " A squeal accompanies the sound of her [npc.assSkin+] being whacked as [pc.youShe] [pc.verb(reach)] around and [pc.verb(smack)] [npc.her] again for " + switch (obedience) {
          case DISOBEDIENT -> "being so defiant";
          case NEUTRAL -> "being so reluctant";
          case OBEDIENT -> "looking at [pc.youHer] with such venom.";
          default -> Arrays.toString(new Throwable().getStackTrace());
        },
        " Unhappy with [npc.her] " + switch (obedience) {
          case DISOBEDIENT -> "defiant remarks";
          case NEUTRAL -> "slightly rebellious remarks";
          case OBEDIENT -> "look of considerable loathing";
          default -> Arrays.toString(new Throwable().getStackTrace());
        } + ", [pc.youShe] [pc.verb(grab)] [npc.her] by  [npc.her] [npc.wrist], your slave squirming and squealing as you punish the [npc.racePos] vulnerable backside a few more blows."
      );

      UtilText.nodeContentSB.append( // reaction
        // new Pair<AffectionLevel, ObedienceLevel>(AffectionLevel.DISLIKE, ObedienceLevel.DISOBEDIENT)
        switch (affection) {
          case DISLIKE -> switch (obedience) {
            case DISOBEDIENT -> UtilText.returnStringAtRandom(
              "An angry growl escapes from [npc.namePos] [npc.mouth] as [npc.she] recoils from [pc.herYour] touch. [npc.speech(Get your hands off me you fucking [pc.bitch]!)] [npc.She] [npc.verb(snap)], twirling around to move [npc.her] freshly spanked [npc.butt] out of [pc.yourHer] reach.",

              "[npc.speech(I'll break your fucking fingers next time you try that!)] [npc.name] shouts as [npc.she] stumbles away from [pc.youHer] and covers [npc.her] sore cheeks with [npc.her] [npc.hands]."
            ) + dislikePunishment;
            case NEUTRAL -> UtilText.returnStringAtRandom(
              "[npc.name] almost protests, but [npc.she] manages not to fight back as [pc.youShe] [pc.verb(have)] [pc.yourHer] way with [npc.her]. [pc.youShe] [pc.verb(hears)] [npc.her] mumble something under [npc.her] breath as [pc.youShe] [pc.verb(let)] [npc.her] go, although the sight of the [npc.racePos] freshly spanked [npc.butt] is almost too much to give up.",
              "[npc.speech(I hope you know I hate you.)] [npc.name] [npc.verb(snarl)] as [pc.youShe] [pc.verb(spank)] [npc.her], although [npc.she] manages not to move as [pc.yourHer] [pc.hand] slams down on [npc.her] exposed [npc.ass], waiting until [pc.youShe] [pc.are] done before backing away."
            ) + dislikePunishment;
            case OBEDIENT -> UtilText.returnStringAtRandom(
              "Despite dutifully getting ready to be spanked, [pc.name] can [pc.verb(see)] the distinct look of hatred in [npc.namePos] [npc.eyes] as [npc.she] obediently [npc.verb(receive)] [npc.her] punishment. Even as [npc.sheYou] [npc.verb(nurse)] [npc.her] sore [npc.ass], [pc.youShe] can sense [npc.herYour] rage at being humiliated like this.",
              "Even as [npc.namePos] [npc.verb(raise)] [npc.her] [npc.hips] to let [pc.youHer] spank [npc.her] harder, [npc.she] [npc.verb(give)] [pc.youHer] a look dripping with loathing."
            ) + dislikePunishment;
            default -> Arrays.toString(new Throwable().getStackTrace());
          };
          case NEUTRAL -> switch (obedience) {
            case DISOBEDIENT -> UtilText.returnStringAtRandom(
              "A meek noise escapes from [npc.namePos] [npc.mouth] as [npc.she] realises [pc.youShe] [pc.is]n't going to let [npc.her] escape before [pc.youShe] [pc.is] satisfied, and [pc.youShe] [pc.have] to [pc.verb(give)] [npc.her] [npc.butt] a thorough tenderising before [npc.she] begrudgingly [npc.verb(accept)] [npc.her] punishment. [npc.speech(This is no way to treat a slave!)] [npc.She] [npc.verb(grumbles)] as [pc.youShe] finally [pc.verb(let)] [npc.her] go.",
              "[npc.speech(Let me go, you're hurting me!)] [npc.name] groans as [pc.youShe] [pc.verb(hold)] [npc.her] firmly in place, making sure to properly discipline the defiant [npc.race]. When you deem [npc.her] sufficiently chastened, [pc.youShe] [pc.verb(let)] [npc.her] stumbles away to massage [npc.her] sore cheeks with [npc.her] [npc.hands]."
            );
            case NEUTRAL -> UtilText.returnStringAtRandom(
              "[npc.name] almost protests, but [npc.she] manages not to fight back as [pc.youShe] [pc.verb(have)] [pc.yourHer] way with [npc.her]. [pc.youShe] [pc.verb(hears)] [npc.her] mumble something under [npc.her] breath as [pc.youShe] [pc.verb(let)] [npc.her] go, although the sight of the [npc.racePos] freshly spanked [npc.butt] is almost too much to give up.",
              "[npc.speech(I hope you know I hate you.)] [npc.name] [npc.verb(snarl)] as [pc.youShe] [pc.verb(spank)] [npc.her], although [npc.she] manages not to move as [pc.yourHer] [pc.hand] slams down on [npc.her] exposed [npc.ass], waiting until [pc.youShe] [pc.are] done before backing away."
            );
            case OBEDIENT -> UtilText.returnStringAtRandom(
              "As [npc.name] dutifully [npc.verb(get)] ready to be spanked, [pc.name] can see that [npc.name] [npc.verb(feel)] that her punishment is uncalled for. [npc.SheYou] [npc.verb(ask)] [pc.youHer] if [pc.youShe] would like to keep going, but [pc.youShe] [pc.verb(tell)] [npc.her] that [npc.youShe] [npc.is] free to go, and [npc.sheYou] [npc.verb(nurse)] [npc.her] sore [npc.ass] as [npc.she] stalks away, chastened.",
              "Even as [npc.namePos] [npc.verb(raise)] [npc.her] [npc.hips] to let [pc.youHer] spank [npc.her] harder, [npc.she] [npc.verb(give)] [pc.youHer] a confused look, as if [npc.she] [npc.verb(think)] [npc.she] doesn't deserve [npc.her] punishment. [npc.thought(I should be careful [pc.he] doesn't get too distracted again...)] [npc.she] thinks to [npc.herself], as [npc.herYour] ass twinges in pain."
            );
            default -> Arrays.toString(new Throwable().getStackTrace());
          };
          case LIKE -> switch (obedience) {
            // TODO (mark): finish LIKE (currently same as NEUTRAL)
            case DISOBEDIENT -> UtilText.returnStringAtRandom(
              "A meek noise escapes from [npc.namePos] [npc.mouth] as [npc.she] realises [pc.youShe] [pc.is]n't going to let [npc.her] escape before [pc.youShe] [pc.is] satisfied, and [pc.youShe] [pc.have] to [pc.verb(give)] [npc.her] [npc.butt] a thorough tenderising before [npc.she] begrudgingly [npc.verb(accept)] [npc.her] punishment. [npc.speech(This is no way to treat a slave!)] [npc.She] [npc.verb(grumbles)] as [pc.youShe] finally [pc.verb(let)] [npc.her] go.",
              "[npc.speech(Let me go, you're hurting me!)] [npc.name] groans as [pc.youShe] [pc.verb(hold)] [npc.her] firmly in place, making sure to properly discipline the defiant [npc.race]. When you deem [npc.her] sufficiently chastened, [pc.youShe] [pc.verb(let)] [npc.her] stumbles away to massage [npc.her] sore cheeks with [npc.her] [npc.hands]."
            );
            case NEUTRAL -> UtilText.returnStringAtRandom(
              "[npc.name] almost protests, but [npc.she] manages not to fight back as [pc.youShe] [pc.verb(have)] [pc.yourHer] way with [npc.her]. [pc.youShe] [pc.verb(hears)] [npc.her] mumble something under [npc.her] breath as [pc.youShe] [pc.verb(let)] [npc.her] go, although the sight of the [npc.racePos] freshly spanked [npc.butt] is almost too much to give up.",
              "[npc.speech(I hope you know I hate you.)] [npc.name] [npc.verb(snarl)] as [pc.youShe] [pc.verb(spank)] [npc.her], although [npc.she] manages not to move as [pc.yourHer] [pc.hand] slams down on [npc.her] exposed [npc.ass], waiting until [pc.youShe] [pc.are] done before backing away."
            );
            case OBEDIENT -> UtilText.returnStringAtRandom(
              "As [npc.name] dutifully [npc.verb(get)] ready to be spanked, [pc.name] can see that [npc.name] [npc.verb(feel)] that her punishment is uncalled for. [npc.SheYou] [npc.verb(ask)] [pc.youHer] if [pc.youShe] would like to keep going, but [pc.youShe] [pc.verb(tell)] [npc.her] that [npc.youShe] [npc.is] free to go, and [npc.sheYou] [npc.verb(nurse)] [npc.her] sore [npc.ass] as [npc.she] stalks away, chastened.",
              "Even as [npc.namePos] [npc.verb(raise)] [npc.her] [npc.hips] to let [pc.youHer] spank [npc.her] harder, [npc.she] [npc.verb(give)] [pc.youHer] a confused look, as if [npc.she] [npc.verb(think)] [npc.she] doesn't deserve [npc.her] punishment. [npc.thought(I should be careful [pc.he] doesn't get too distracted again...)] [npc.she] thinks to [npc.herself], with a twinge of pain."
            );
            default -> Arrays.toString(new Throwable().getStackTrace());
          };
        }
      ).append("</p>");

      // TODO (mark): account for submissive / dominant reaction to being spanked

      if (enjoysSpanking()) {
        UtilText.nodeContentSB.append("<p>").append(
          switch (affection) {
            case DISLIKE -> UtilText.returnStringAtRandom(
              "You notice that despite how much [npc.she] hates you, [npc.name] couldn't help lingering for a moment, as if [npc.she] were hoping for more.",
              "Despite all of [npc.namePos] vitriol, you can see [npc.her] panting, and grinding [npc.her] legs together from having [npc.her] sensitive behind so thoroughly punished."
            );
            case NEUTRAL -> UtilText.returnStringAtRandom(
              "Although [npc.she] may not have thought [npc.her] punishment was justified, you notice [npc.name] biting [npc.her] lip and letting out a quiet moan as [npc.she] [npc.verb(feels)] the fresh marks on [npc.her] [npc.hipSize] ass.",
              "When [npc.she] thinks you aren't looking any more, " + getMasturbationDescription()
            );
            case LIKE -> UtilText.returnStringAtRandom(
              "Giving in to [npc.her] masochistic streak, [npc.name] gasps, [npc.speech(Can we do that again sometime?)] [npc.She] makes a point of showing off the state of [npc.her] thoroughly punished rear by bending over slightly and wiggling [npc.her] butt in [pc.yourHer] direction.",
              "Although most people would be embarrassed to enjoy being spanked, your slave found it too arousing to keep control of [npc.herself]. " + getMasturbationDescription()
            );
            default -> Arrays.toString(new Throwable().getStackTrace());
          }
        )
        .append("</p>");
      } else if (hatesSpanking()) {
        // TODO (mark)
        UtilText.nodeContentSB.append("<p>").append(
          switch (affection) {
            case DISLIKE -> "";
            case NEUTRAL -> "";
            case LIKE -> "";
            default -> Arrays.toString(new Throwable().getStackTrace());
          }
        ).append("</p>");
      }

      return UtilText.nodeContentSB.toString();
    }

    @Override
    public Response getResponse(int responseTab, int index) {
      if (index == 1) {
        return new Response("Finish", "", previousDialogueNode);
      }
      return null;
    }
  };

  private DialogueNode DENY_COCK = new DialogueNode("", "", true) {
    @Override
    public DialogueNodeType getDialogueNodeType() {
      return DialogueNodeType.PHONE;
    }

    @Override
    public String getLabel() {
      return "Teasing";
    }

    @Override
    public String getContent() {

      AffectionLevelBasic affection = AffectionLevelBasic.getAffectionLevelFromValue(slave.getAffection(Main.game.getPlayer()));
      ObedienceLevelBasic obedience = ObedienceLevelBasic.getObedienceLevelFromValue(slave.getObedienceValue());
      // Pair<AffectionLevel, ObedienceLevel> disposition = new Pair<>();

      UtilText.nodeContentSB.setLength(0);

      UtilText.nodeContentSB.append("<p>")
        .append(
          switch (affection) {
            case DISLIKE -> """
              [npc.name] curls [npc.her] [npc.lip] in distaste as you approach [npc.herHim].
            """;
            case NEUTRAL -> """
              [npc.name] looks neither excited nor dismayed as you approach [npc.herHim].
            """;
            case LIKE -> """
              [npc.name] looks excited to see you as you approach [npc.herHim].
            """;
            default -> Arrays.toString(new Throwable().getStackTrace());
          }
        ).append(
          switch (obedience) {
            case DISOBEDIENT -> " [npc.She] immediately shies away as you reach your [pc.hand] down [npc.her] [npc.bodyShape] body to";
            case NEUTRAL -> " [npc.She manages to hold still] as you reach your [pc.hand] down [npc.her] [npc.bodyShape] body to";
            case OBEDIENT -> " [npc.She] obediently allows you to inspect [npc.her] body, reaching down, you";
            default -> Arrays.toString(new Throwable().getStackTrace());
          }
        ).append(" take hold of [npc.her] chaste [npc.penisRace] [npc.penis].");

      String cock = "";
      String begging = "";
      if (slave.hasStatusEffect(StatusEffect.CHASTITY_1)) { // a day
        cock = "caged [npc.girlCock]";
        begging = "[npc.speech(Please [npc.petName(pc)], I've been horny all day, can you just take it off for a few minutes?')]";
      } else if (slave.hasStatusEffect(StatusEffect.CHASTITY_2)) { // 2+ days
        cock = "caged [npc.girlCock]";
        begging = "[npc.speech(Please [npc.petName(pc)], you haven't let me out for two days now. I'll be a good [npc.girl], just let me masturbate quickly!')]";
      } else if (slave.hasStatusEffect(StatusEffect.CHASTITY_3)) { // a week
        cock = "caged and leaking [npc.girlCock]";
        begging = "[npc.speech([I'm so horny npc.petName(pc)], I can't even think straight. I need to breed someone right now, or I'm going to go crazy!')]";
      } else if (slave.hasStatusEffect(StatusEffect.CHASTITY_4)) { // 2+ weeks
        // UtilText.nodeContentSB.append("");
        cock = "caged and leaking [npc.girlCock]";
        begging = "[npc.speech([Fuuuck...)] [npc.name] moans. [npc.She] is so distracted by [npc.her] painfully caged erection that [npc.she] can't even summon the words to beg to have [npc.her] cage removed. [npc.speech([npc.petName(pc), it hurts so badly! And it's leaking everywhere... I'll do anything, just please let me cum!])]";
      } else if (slave.isErectionPreventedPhysically()) {
        cock = "caged [npc.girlCock]";
        begging = "[npc.speech(It feels a little sore, [npc.petName(pc)]. Are you going to take it off soon?')]";
      } else if (slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)) {
        cock = "pent-up [npc.girlCock]";
        String ending = "you run your [pc.hands] over [npc.her] desperate and throbbing [npc.femininityRace]-[npc.cock].";
        UtilText.nodeContentSB.append(switch (affection) {
          case DISLIKE -> " [npc.speech(Get your [pc.hands] off me!)] [npc.name] snarls, but even though [npc.she] obviously loathes you, [npc.she] doesn't pull away, letting " + ending;
          case NEUTRAL -> " [npc.speech(Th-thank you.)] [npc.name] mumbles, slightly embarrassed to be letting " + ending;
          case LIKE -> " [npc.speech(Ooh, finally!)] [npc.name] moans, thrusting [npc.her] [npc.hips+] out to let " + ending;
          default -> Arrays.toString(new Throwable().getStackTrace());
        });
        begging = "";
      } else {
        cock =  "pent-up [npc.girlCock]";
        begging = switch (obedience) {
          case DISOBEDIENT -> "[npc.speech(I don't give a fuck what you told me to do! As if I would ever touch myself in front of you anyway!)] [npc.name] spits.";
          case NEUTRAL -> "[npc.speech(I still don't see why I'm not allowed to touch myself.)] [npc.name] whines.";
          case OBEDIENT -> "[npc.speech(I haven't been touching myself, just like you ordered, [npc.petName(pc)].)]";
          default -> Arrays.toString(new Throwable().getStackTrace());
        };
      }

      if (isVeryPentUp()) {
        UtilText.nodeContentSB.append(
          " [npc.name] mewls as you take hold of [npc.her] ").append(cock).append(". Even the gentle touch sends [npc.herHim] bucking [npc.her] hips into your [pc.hand], eager for more.</p><p>[pc.speech(It looks like your balls are a little sore, pet.)] You remark, giving [npc.namePos] [npc.balls+] a firm squeeze as [npc.she] groans and #IF(npc.hasLegs())clamps [npc.her] [pc.legs+] together#ELSEclenches [npc.her] muscles under your touch#ENDIF."
        ).append(begging);
      } else {
        UtilText.nodeContentSB.append(
          " [npc.name] mewls as you take hold of [npc.her] ").append(cock).append(". Even though [npc.she] hasn't been denied an orgasm for that long, it is still obvious from the way [npc.she] gently bucks [npc.her] hips into your [pc.hand] that [npc.she] is eager for more.</p><p>[pc.speech(It looks like your balls are a little sore, pet.)] You remark, giving [npc.namePos] [npc.balls+] a firm squeeze as [npc.she] groans and #IF(npc.hasLegs())clamps [npc.her] [pc.legs+] together#ELSEclenches [npc.her] muscles under your touch#ENDIF."
        ).append(begging);
      }

      UtilText.nodeContentSB.append("</p><p>").append(
        "[pc.speech(Just a little longer, [pc.petName(npc)].)] You say as you grope [npc.herHim], enjoying the feeling of [npc.herHim] squirming in your grip. [pc.speech(I don't want to see you trying to get off, and I definitely don't want you making a mess everywhere. Now be a good little slave and forget about using that cock. If you behave then I might let you touch it some day.)]</p><p>"
      );

      if (enjoysDenial()) {
        UtilText.nodeContentSB.append(
          "[npc.name] looks at you with mixed frustration and lust as [npc.she] breathes ragged breaths. [npc.speech(Alright, [npc.petName(pc)]. You can tease my useless [npc.cock] whenever you please.)]"
        );
      } else {
        UtilText.nodeContentSB.append(
          "[npc.name] groans in frustration as [npc.she] breathes raggedly while trying to ignore the growing erection #IF(npc.hasLegs())between [npc.her] [npc.legs+]#ELSEstraining for [npc.her] attention#ENDIF. [npc.speech(Are you finished?)]."
        );
      }

      return UtilText.nodeContentSB.append("</p><p>You give your [npc.race] slave a friendly slap on the rear, leaving [npc.her] even more horny than before, and send [npc.herHim] on [npc.her] way.</p>").toString();
    }

    @Override
    public Response getResponse(int responseTab, int index) {
      if (index == 1) {
        return new Response("Finish", "", previousDialogueNode);
      }
      return null;
    }
  };

  public Response getInteractResponses(int index, DialogueNode previousDialogueNode) {
    this.previousDialogueNode = previousDialogueNode;

    List<Response> actions = new LinkedList<>() {{
      // TODO (mark): It would be cool to unlock these after completing a little miniquest for the slave
      //              E.g. fulfilling one of their "love" fetishes
      // add(new Response("Spank", UtilText.parse(slave, "[npc.name] has not permitted you to spank [npc.herHim]."), null));
      add(new Response("Spank", UtilText.parse(slave, "Give [npc.name] a good slap on [npc.her] [npc.ass+]."), SPANK) {
        @Override
        public void effects() {
          if (enjoysSpanking()) {
            Main.game.getTextEndStringBuilder().append(
              slave.setLust(slave.getLust() + 10)
            );
          } else if (hatesSpanking()) {
            Main.game.getTextEndStringBuilder().append(
              slave.setLust(slave.getLust() - 10)
            );
          }
        }
      });
    }};

    if (DenyCock.isPhysicallyPossible(slave)) {
      if (DenyCock.isBaseRequirementsMet(slave)) {
        actions.add(new Response("Deny cock", UtilText.parse(slave, "[npc.name] has not permitted you to deny [npc.herHim]."), DENY_COCK));
      } else {
        actions.add(DenyCock.getBlockedResponse(slave));
      }
    }

    if (index > 0 && actions.size() >= index) {
      return actions.get(index - 1);
    }
    return null;
	}

  public static boolean isAvailable(GameCharacter slave) {
    return slave.isSlave() && slave.getOwner().isPlayer();
  }

  public static SlaveInteract fromCharacter(NPC slave) {
    return isAvailable(slave) ? new SlaveInteract(slave) : null;
  }
}
