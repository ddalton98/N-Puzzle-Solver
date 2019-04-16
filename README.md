# CS4006-Project

This is a an A-Star (A*) based heuristic algorithm to solve a given n-puzzle from a given start state to a given goal state. It will display all the steps it took between the start state and the goal state and the time it took to solve it. 

## Getting Started

* To run this program clone the repository to your own machine with java JDK 8 or newer.
* Compile the program in the command line or within your IDE of choice.

### Running The Program

* Run the program from the command line.
* You will be presented with a window asking for a start state. Enter your puzzle in the format of a numbers separated by spaces. An 8-puzzle will require 9 numbers to be entered with '0' representing the empty tile i.e. 
```
1 2 3 7 8 0 5 6 7

Equates to
1 2 3
7 8 0
5 6 7
```
* You will them be prompted for the goal state which should be entered in the same format as above. Use the same size goal and start states.
* The puzzle should complete for any complexity 3x3 in less than a second. Simple 4x4 may take longer and complex ones may take several minutes.
* Once the solution has been reached the console will print out the steps taken to reach the goal and how long it took.

### Common Start States

Input | Complexity
--- | --- 
1 2 3 7 8 0 4 5 6 | Easy
3 8 1 7 5 0 2 4 6 | Medium
8 6 7 2 5 4 3 0 1 | Hardest

## Authors

* [Stephen Cliffe](https://www.github.com/nytrode)
* [Daniel Dalton](https://www.github.com/ddalton98) - Me!
* [Alan Finnin](https://www.github.com/alanfinnin)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
