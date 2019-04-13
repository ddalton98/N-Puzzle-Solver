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
  private static PriorityQueue<Board> open = new PriorityQueue<>();
  private static HashMap<String, Board> closed = new HashMap<>();
  public static final int[] goal = {1,2,3,-1,4,5,6,-1,7,8,0};
  private static ArrayList<Board> path = new ArrayList<>();
  //public static final int[] goal = {1,2,3,4,-1,5,6,7,8,-1,9,10,11,12,-1,13,14,15,0};
  private static int len;
  private static int sqr;	

  public static void main(String[] args){
	//int[] start = {1,2,3,9,-1,7,8,0,11,-1,4,5,6,15,-1,14,12,13,10};
  	int[] start = {3,8,1,-1,7,5,0,-1,2,4,6}; //medium
        //int[] start = {8,6,7,-1,2,5,4,-1,3,0,1}; //hard
	  
	len = 9;
        sqr = (int) Math.sqrt(len);
	  
	LocalDateTime startTime = LocalDateTime.now();
    	Board board = new Board(start, 0);

	//check if currentBoard = end goal, exit if true
	while(!board.equals(goal)){
		//generate all possible board movements, then add to open ArrayList
		getMovements(board);
		
		//set currentBoard to board with lowest value
		Board temp = open.remove();
	
		//adds chosen board to the closed array
		closed.put(temp.getHash(), temp);
			     
		board = new Board(temp, temp);
	}
	  
	board = board.getParent();
	while(board != null) {
		path.add(board);
		board = board.getParent();
	}
	printPath();
	
	System.out.println(board.toString());
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


  public static void getMovements(Board board){
    int pos = board.getZ();
    int[] state = board.getState();
    int next = 0;
    int g = board.getG() + 1;
    int length = state.length;
    Board add;

    int northPos = pos - sqr - 1;
    int eastPos  = pos + 1;
    int southPos = pos + sqr + 1;
    int westPos  = pos - 1;

    if(northPos > -1 && state[northPos] != -1){
      next = state[northPos];
      int[] n = Arrays.copyOf(state, length);
      n[northPos] = 0;
      n[pos] = next;
      add = new Board(n,g,northPos, board);
      if(!isClosed(add)) open.add(add);
    }
    if(eastPos < length && state[eastPos] != -1){
      next = state[eastPos];
      int[] n = Arrays.copyOf(state, length);
      n[eastPos] = 0;
      n[pos] = next;
      add = new Board(n,g,eastPos, board);
      if(!isClosed(add)) open.add(add);
    }
    if(southPos < length && state[southPos] != -1){
      next = state[southPos];
      int[] n = Arrays.copyOf(state, length);
      n[southPos] = 0;
      n[pos] = next;
      add = new Board(n,g,southPos, board);
      if(!isClosed(add)) open.add(add);
    }
    if(westPos > -1 && state[westPos] != -1){
      next = state[westPos];
      int[] n = Arrays.copyOf(state, length);
      n[westPos] = 0;
      n[pos] = next;
      add = new Board(n,g,westPos, board);
      if(!isClosed(add)) open.add(add);
    }
}

private static boolean isClosed(Board board){
    String key = board.getHash();
    return closed.containsKey(key);
}
public static void printPath() {
	Collections.reverse(path);
	System.out.println("Start State:");
	System.out.println(path.get(0).toString());
	System.out.println("========");
	for(Board temp : path) {
		System.out.println("Step: " + temp.getG());
		System.out.println(temp.toString());
	}
	System.out.println("========\nGoal State:");
	System.out.println(path.get(path.size() - 1).toString());
}
}
