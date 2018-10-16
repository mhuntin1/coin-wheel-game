# Strategic Player Implementation
------------------------------------------

### Repo URL

https://github.com/AgileSoftwareArchitects/coin-wheel-game


## Summary:

This deliverable includes the preliminary algorithm of the 4 coin 2 reveal strategy game as well as 
working code and tests that implement that algorithm. 

## System Requirements

1. Java SDK 8
2. Apache Ant 1.10.5

## Ant Tasks

Ant tasks:

clean: delete all files and folder related to compiled code

buildSource: compile source files

buildTest: compile test files

build: build both source and test files

test: run unit tests

## Algorithm of Four Coin Two Reveal:

A wheel of coins in an unknown state.
 
Algorithm for a guaranteed win state in five moves or less.

#### STEPS: 

     Spin 1: Open and reveal two diagonal coins and flip to the same face. 

     Spin 2: Open and reveal two adjacent coins and flip to the same face. 

     Spin 3: Open and reveal two diagonal coins:

             Best Case: If final coin found, flip to the same face in order to win the game.
       
             Worst Case: Otherwise, flip one of the matching coins to make mismatched faces. 
       
             ->This provides a known state of the wheel: matching faces that are set adjacent to one another. 
 
     Spin 4: Open and reveal two adjacent coins:
       
             -If the faces match flip both coins to win.
             -If faces do not match then flip both coins: H->T and T->H 
 
     Spin 5: Open and reveal two diagonal coins and flip both coins to win.  

## Breakdown of Tests:
-----------------------------------------------------------------------

#### getSlotsToRevealGeneralTest

This test initializes the game by passing parameter into beginGame(). It then creates an expected string value and calls getSlotsReveal
then converts it to a string. The test then compares the two strings to assert that the output is expected.

#### getNewCoinStateGeneralTest

A test which begins a mock game and asserts the getNewCoinStateGeneral() method is correctly revealing coins in a general game without strategy. 

#### fourTwoPlayerTest

Asserts that the 4 coin 2 reveal strategy game is working through the algorithm with the given methods in order to win in 5 spins or less.

#### flipAllTest

Asserts that the flipAll method is manipulating the CharSequence as expected. 
 

## Authors:

- Jon Bowen | jbowen10@msudenver.edu

- Jackie Nugent | jnugent3@msudenver.edu

- Mark Huntington | mhuntin1@msudenver.edu
