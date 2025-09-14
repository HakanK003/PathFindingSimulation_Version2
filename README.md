# Path Finding Simulation in 2 Dimensions
### Idea 
Given map has some known obstacles and target locations. 
Then algorithm calculates shortest path.
The robot starts moving on that path. 
If robot detects any obstacles using its sensors then robot/algorithm recalculates shortest path with new information and keeps going on new path. 
This process will be repeated as much as needed until the robot reaches the target location.

### Some Key Features
 - Path finding algorithms
 - Map configurations
 - Saving maps to make them reusable
 - Robots sensors to detect obstacles
 - Log and error tracker

### Future Ideas
  - 3 dimensional version
  - Dynamic unknown obstacles
  - Different sensor combinations
  - Multiple target locations
  - Different path finding algorithms
  - Using octagons (or some other shapes) as map grids instead of squares (unlikely due to high complexity and low value)

### Version History
Initially, I started to this project last year for a robotics club project (original version that I proposed for my team can be found here [PathFindingSimulation](https://github.com/HakanK003/PathFindingSimulation)). However, team decided to not use this simulation due to its complexity. 
I find this project quite interesting and decided to complete it anyway as a personal project separately after a year. 

### How To Use It
  - Clone the repository (I think you need the Java 17 setup on your device to run the code)
  - Create map using menu panel
  - Choose control options
  - Click Start
  - Simulation should start
  - If same map should be used again click save map option under the menu and select from saved maps when using
