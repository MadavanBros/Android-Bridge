Android-Bridge
==============

Update 4.14.13 9:30 AM (ashwin):

Added the BridgeServer, BridgeThread, and BridgePlayer class. BridgeServer accepts clients as BridgePlayers
and spawns Bridge Threads once it has 4 players. BridgeThreads then use the BridgePlayers's send, readNext, and
readAll methods to communicate with players.

I looked at your Rank and Suit enums and your not taking advantage of some of the built in methods of Enums.
Enums already have a compareTo method (that compares based on order in enum) and a getValue method (gets the
enum value from a String).

I liked how you split everything into different packages so I made sure to do that.
  - com.madavan.bridge.android Android Bridge Code
  - com.madavan.bridge.cards   Cards
  - com.madavan.bridge.server  Server

In my code I wrote Card.toString() and a static Card.fromString() that write Strings and create Cards from Strings.
We could use this for our messaging, or we could do what you did.

To Do List
- Add BridgeActivity code (all non-GUI components)
- Add BridgeThread code
- Create system of messaging
- LEARN XML

Android Naming Conventions
- Activites: NameActivity (ex. BridgeActivity)
- Layout Files: typeoffile_name_of_file (ex. activity_bridge)
- Drawable Files: typeofdrawable_name_of_file (ex. button_menu)

Java Naming Conventions
- Global Variables: _nameOfVariable
- Insatnce Variable: nameOfVariable
- Method Name: nameOfMethod
