# Strategic Player Implementation
------------------------------------------

## Summary:

This deliverable includes an implemntation of StrategicPlayer Interface along with tests.

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

## Breakdown of Tests:
-----------------------------------------------------------------------

#### getSlotsToRevealTest

This test initializes the game by passing parameter into beginGame(). It then creates an expected string value and calls getSlotsReveal
then converts it to a string. The test then compares the two string to assert that the output is expected.

#### getNewCoinStateTest

This test initializes the game by passing parameter into beginGame(). It then creates a strng based on the revealed coin states.
It then gets new coin states based on the dominant coin face.

## Authors:

Jon Bowen

Jackie Nugent

Mark Huntington
