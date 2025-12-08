# Tic Tac Toe Bot

> ### Chloe Circenis & Caiden Gilbert
> **Object Oriented Analysis and Design**  
> University of Colorado Boulder  
> **Professor:** Bill Wright  
> **Date:** December 7, 2025
---
## About
Final Project for Object-Oriented Programming Class, which implements a Tic Tac Toe UI where players can play each other or a bot. The UI is implemeted using Java Swing and the bot uses MiniMax to make best moves. This repository is well suited for scaling to larger games such as connect four, checkers, or even chess. 

## File Structure
```bash
components/
└── src/
    ├── main/
    │   └── java/
    │       ├── board/
    │       │   ├── Board.java                      # abstract grid implementation
    │       │   ├── Cell.java                       # game data structure
    │       │   ├── Coordinates.java                # game data structure
    │       │   └── Occupant.java                   # abstract game piece
    │       │
    │       ├── game/    
    │       │   ├── Game.java                       # abstract game state class
    │       │   ├── GameDriver.java                 # abstract game driver class
    │       ├── TicTacToe/
    │       │   ├── MoveObserver.java               # game and player classes listen
    │       │   ├── MoveSubject.java                # gui publishes
    │       │   ├── Simulation.java                 # facade for running simulated games
    │       │   ├── TicTacToeDriver.java            # specific GameDriver for tictactoe
    │       │   └── TicTacToePiece.java             # specific occupant for tictactoe
    │       │
    │       ├── player/
    │       │   ├── Player.java                     # abstract player class
    │       │   ├── PlayerFactory.java              # generate human and bot players
    │       │   └── strategy/
    │       │       ├── HumanStrategy.java          # human player methods
    │       │       └── MiniMaxStrategy.java        # minimax algorithm for bot players
    │       │
    │       └── ui/
    │           └── TicTacToeGUI.java               # ui implementation
    │
    └── test/
        └── java/
            └── GameTest.java                       # implements tests for several game modes 

```
## Object Oriented Design Patterns Used

### Builder Pattern

- Implemented in Game
- Makes game initialization easier in main and tests

### Strategy Pattern

- Implemented for Players
- Allows different players to have different implementations

### Observer Pattern

- Implemented in MoveSubject and MoveObserver
- Allows for easy communication between ui and game/players

### Factory Pattern

- Implemeted in PlayerFactory
- Allows for the creation of a lot of players quickly

### Facade Pattern

- Implemented in Simulation
- Can provide insights into the MiniMax algorithm and be used for statistical analysis

---

## Dependencies 

This project is built with **Gradle** and uses the following key dependency:

- **JUnit 5 (Jupiter)** – For unit testing

All dependencies are managed automatically through `build.gradle`, so no manual installation is required.

---

## Build & Run Instructions  

1. Clone the repo
    - git clone https://github.com/ChloeCircenis/TicTacToeBot.git
    - cd TicTacToeBot

3. Use the included Gradle wrapper to build
    - ./gradlew build

3. (Optional) Run tests
    - ./gradlew test

4. Run the application (UI)
    - ./gradlew run

         


