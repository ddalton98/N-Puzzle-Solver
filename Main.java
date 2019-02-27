import javax.swing.*;

public class Main {
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
}
