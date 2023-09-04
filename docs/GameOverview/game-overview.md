---
layout: default
title: Game Overview
nav_order: 4
permalink: /GameOverview
search_exclude: true
---

## Table of contents
{: .no_toc .text-delta }

1. TOC
{:toc}

---

# What is this game?

This game is a traditional RPG (role-playing game) where you play as a cat, and your goal is to walk around and interact with things in a level
in order to complete certain tasks. At the beginning of the game, the player cat's ball goes missing. The player has to perform
a series of actions in order to find the ball and win the game.

Inspiration for this game came from popular 2D RPG games such as Earthbound and Pokemon. The player currently can walk around and interact with entities as is standard to the genre.
The game's story, level, and graphics were inspired by the games [Earthbound](https://www.youtube.com/watch?v=EIoLcNLyd0g) and [Pokemon Mystery Dungeon](https://www.youtube.com/watch?v=gheGUlKuYmc).

The art direction was chosen to facilitate a "happy" vibe, and follows a blocky minimalistic style ~~because I am a horrible artist~~.
The grass is green, the flowers dance, and a bright color palette is used ~~which is totally not just the result of me only using Microsoft Paint default colors~~.

# Features

- One very short level that follows a story of finding the cat's lost ball
- Several entities that can be interacted with
- Player can walk around the level and interact with specific entities
- Dialogue from entities is conveyed using a textbox
- The level is completed when the cat's ball is found
- Tile map loader that supports both static and animated tiles
- Some interesting level set pieces and the ability to layer tiles to create a sense of depth
- A map editor tool
- A menu and credits screen

The menu screen can be navigated using the arrow keys, and an option can be selected with the space bar.

The player character (cat) can walk left, right, up, and down using the arrow keys. 
The space key can be used to tell the player to interact with an entity. 
If you complete the story and find the cat's ball, the level will be completed.

Clearly, this game is not finished -- it's pretty much just the "start" of an RPG. 
There is one level, a couple of entities that can be interacted with,
and the player can only walk around and interact. 
There are many different directions the game can be taken in from here, and the backing game engine was built to be as flexible as possible in order to support a wide variety of features that could be feasibly implemented.

# Tools used to make this game

This game was written in Java and utilizes Java Swing for UI/rendering. 

No external libraries were used; it's made entirely with what Java supplies out of the box.

All the game's art was created from scratch using Microsoft Paint.

# Running the game

The game can be run like any other Java application -- execute it from the `main` method.

The `main` method for the game is in the `Game` package > `Game.java` file.

The `main` method for the map editor is in the `MapEditor` package > `MapEditor.java` file. This is a separate program
from the game, but both are included in this project.

Java version 8 or later is required to run this game.
To verify your Java version, run the `java -version` command in your CLI.

# Game Screenshots

Here is what the game looks like:

Menu Screen
![menu-screen.png](../assets/images/menu-screen.png)

In Game Screens (the player character is the adorable cat!)
![game-screen-1.png](../assets/images/game-screen-1.png)

![game-screen-2.png](../assets/images/game-screen-2.png)

Example of walking around the map and interacting with entities.
![playing-level.gif](../assets/images/playing-level.gif)



