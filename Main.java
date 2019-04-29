import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Alan Finnin     17239621
 * Daniel Dalton   17219477
 * Stephen Cliffe  17237157
 **/
public class Main {
	private static PriorityQueue<Board> open = new PriorityQueue<>();
	private static HashMap<String, Board> closed = new HashMap<>();
	private static ArrayList<Board> path = new ArrayList<>();
	private static int[] start;
	private static int len;
	private static int sqr;

	public static void main(String[] args) {
		Board.setGoal(convertSqr(inputWindow("GOAL")));
		start = convertSqr(inputWindow("START"));
		
		boolean exceedMax = false;

		LocalDateTime startTime = LocalDateTime.now();
		Board board = new Board(start, 0);

		int maxIterations = maxIterations(len);
		
		//check if currentBoard = end goal, exit if true
		while (!board.equals(Board.getGoal())) {
			//generate all possible board movements, then add to open ArrayList
			getMovements(board);
			//set currentBoard to board with lowest value
			Board temp = open.remove();

			//adds chosen board to the closed array
			closed.put(temp.getHash(), temp);

			board = new Board(temp, temp);

			if(closed.size() >= maxIterations){
				exceedMax = true;
				break;
			}
		}
		//follows the trail of parent boards to determine the path taken
		if(exceedMax){
			System.out.println("Exceeded the max number of valid iterations.\nPuzzle must be unsolvable");
		}else {
			board = board.getParent();
			while (board != null) {
				path.add(board);
				board = board.getParent();
			}
			printPath();

			LocalDateTime endTime = LocalDateTime.now();
			Duration duration = Duration.between(startTime, endTime);

			System.out.println("Time taken: " + duration.getSeconds() + "." + duration.getNano() / 1000000 + "s");
			System.out.println("Iterations: " + closed.size());
		}
	}

	private static String inputWindow(String state){
		String msg = "Please input a square puzzle(3x3, 4x4, etc.)\n using numbers" + state + " with a space separating each";
		String pattern = "^(\\d{1,2}\\s+){8,}\\d{1,2}(?=\\s)*";
		String rawInput = JOptionPane.showInputDialog(null, msg, "8 Puzzle", JOptionPane.QUESTION_MESSAGE);

		if (rawInput.matches(pattern)) {
			String[] strInput = rawInput.split("\\s+");
			int inputArr[] = new int[strInput.length];
			for (int i = 0; i < strInput.length; i++) {
				inputArr[i] = Integer.parseInt(strInput[i]);
			}

			Arrays.sort(inputArr);

			for (int i = 0; i < inputArr.length; i++) {
				if (inputArr[i] != i) {
					JOptionPane.showMessageDialog(null, "Entry format incorrect: Duplicate Digit.", "Error!", JOptionPane.ERROR_MESSAGE);
					return inputWindow(state);
				}
			}
			//Test for squareness
			double sr = Math.sqrt(inputArr.length);

			if ((sr - Math.floor(sr)) == 0) {
				len = inputArr.length;
				sqr = (int) Math.sqrt(len);
				return rawInput;
			}
			JOptionPane.showMessageDialog(null, "Entry format incorrect: Not a perfect square puzzle\nI.E. 3x3, 4x4 etc.", "Error", JOptionPane.ERROR_MESSAGE);
			return inputWindow(state);
		} else {
			JOptionPane.showMessageDialog(null, "Entry format incorrect, Tip: Keep cats away from the keyboard.", "Error", JOptionPane.ERROR_MESSAGE);
			return inputWindow(state);
		}
	}


	public static void getMovements(Board board) {
		int pos = board.getZ();
		int[] state = board.getState();
		int g = board.getG() + 1;
		int length = state.length;

		int northPos = pos - sqr - 1;
		int eastPos = pos + 1;
		int southPos = pos + sqr + 1;
		int westPos = pos - 1;

		if (northPos > -1 && state[northPos] != -1) {
			getDirection(board, g, northPos);
		}
		if (eastPos < length && state[eastPos] != -1) {
			getDirection(board, g, eastPos);
		}
		if (southPos < length && state[southPos] != -1) {
			getDirection(board, g, southPos);
		}
		if (westPos > -1 && state[westPos] != -1) {
			getDirection(board, g, westPos);
		}
	}

	private static void getDirection(Board board, int g, int dirPos){
		int[] state = board.getState();
		int next = state[dirPos];
		int[] n = Arrays.copyOf(state, state.length);
		n[dirPos] = 0;
		n[board.getZ()] = next;
		Board add = new Board(n, g, dirPos, board.getParent());
		if (!isClosed(add)) open.add(add);
	}

	private static boolean isClosed(Board board) {
		String key = board.getHash();
		return closed.containsKey(key);
	}

	public static int[] convertSqr(String in) {
		int temp[] = new int[len + (sqr-1)];

		String split[] = in.split("\\s+");
		int count = 0;

		for (int x = 0; x < temp.length; x++) {
			if(count > 0) {
				if ((x+1)%(sqr+1) == 0) {
					temp[x] = -1;
				} else {
					temp[x] = Integer.parseInt(split[count]);
					count++;
				}
			}else{
				temp[x] = Integer.parseInt(split[count]);
				count++;
			}
		}
		return temp;
	}

	private static int maxIterations(int n){
		int result = 1;
		for(int i = 1; i <= n; i++){
			result *= i;
		}
		return result/2;
	}

	public static void printPath() {
		Collections.reverse(path);
		System.out.println("Start State:");
		System.out.println(new Board(start, 0).toString());
		System.out.println("========");
		for(Board temp : path) {
			System.out.println("Step: " + temp.getG());
			System.out.println(temp.toString());
		}
		System.out.println("========\nGoal State:");
		System.out.println(path.get(path.size() - 1).toString());
	}
}
