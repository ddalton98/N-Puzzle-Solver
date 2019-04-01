import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.time.*;
/**
*   Alan Finnin     17239621
*   Daniel Dalton   17219477
*   Stephen Cliffe  17237157
**/
public class Main{
  private static ArrayList<Board> open = new ArrayList<Board>();
  private static ArrayList<Board> closed = new ArrayList<Board>();
  //private static int goal[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
  private static int goal[][] = {{1,2,3},{4,5,6},{7,8,0}};

  public static void main(String[] args){
    int[][] temp;

    temp = convertSqr(inputWindow("start state"));//inputWindow("start state"));
    //goal = convertSqr(inputWindow("end state"));
	LocalDateTime startTime = LocalDateTime.now();
    Board currentBoard = new Board(temp,goal,0);
	
	int g = 0; //keeps track of level of current table
	int curTable[][];
	
	//check if currentBoard = end goal, exit if true
	while(currentBoard.finish() == false){
		//set g to the g value of the currentBoard
		g = currentBoard.getG();
		//System.out.println(g + ", " + currentBoard.getValue());
		
		//generate all possible board movements, then add to open ArrayList
		getMovements(currentBoard, g+1);
		
		//sort open ArrayList by lowest G + H value
		sort();
		
		//set currentBoard to board with lowest value
		curTable = open.get(0).getTable();
		g = open.get(0).getG();
		currentBoard = new Board(copyArr2D(curTable), goal, g);
		
		//adds chosen board to the closed array
		closed.add(new Board(copyArr2D(curTable), goal, g));
		
		//removes chosen board from open array
		open.remove(0);
		open.trimToSize();
		
	}
	
	currentBoard.printBoard();
	LocalDateTime endTime = LocalDateTime.now();
	Duration duration = Duration.between(startTime, endTime);

	System.out.println("Time taken: " + duration.getSeconds() + "." + duration.getNano()/1000000 + "s");
  }
  
  private static String inputWindow(String state){
		
		boolean correct = true;
		String msg = "Please input a square puzzle(3x3, 4x4, etc.)\n using numbers" + state + " with a space separating each";
		String pattern = "^(\\d{1,2}\\s+){8,}\\d{1,2}(?=\\s)*";
		String rawInput = JOptionPane.showInputDialog(null, msg, "8 Puzzle", JOptionPane.QUESTION_MESSAGE);

		if(rawInput.matches(pattern)){
			String[] strInput = rawInput.split("\\s+");
			int inputArr[] = new int[strInput.length];
			for(int i = 0; i < strInput.length; i++){
				inputArr[i] = Integer.parseInt(strInput[i]);
			}
			
			int tempArr[] = inputArr;
			Arrays.sort(tempArr);

			for(int i = 0; i < inputArr.length; i++){
				if(tempArr[i] != i){
					JOptionPane.showMessageDialog(null, "Entry format incorrect: Duplicate Digit.", "Error!", JOptionPane.ERROR_MESSAGE);
					return inputWindow(state);
				}
			}
			//Test for squareness
			int x = tempArr[tempArr.length-1]+1;
			double sr = Math.sqrt(x); 
		  
			if((sr - Math.floor(sr)) == 0){
				return rawInput;
			}
			JOptionPane.showMessageDialog(null, "Entry format incorrect: Not a perfect square puzzle\nI.E. 3x3, 4x4 etc.", "Error", JOptionPane.ERROR_MESSAGE);
			return inputWindow(state);
		}else {
			JOptionPane.showMessageDialog(null, "Entry format incorrect, Tip: Keep cats away from the keyboard.", "Error", JOptionPane.ERROR_MESSAGE);
			return inputWindow(state);
		}
	}


  public static void getMovements(Board a, int g){
	  int[][] table = a.getTable();
	  int pos0[] = getPos0(table);
	  int x = pos0[0];
	  int y = pos0[1];

	  int west  = y + 1;
	  int east  = y - 1;
	  int north = x + 1;
	  int south = x - 1;

	  if(!(north > goal.length-1)){ //north
		  int next = table[north][y];
		  //System.out.print("North: " + next);

		  int[][] b = copyArr2D(a.getTable());
		  b[x][y] = next;
		  b[north][y] = 0;

		  Board temp = new Board(b, goal, g);
		  //System.out.println("\tH: " + temp.getValue());
		  if(!isClosed(temp)){
			open.add(temp);
		  }
	  }

    if(!(south < 0)){ //south
		  int next = table[south][y];
		  //System.out.print("South: " + next);

		  int[][] b = copyArr2D(a.getTable());
		  b[x][y] = next;
		  b[south][y] = 0;

		  Board temp = new Board(b, goal, g);
		  //System.out.println("\tH: " + temp.getValue());
		  if(!isClosed(temp)){
			open.add(temp);
		  }
	  }

    if(!(east < 0)){ //east
		  int next = table[x][east];
		  //System.out.print("East:  " + next);

		  int[][] b = copyArr2D(a.getTable());
		  b[x][y] = next;
		  b[x][east] = 0;

		  Board temp = new Board(b, goal, g);
		  //System.out.println("\tH: " + temp.getValue());
		  if(!isClosed(temp)){
			open.add(temp);
		  }
	  }

    if(!(west > goal.length-1)){ //west
		  int next = table[x][west];
		  //System.out.print("West:  " + next);

		  int[][] b = copyArr2D(a.getTable());
		  b[x][y] = next;
		  b[x][west] = 0;

		  Board temp = new Board(b, goal, g);
		  //System.out.println("\tH: " + temp.getValue());
		  if(!isClosed(temp)){
			open.add(temp);
		  }
	  }
}

public static boolean isClosed(Board a){
	boolean result;
	
	for(int i = 0; i < closed.size(); i++){
		if(a.isEqual(closed.get(i))) return true;
	}
	
	return false;
}

  public static void sort(){
	  Collections.sort(open, new SortBoard());
  }

  public static int[][] copyArr2D(int[][] in){
    int [][] out = new int[goal.length][goal.length];

    for(int i = 0; i < in.length; i++){
      for(int j = 0; j < in.length; j++){
        out[i][j] = in[i][j];
      }
    }
    return out;
  }

  public static int[] getPos0(int[][] arr){
	  int[] pos = {0,0};

	  for(int x = 0; x < arr.length; x++){
		for(int y = 0; y < arr.length; y++){
			if(arr[x][y] == 0){
				pos[0] = x;
				pos[1] = y;
			}
		}
	  }

	  return pos;
  }

  public static int[][] convertSqr(String in){
	  int temp [][] = new int[goal.length][goal.length];

	  String split[] = in.split("\\s+");
	  int len = (int) Math.sqrt(split.length);
	  int count = 0;

	  for(int x = 0; x < len; x++){
		for(int y = 0; y < len; y++){
			temp[x][y] = Integer.parseInt(split[count]);
			count++;
		}
	  }

	  return temp;
  }
}
  
  class SortBoard implements Comparator<Board> 
{ 
    // Used for sorting in ascending order of 
    // Board value 
    public int compare(Board a, Board b) 
    { 
        return a.getValue() - b.getValue(); 
    } 
} 
