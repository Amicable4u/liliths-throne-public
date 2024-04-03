# Readme

This is the source code for Lilith's Throne.

You must agree to the terms of the attached disclaimer and abide by the terms of the license if you wish to view this source code.

Dev-note: This project is relying on JavaFX which isn't included in the Openjdk, which is often the common choice for Linux enthusiasts. If you're using Linux, please make sure to use the Oracle JDK to build this project or install OpenJFX.

Copyright 2016 Innoxia (innoxia7@gmail.com) all rights reserved.


## Getting Started

```
asdf install java oracle-17
mvn package OR mvn clean install -T 1C -o
java -jar target/Lilith\'s\ Throne\ \(linux\)/Lilith\'s\ Throne-0.4.9.jar
```

## Changelog (this fork)

* started using java 21 (if you see java.lang.NullPointerException: Cannot invoke "com.sun.tools.javac.code.Symbol.isEnum()" because "expr.sym" is null it is because java 17 does not permit nested switch expressions)
* Add a cheat menu (#cheatMenuButton). Can refill health & mana, gain money, gain spell upgrade points
  * lmao I just learned about the existing debug menu. Oh well.
* Add the "Ask" action during sex, allowing the player to ask their partners what they would like.
* Add the "Deny cock" action during sex, allowing characters to tease their partner's cocks if they are currently in chastity.
* Added utilities in ChasteReason.java for reporting whether a character is able to masturbate, and if not, returns the reason why
* Added a new SlaveJob for "fortress warden" (assignable to Fyrsia only)
* Added a new SlaveInteract menu for slaves. Contains actions similar to the manage menu, but there are repeatable, "for fun" interactions (don't modify affection/obedience)

See [todo.md](todo.md) for more planned features