import javax.swing.*;
import java.util.*;

/**
*	CS4006 A* Project
*	Alan Finnin		17239621
*	Daniel Dalton	17219477
*	Stephen Cliffe 17237157
*/


public class Main {
    public static void main(String[] args){
		String input = inputWindow();
        //convertSqr(String input);
		//5 6 4 7 0 3 1 2 8
		//5 6 4 7 0 3 1 2 8 9 10 11 12 13 14 15
    }

    private static String inputWindow(){
		boolean correct = true;
        String msg = "Please input a 9 digits(0-9) with a space separating each";
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
					return inputWindow();
				}
			}
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
}
