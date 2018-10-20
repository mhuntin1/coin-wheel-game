# Coin-Wheel-Game

A Game for fun and analysis.

### Repo URL

https://github.com/AgileSoftwareArchitects/coin-wheel-game

### Game Description:

A wheel that contains a ring of coins.

A player may choose the number of coins, number of coins to reveal, and max number of spins. 

All coin states are initially hidden from the player.  

The wheel is considered to be in winning state when all coins are of the same face.

During a game a player attempts to configure a wheel into a winning state before the max number of spins is depleted. 

After each spin, a set amount of coins will be revealed to the player in order to manipulate into a winning state.

#### State of coin:

    Heads state -> H
    Tails state -> T 
    Hidden state -> -
    Change coin state -> ?

#### STEPS:
    (0) Set up of wheel with number of coins, number of reveals, and max number of spins.  

    (1) If the wheel is in a winning state, the player wins and the game is over.
        If the number of turns per game is exceeded, the player loses and the game is over.
    
    (2) The player chooses which coins they wish to reveal in this turn and the state of each chosen
        coin is revealed to the player.

    (3) The player may choose to modify any states of the revealed coins. 

    (4) Coins are then hidden, and the wheel is spun at random amount.

    (5) Repeat 1-4 until reach winning state or number of spins is depleted. 


## Strategic Player Implementation
------------------------------------------

### Algorithm of Four Coin Two Reveal Strategy:
 
Algorithm for a guaranteed win state in five moves or less with four coins and two reveals.

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

#### Directory Setup:
 
 UPDATE HERE

#### lib/

  - Files that support JUnit, JaCoCo, Checkstyle, Java2HTML, CPD, PMD, Proguard

  - See:

      * https://junit.org
  
      * https://www.jacoco.org/jacoco/trunk/doc
  
      * http://checkstyle.sourceforge.net
  
      * http://www.java2html.com
  
      * https://pmd.github.io
 
      * https://sourceforge.net/projects/proguard/

#### Ant Tasks

The set of main targets can be displayed at the command line using:  

                          ant -p 
Example: 

    % ant -p
    Buildfile: build.xml

            Build file for StrategicPlayer project
  
    Main targets:

      checkstyle           generate checkstyle report
      clean                clean up
      compile              compile the source
      cpd                  proccess source with CPD
      dist                 generate the distribution
      doc                  generate the usage documentation
      doc-private          generate the maintenance documentation
      env                  display build parameters
      format               generate formatted versions of source code
      optimize             optimize using proguard
      pmd                  process source with PMD
      report               format junit test results
      run                  run driver
      test                 run junit tests
      testCoverage         run junit tests with JaCoCo instrumentation
      testCoverageReport   format JUnit and JaCoCo test results
      testOptimized        run junit tests using optimized jar
      testOptimizedReport  format junit test results using optimized jar
    Default target: all
    %

See also: https://antapache.org

#### Authors:

- Jon Bowen | jbowen10@msudenver.edu

- Jackie Nugent | jnugent3@msudenver.edu

- Mark Huntington | mhuntin1@msudenver.edu
