import java.util.*;
import java.lang.*;
import javax.swing.*;
/**
*   Alan Finnin     17239621
*   Daniel Dalton   17219477
*   Stephen Cliffe  17237157
**/
public class is17237157{
  private static ArrayList<Board> open = new ArrayList<Board>();
  private static ArrayList<Board> closed = new ArrayList<Board>();
  //private static int goal[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
  private static int goal[][] = {{1,2,3},{4,5,6},{7,8,0}};

  public static void main(String[] args){
    int[][] temp;

    temp = convertSqr("1 2 3 7 8 0 4 5 6");//inputWindow("start state"));
    //goal = convertSqr(inputWindow("end state"));

    Board currentBoard = new Board(temp,goal,0);
	
	int g = 0; //keeps track of level of current table
	
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
		currentBoard = new Board(copyArr2D(open.get(0).getTable()), goal, open.get(0).getG());
		
		//adds chosen board to the closed array
		closed.add(new Board(copyArr2D(open.get(0).getTable()), goal, open.get(0).getG()));
		
		//removes chosen board from open array
		open.remove(0);
		open.trimToSize();
		
	}
	
	currentBoard.printBoard();
  }
  
  private static String inputWindow(String state){
		boolean correct = true;
        String msg = "Please input a 9 digit(0-9) " + state + " with a space separating each";
        String patternEight = "^([0-8]{1}\\s+){8}[0-8]\\s*";
		String patternFifteen = "^([0-9]{1,2}\\s+){15}[0-9]{1,2}\\s*";
        String rawInput = JOptionPane.showInputDialog(null, msg, "8 Puzzle", JOptionPane.QUESTION_MESSAGE);

        if(rawInput.matches(patternEight) || rawInput.matches(patternFifteen)){
			String[] strInput = rawInput.split("\\s+");
			int inputArr[] = new int[strInput.length];

			for(int i = 0; i < strInput.length; i++){
				inputArr[i] = Integer.parseInt(strInput[i]);
			}
			int tempArr[] = inputArr;
			Arrays.sort(tempArr);

			for(int i = 0; i < inputArr.length; i++){
				if(tempArr[i] != i){
					JOptionPane.showMessageDialog(null, "Error: Entry format incorrect, duplicate digit", "Error", JOptionPane.ERROR_MESSAGE);
					return inputWindow(state);
				}
			}
            return rawInput;
        }else {
            JOptionPane.showMessageDialog(null, "Error: Entry format incorrect", "Error", JOptionPane.ERROR_MESSAGE);
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
	  int n = open.size();
	  for(int i = 0; i < n -1; i++){
		  for(int j = 0; j < n-i-1; j++){
			  if(open.get(j).getValue() > open.get(j+1).getValue()){
				  Collections.swap(open, j, j + 1);
			  }
		  }
	  }
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
