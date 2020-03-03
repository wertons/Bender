**BENDER** is a simple pathfinding project made in Java.
The project includes two classes:    
  
  -Bender: The class that recieves the map and returns the path and or returns the shortest possible path.  
  -Cell: A auxiliary class we use in order to construct the map  
 
This pathfinding algorithm is designed to work with maps such as the following:
      <pre>
                #######\n  
                #T    #\n  
                #     #\n  
                #     #\n  
                #  $ T#\n  
                #  X###\n  
                #I    #\n 
                #######
      </pre>
      
Which have to be inserted into the Bender constructor as a single line string. To represent the map we use the following logic:

<ul>
<li>' ': A empty space is used to represent a empty cell.</li>
<li>'X': The letter X is used to represent the starting cell.</li>
<li>'$': The dollar sign is used to represent the goal cell.</li>
<li>'#': The hash is used to represent a wall, a inpassable cell.</li>
<li>'T': The letter T is used to represent a teleporter cell, which on entry teleports to the closest teleport(other than himself).</li>
<li>'I': The letter I used to represent a inverter, the inverter changes the default direction priorities.</li>
</ul>

[Here you can find a more in-depth explanation](./Documentation/Project_3-Bender-Pau_Bestard.pdf)
