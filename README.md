# Mazebot-Simulator

This project simulates a robot with the task of exploring an unkown area and then finding the best path between any two locations. The robot cannot move diagonally and at any given point it can only see what is in front, to the left, and to the right of it.

##About
  1. Build a maze using the map editor. (Red = obstruction, Green = start, Blue = finish) 
  ![alt text][maze]

  2. Press "Begin." All of the squares will then turn gray (Unknown) and will gradually reappear as the robot discovers them (some may remain gray, this means there is no way the robot could ever see those spots). Boxes that turn yellow are boxes that the bot will revist to further explore at a later time.
  
  ![alt text][explore]
  3. After the robot explores the entire area, an ideal path between the start and end will be generated. The path is in purple.

  ![alt text][path]
  4. You may press "Stop" and then edit/save/load the map. Press "Begin" again to generate a new path. You can change the delay in between steps in the top right (in ms).
  
  ![alt text][delay]

[maze]: https://i.imgur.com/TCcj0Ic.png "Maze"
[explore]: https://i.imgur.com/qbyVR9f.png "Explore"
[path]: https://i.imgur.com/eBOEjaI.png "Path"
[delay]: https://i.imgur.com/9x2jh3b.png "Delay"
