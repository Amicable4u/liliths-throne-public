# FAQ

__Q: How do I open the debug menu?__
A: Run the game and type the cheat code "buggy" (while anywhere in the game world, must be after the prologue)

__Q: How do I add character specific Dialogue?__

A: On the debug menu, navigate to Test Parser > Help. Also see [UtilText.java](src/com/lilithsthrone/game/dialogue/utils/UtilText.java) and [ParserTarget.java](src/com/lilithsthrone/game/dialogue/utils/ParserTarget.java) for a list of commands that will be substituted with appropriate character-specific terms. 

__Q: How do I make dialogues with multiple steps where actions can be chosen?__

A: See [QuickTransformations.java](src/com/lilithsthrone/game/dialogue/npcDialogue/QuickTransformations.java)

__Q: There are so many files, where do I begin?__

A: Start at [Sex.java](src/com/lilithsthrone/game/sex/Sex.java), [Main.java](src/com/lilithsthrone/main/Main.java) and [MainController.java](src/com/lilithsthrone/controller/MainController.java). From there, Find Reference and Ctrl+Shift+F are your friends. You may want to filter out the following directories in your searches: `src/com/lilithsthrone/game/character,src/com/lilithsthrone/game/dialogue,src/com/lilithsthrone/game/sex/sexAction`