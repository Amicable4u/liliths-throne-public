package com.lilithsthrone.game.dialogue.places.dominion.mountIsil;

import com.lilithsthrone.game.character.npc.dominion.mountIsil.Silenis;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.mountIsil.SilenisDialogue;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;

public class Guardian {
  public final DialogueNode exterior;
  public final DialogueNode talk;
  public final DialogueNode victory;
  private final Class npc;

  public Guardian(Class npc, DialogueNode exterior, DialogueNode talk, DialogueNode victory) {
    this.exterior = exterior;
    this.talk = talk;
    this.victory = victory;
    this.npc = npc;
  }

  public NPC getNpc() {
    return Main.game.getNpc(this.npc);
  }

  private static Guardian silenis = new Guardian(Silenis.class, SilenisDialogue.EXTERIOR, SilenisDialogue.TALK, SilenisDialogue.VICTORY);
  // private static Guardian vissea = new Guardian(Vissea.class, VisseaDialogue.EXTERIOR, VisseaDialogue.TALK, VisseaDialogue.VICTORY);
  // private static Guardian glycor = new Guardian(Glycor.class, GlycorDialogue.EXTERIOR, GlycorDialogue.TALK, GlycorDialogue.VICTORY);
  // private static Guardian cyrephses = new Guardian(Cyrephses.class, CyrephsesDialogue.EXTERIOR, CyrephsesDialogue.TALK, CyrephsesDialogue.VICTORY)

  public static Guardian get() {
    // if (Main.game.getPlayerCell().getLocation().getX() > 16) {
    // 	return glycor;
    // }
    // if (Main.game.getPlayerCell().getLocation().getX() < 4) {
    // 	return vissea;
    // }
    if (Main.game.getPlayerCell().getLocation().getY() < 4) {
      return silenis;
    }
    
    // return cyrephses
    return null;
  }
}