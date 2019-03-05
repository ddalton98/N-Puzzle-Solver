public class GameCreationAndMovement
{
	static int[][] gameBoard = {{1,9,3},{4,5,6},{4,8,0}};
	//{{5,2,6},{1,0,3},{4,8,7}};
	/* public static void main(String args[])
	{
		printBoard();
		//move(1, 2, 'n');
		//west(1, 1);
		System.out.println();
		printBoard();
		System.out.println(isGameOver());
		System.out.println(numberWrong());
	} */
	
	/*	
	*	Will work with 16x16 && 9x9 as long as r == c
	*/
	public static void printBoard()
	{
		for(int r = 0; r < gameBoard.length; r++) //rows
			for(int c = 0; c < gameBoard.length; c++) //columns
			{
				System.out.print(gameBoard[r][c] + "\t");
				if(c == gameBoard.length-1) System.out.print("\n\n");
			}
	}
	
	public static void move(int r, int c,char direction) //--r |c
	{
		switch(direction) {
			case 'n' :
				north(r, c); break;
			case 's' :
				south(r, c); break;
			case 'e' :
				east(r, c); break;
			case 'w' :
				west(r, c); break;
		}
	}
	public static void north(int r, int c)
	{
		int tempHolder = gameBoard[r][c];
		gameBoard[r][c] = gameBoard[r-1][c];
		gameBoard[r-1][c] = tempHolder;
	}
	public static void south(int r, int c)
	{
		int tempHolder = gameBoard[r][c];
		gameBoard[r][c] = gameBoard[r+1][c];
		gameBoard[r+1][c] = tempHolder;
	}
	public static void east(int r, int c)
	{
		int tempHolder = gameBoard[r][c];
		gameBoard[r][c] = gameBoard[r][c+1];
		gameBoard[r][c+1] = tempHolder;
	}
	public static void west(int r, int c)
	{
		int tempHolder = gameBoard[r][c];
		gameBoard[r][c] = gameBoard[r][c-1];
		gameBoard[r][c-1] = tempHolder;
	}
	
	public static boolean isGameOver()
	{
		int numberOfWrong = 0;
		int count = 1;
		for(int r = 0; r < gameBoard.length; r++)
			for(int c = 0; c < gameBoard.length; c++)
			{
				if(count != ((gameBoard.length*gameBoard.length))){
					if(!(gameBoard[r][c] == count)) return false;
					count++;
				}
				else{
					if(gameBoard[r][c] != 0) return false;
				}
			}
		return true;
	}
	public static int numberWrong()
	{
		int numberOfWrong = 0;
		int count = 1;
		for(int r = 0; r < gameBoard.length; r++)
			for(int c = 0; c < gameBoard.length; c++)
			{
				if(count != ((gameBoard.length*gameBoard.length))){
					if(!(gameBoard[r][c] == count)) numberOfWrong++;
					count++;
				}
				else{
					if(gameBoard[r][c] != 0) numberOfWrong++;
				}
			}
		return numberOfWrong;
	}
}
