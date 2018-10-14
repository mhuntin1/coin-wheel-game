# Notes: user stories 10/11/18-10/12/18
======================================================
## User stories:
	1) As a player I want to begin a game
		-As a dev I need to hold state for coins
		-As a dev I need to hold state for wheel
		-As a dev I need a game to control the wheel
			-number of slots
			-state of each coin (H/T)
		-As a user I want to choose how many coins I want in the game
		-As a user I want to choose how many spins I want in the game
		-As a user I want to choose how many reveals I want in the game
	
	2) As a dev I want to implement strategy for the 4/2 game so the user can find best odds to win
		-As a dev I want to recognize that it's a 4/2 game
		-As a dev when it is a 4/2 game I want to win in 5 spins or less everytime
		-As a dev I want to implement the algorithm
			1. Reveal slots 1 & 3 and flip to H
			2. Reveal slots 2& 3 and flip to H
			3. Reveal 0 & 2 and if T is showing flip to H else flip 0 to T
			4. Reveal 1 & 2 flip both
			5. Reveal 0 & 2 and flip both.
		
	
	



## Done:
	1) As a user I want to get new coin states
