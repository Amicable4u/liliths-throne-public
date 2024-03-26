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

* Add a cheat menu (#cheatMenuButton). Can refill health & mana, gain money, gain spell upgrade points

See [todo.md](todo.md) for more planned features