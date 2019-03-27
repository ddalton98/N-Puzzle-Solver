import javax.swing.*;

public class Main {
  private static ArrayList<Board> open = new ArrayList<Board>();
	
    public static void main(String[] args){
        System.out.println(inputWindow());
    }

    private static String inputWindow(){
        String msg = "Please input a 9 digits(0-9) with a space separating each";
        String pattern1 = "^(?!.*(.).*\\1)*\\d{10}$";
        String pattern2 = "^([0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1}\\s{1}[0-9]{1})\\s*";
        String rawInput = JOptionPane.showInputDialog(null, msg, "8 Puzzle", JOptionPane.QUESTION_MESSAGE);
        String checkStr = rawInput.replaceAll("\\s", "");
        if(checkStr.matches(pattern1) && rawInput.matches(pattern2)){
            return rawInput;
        }else {
            JOptionPane.showMessageDialog(null, "Error: Entry format incorrect", "Error", JOptionPane.ERROR_MESSAGE);
            return inputWindow();
        }
    }
    
    public static int[][] convertSqr(String in){
	  int temp [][] = {{0,0,0},{0,0,0},{0,0,0}};
	  
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
	
  public static void getMovements(Board a){
	  int[][] table = a.getTable();
	  int pos0[] = getPos0(table); 
	  int x = pos0[0];
	  int y = pos0[1];
	  
	  int west  = y + 1;
	  int east  = y - 1;
	  int north = x - 1;
	  int south = x + 1;
	  
	  if(!(west > 2)){ //west
		  int next = table[x][west];
		  System.out.print("West:  " + next);
		  
		  int[][] b = a.getTable();
		  b[x][y] = next;
		  b[x][west] = 0;
		  
		  Board temp = new Board(b, goal, 0);
		  System.out.println("\tH: " + temp.getValue());
		  open.add(temp);
	  }
	  
	  if(!(east < 0)){ //east
		  int next = table[x][east];
		  System.out.print("East:  " + next);
		  
		  int[][] b = a.getTable();
		  b[x][y] = next;
		  b[x][east] = 0;
		  
		  Board temp = new Board(b, goal, 0);
		  System.out.println("\tH: " + temp.getValue());
		  open.add(temp);
	  }
	  
	  if(!(south > 2)){ //south
		  int next = table[south][y];
		  System.out.print("South: " + next);
		  
		  int[][] b = a.getTable();
		  b[x][y] = next;
		  b[south][y] = 0;
		  
		  Board temp = new Board(b, goal, 0);
		  System.out.println("\tH: " + temp.getValue());
		  open.add(temp);
	  }
	  
	  if(!(north < 0)){ //north
		  int next = table[north][y];
		  System.out.print("North: " + next);
		  
		  int[][] b = a.getTable();
		  b[x][y] = next;
		  b[north][y] = 0;
		  
		  Board temp = new Board(b, goal, 0);
		  System.out.println("\tH: " + temp.getValue());
		  open.add(temp);
	  }
  }
}
