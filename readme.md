# TRroller
TRroller is a bit too overzealous discord dice bot written using the JDA library.  It provides the following features:
Feature 			| Description
------- 			| -----------
Dice Rolling		| Basic TTRPG dice types, includes multi dice roll and modifiers
GM Roll				| Emphemeral Roll options for the secret GM rolls
Dice Reseed			| Reseeding current dice because of too many unlucky rolls.  Currently global reseed
Different Algos		| Change the algorithm used to generate the random numbers
Different Distros	| Change the ranom number distribution

## Using the bot
### Roll
The roll command has a couple options.  Becuse it uses the slash command interface with discrod, it will support autocomplete.  The basic structure of the command is as follows:
* Roll
	* role_type (String)
		* gm (if this option is not used, gm is not enabled)
	* quantity
		* ints > 0 (default 1)
	* dice_type
		* d20 (default)
		* hiLow
		* d6 (TODO)
		* d4 (TODO)
		* d8 (TODO)
		* d12 (TODO)
	* modifier (int)
		* ints (default 0)
		
With this command structure in mind, here are a few sample rolls:

	//roll (rolls a single d20 with no modifiers)
	//roll gm (rolls a single d20 ephemerally)

Ugh, I'll finish this section eventually

### Jail
The jail command has plenty of hidden features designed to improve your luck.  It will let you reseed the dice, change the algorithm, and change the random number distribution.  
* jail
	* reseed 
	* algo
		* basic
		* crypto
		* blumBlumShub (TODO)
		* fibonacci (TODO)
		* shiftRegister (TODO)
	* distro (I'll probably add more things to tweak this eventually)
		* uniform
		* normal
		* beta (TODO)
		
Again, I'll... finish this eventually?

## License 
MIT 
	