*	CS4006 A* Project
*	Alan Finnin		17239621
*	Daniel Dalton	17219477
*	Stephen Cliffe 17237157
*/


public class Main {
    public static void main(String[] args){
		string input = inputWindow();
        //convertSqr(String input);
    }

    private static String inputWindow(){
		boolean correct = true;
        String msg = "Please input a 9 digits(0-9) with a space separating each";
        String pattern = "^([0-9]{1}\\s+){9}[0-9]";
        String rawInput = JOptionPane.showInputDialog(null, msg, "8 Puzzle", JOptionPane.QUESTION_MESSAGE);
        String checkStr = rawInput.replaceAll("\\s", "");
        if(rawInput.matches(pattern)){
			String[] strInput = rawInput.split(" ");
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
