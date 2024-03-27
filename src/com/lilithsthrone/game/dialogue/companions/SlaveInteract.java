package com.lilithsthrone.game.dialogue.companions;

import java.util.LinkedList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.DenyCock;

public class SlaveInteract {

  public static String responseTabTitle = UtilText.parse("[style.colourArcane(Interact)]");

  private NPC slave;
  private DialogueNode previousDialogueNode;

  public SlaveInteract(NPC slave) {
    this.slave = slave;
  }

  private DialogueNode SPANK = new DialogueNode("", ".", true) {
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
      String tailSpecial1 = "", tailSpecial2 = "";
      if(!slave.hasTail()) {
        tailSpecial1 = "Letting out [pc.a_moan+], [pc.name] [pc.verb(reach)] down and [pc.verb(grab)] [npc.namePos] waist, using one [pc.hand(true)] to hold [npc.herHim] still,"
                  + " while using [pc.her] other to deliver a series of firm spanks to [npc.her] [npc.ass+].";
      } else {
        tailSpecial1 = "Letting out [pc.a_moan+], [pc.name] playfully [pc.verb(grab)] the base of [npc.her] [npc.tail+] and [pc.verb(pull)] upwards,"
              + " raising [npc.her] [npc.ass+] up high in the air before starting to deliver a series of firm spanks to [npc.her] exposed cheeks.";
      }
      if(!slave.hasTail()) {
        tailSpecial2 = "[pc.Name] [pc.verb(reach)] down and [pc.verb(grab)] [npc.namePos] waist with one hand,"
            + " holding [npc.herHim] firmly in [pc.her] grip as [pc.she] [pc.verb(start)] to playfully slap [npc.her] exposed cheeks.";
      } else {
        tailSpecial2 = "[pc.Name] [pc.verb(reach)] down and [pc.verb(grab)] the base of [npc.namePos] [npc.tail+], causing [npc.herHim] to let out a surprised yelp as [pc.she] playfully [pc.verb(pull)] upwards,"
                  + " forcing [npc.herHim] to push [npc.her] [npc.ass+] up high in the air as [pc.name] [pc.verb(start)] to firmly spank [npc.her] exposed cheeks.";
      }
    
      return UtilText.returnStringAtRandom(
            tailSpecial1,
            tailSpecial2,
            "[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [pc.name] [pc.verb(start)] playfully spanking [npc.her] [npc.ass+].",
            
            "[pc.Name] [pc.verb(use)] one [pc.hand] to hold [npc.name] still, while using [pc.her] other to deliver a series of firm spanks to [npc.her] exposed ass cheeks.",
            
            "[pc.Name] [pc.verb(reach)] down and [pc.verb(start)] to playfully spank [npc.her] [npc.ass+], which causes [npc.herHim] to [npc.verb(squirm)] and [npc.verb(squeal)] in response.");
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
      add(new Response("Spank", UtilText.parse(slave, "Give [npc.name] a good slap on [npc.her] [npc.ass+]."), SPANK));
    }};

    if (DenyCock.isPhysicallyPossible(slave)) {
      if (DenyCock.isBaseRequirementsMet(slave)) {
        actions.add(new Response("Deny cock", UtilText.parse(slave, "[npc.name] has not permitted you to deny [npc.herHim]."), null));
      } else {
        actions.add(DenyCock.getBlockedResponse(slave));
      }
    }

    if (index > 0 && actions.size() >= index) {
      return actions.get(index - 1);
    }
    return null;

    // TODO (mark): remove example responses
		
    // if (index == 1) {
    //   if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
    //     return new Response("Inspect", UtilText.parse(slave, "You are already taking a closer look at [npc.name]!"), null);
    //   }
    //   return new Response("Inspect", UtilText.parse(slave, "Take a closer look at [npc.name]!"), SLAVE_MANAGEMENT_INSPECT);
      
    // } else if (index == 2) {
    //   if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
    //     return new Response("Job", UtilText.parse(slave, "You are already setting [npc.namePos] job and work hours!"), null);
    //   }
    //   if(!slave.getOwner().isPlayer()) {
    //     return new Response("Job", "You cannot manage the job of a slave you do not own!", null);
    //   }
    //   return new Response("Job", "Set this slave's job and work hours.", SLAVE_MANAGEMENT_JOBS);
      
    // } else if (index == 3) {
    //   if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
    //     return new Response("Permissions", UtilText.parse(slave, "You are already setting [npc.namePos] permissions!"), null);
    //   }
    //   if(!slave.getOwner().isPlayer()) {
    //     return new Response("Permissions", "You cannot manage the permissions of a slave you do not own!", null);
    //   }
    //   return new Response("Permissions", "Set this slave's permissions.", SLAVE_MANAGEMENT_PERMISSIONS);
      
    // } else if (index == 4) {
    //   if(!slave.getOwner().isPlayer()) {
    //     return new Response("Inventory", "You cannot manage the inventory of a slave you do not own!", null);
    //   }
      
    //   if(slave.getOwner().isPlayer()) {
    //     return new ResponseEffectsOnly("Inventory", UtilText.parse(slave, "Manage [npc.namePos] inventory.")){
    //       @Override
    //       public void effects() {
    //         Main.mainController.openInventory(slave, InventoryInteraction.FULL_MANAGEMENT);
    //       }
    //     };
    //   } else {
    //     return new Response("Inventory", UtilText.parse(slave, "You can't manage [npc.namePos] inventory, as you don't own [npc.herHim]!"), null);
    //   }
      
    // } else if(index == 5) {
    //   if(!slave.getOwner().isPlayer()) {
    //     return new Response("Send to Kate", "You cannot send slaves which you do not own to Kate!", null);
        
    //   } else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
    //     return new Response("Send to Kate",
    //         UtilText.parse(slave, "Send [npc.name] to Kate's beauty salon, 'Succubi's secrets', to get [npc.her] appearance changed."),
    //         SLAVE_MANAGEMENT_COSMETICS_HAIR) {
    //           @Override
    //           public void effects() {
    //             BodyChanging.setTarget(slave);
    //           }
    //         };
    //   } else {
    //     return new Response("Send to Kate", "You haven't met Kate yet!", null);
    //   }
      
    // } else if (index == 6) {
    //   if(!slave.getOwner().isPlayer()) {
    //     return new Response("Perks", "You can't manage the perks of slaves that you do not own!", null);
    //   }
    //   if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
    //     return new Response("[style.colourMinorBad(Reset perks)]",
    //         UtilText.parse(slave, "Reset all of [npc.namePos] perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)"),
    //         Main.game.getCurrentDialogueNode()) {
    //       @Override
    //       public void effects() {
    //         slave.resetPerksMap(false, false);
    //       }
    //     };
    //   }
    //   return new Response("Perks", "Spend your slave's perk points.", SLAVE_MANAGEMENT_PERKS);
      
    // } else if(index==7) {
    //   if(!slave.isAbleToSelfTransform()) {
    //     return new Response("Transformations", slave.getUnableToTransformDescription(), null);
        
    //   } else {
    //     return new Response("Transformations",
    //         UtilText.parse(slave, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
    //         BodyChanging.BODY_CHANGING_CORE){
    //       @Override
    //       public void effects() {
    //         BodyChanging.setTarget(slave, coreNode, defaultResponseTab);
    //       }
    //     };
    //   }
      
    // } else if(index==8) {
    //   if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
    //     return new Response("Set names", UtilText.parse(slave, "You are managing [npc.namePos] name!"), null);
    //   }
    //   if(slave == null) {
    //     return new Response("Set names", "You haven't selected anyone...", null);
    //   }
    //   return new Response("Set names", UtilText.parse(slave, "Change [npc.namePos] name or tell [npc.herHim] to call you by a different name."), OCCUPANT_CHOOSE_NAME);
      
    // } else if(index==10 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
    //   return new Response("Send home", UtilText.parse(slave, "[npc.Name] isn't in your party, so you can't send [npc.herHim] home..."), null);
      
    // } else if(index==11) {
    //   if(!slave.getOwner().isPlayer()) {
    //     return new Response("Combat moves", "You can't manage the combat moves of slaves that you do not own!", null);
    //   }
    //   return new Response("Combat moves", UtilText.parse(slave, "Adjust the moves [npc.name] can perform in combat."), CombatMovesSetup.COMBAT_MOVES_CORE) {
    //     @Override
    //     public void effects() {
    //       CombatMovesSetup.setTarget(slave, coreNode);
    //     }
    //   };
      
    // } else if(index==12) {
    //   return new Response("Spells", UtilText.parse(slave, "Manage [npc.namePos] spells."), SpellManagement.CHARACTER_SPELLS_EARTH) {
    //     @Override
    //     public void effects() {
    //       SpellManagement.setSpellOwner(slave, coreNode);
    //     }
    //   };
      
    // } else if(index==13) {
    //   if(!slave.isElementalSummoned()) {
    //     return new Response("Dispel elemental", UtilText.parse(slave, "[npc.Name] doesn't have an elemental summoned..."), null);
        
    //   } else {
    //     return new Response("Dispel elemental", UtilText.parse(slave, "Tell [npc.name] to dispel [npc.her] elemental."), coreNode) {
    //       @Override
    //       public void effects() {
    //         slave.removeCompanion(slave.getElemental());
    //         slave.getElemental().returnToHome();
    //       }
    //     };
    //   }
      
    // } else if(index==14) {
    //   if(!Main.game.getPlayer().hasItemType("innoxia_slavery_freedom_certification")) {
    //     return new Response("Set free",
    //         UtilText.parse(slave,
    //             "You do not have a Freedom Certification, so you cannot set [npc.name] free..."
    //             + "<br/><i>Freedom Certifications can be purchased from Finch in Slaver Alley's Slaver Administration building.</i>"),
    //         null);
        
    //   } else {
    //     if(slave instanceof Scarlett) {
    //       return new Response("Set free",
    //           UtilText.parse(slave,
    //               "Fill out a Freedom Certification and set [npc.name] free!"
    //               + "<br/>If you do this, [npc.she] will undoubtedly immediately leave and return to Helena's nest..."),
    //           SET_SLAVE_FREE_SCARLETT);
    //     }
    //     if(slave instanceof Brax) {
    //       return new Response("Set free",
    //           UtilText.parse(slave,
    //               "You cannot set [npc.name] free...<br/><i>(This will be added later when I add more content for Brax!)</i>"),
    //           null);
    //     }
        
    //     String unavailableGuestDescription = "";
    //     if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
    //       unavailableGuestDescription = "[style.italicsMinorBad(As you do not have Lilaya's permission to house guests, you will be unable to invite [npc.name] to stay here in the mansion after freeing [npc.herHim]!)]";
          
    //     } else if(!slave.isAffectionHighEnoughToInviteHome()) {
    //       unavailableGuestDescription = "[style.italicsBad([npc.Name] does not like you enough to consider staying with you after being set free!)]";
          
    //     } else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
    //       unavailableGuestDescription = "[style.italicsMinorBad(As you do not have a free guest room for [npc.name] to move in to, you will be unable to invite [npc.herHim] to stay here in the mansion after freeing [npc.herHim]!)]";
    //     }
    //     String thanksjava = unavailableGuestDescription;
        
    //     return new Response("Set free",
    //         UtilText.parse(slave,
    //             "Fill out a Freedom Certification and set [npc.name] free!"
    //             + "<br/>"
    //             + (thanksjava.isEmpty()
    //               ?"[style.italicsMinorGood([npc.Name] likes you enough to accept an offer of staying here to live with you, so after freeing [npc.herHim] you can offer to let [npc.herHim] stay in one of the free guest rooms!)]"
    //               :thanksjava+"<br/>[style.italicsBad(This will permanently remove [npc.herHim] (and everything in [npc.her] inventory) from the game!)]")),
    //         SET_SLAVE_FREE) {
    //       @Override
    //       public Colour getHighlightColour() {
    //         if(thanksjava.isEmpty()) {
    //           return PresetColour.GENERIC_MINOR_GOOD;
    //         }
    //         return PresetColour.GENERIC_NPC_REMOVAL;
    //       }
    //     };
    //   }
      
    // } else if(index == 0) {
    //   if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
    //     return new Response("Back", "Return to the occupant list overview.", coreNode) {
    //       @Override
    //       public void effects() {
    //         Main.game.getDialogueFlags().setManagementCompanion(null);
    //         coreNode = null;
    //       }
    //     };
    //   }
    //   return new Response("Leave", "Exit the occupant management screen.", Main.game.getDefaultDialogue(false)) {
    //     @Override
    //     public void effects() {
    //       Main.game.getDialogueFlags().setManagementCompanion(null);
    //       coreNode = null;
    //     }
    //   };
    // }
	}

  public static boolean isAvailable(GameCharacter slave) {
    return slave.isSlave() && slave.getOwner().isPlayer();
  }

  public static SlaveInteract fromCharacter(NPC slave) {
    return isAvailable(slave) ? new SlaveInteract(slave) : null;
  }
}
