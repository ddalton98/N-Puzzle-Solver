public class Board{
  private int g;
  private int value;
  private int [][] arr;
  private int [][] goal;

  public Board(int arr[][], int goal[][], int g){
    this.g = g;
    this.arr = arr;
	  this.goal = goal;
    this.value = g + this.getH();
  }

  private int getH(){
    int count = 0;

	for(int x = 0; x < goal.length; x++){
		for(int y = 0; y < goal.length; y++){
			if(arr[x][y] != goal[x][y]) count++;
		}
	  }
    return count;
  }

  public boolean finish(){
	  boolean ret = false;

	  if(this.getH() == 0) ret = true;

	  return ret;

  }
  
  public boolean isEqual(Board a){
	  int[][] temp = a.getTable();
	  int count = 0;
	  
	  for(int x = 0; x < arr.length; x++){
		for(int y = 0; y < arr.length; y++){
			if(arr[x][y] == temp[x][y]) count++;
		}
	  }
	  
	  if(count == arr.length*arr.length){
		  return true;
	  } else return false;
  }

  public int[][] getTable(){
    return arr;
  }

  public int getValue(){
    return value;
  }

  public int getG(){
    return g;
  }

  public void setG(int val){
	  g = val;
  }

  public void printBoard(){
	  for(int i = 0; i < arr.length; i++){
		  for(int j = 0; j < arr.length; j++){
			  System.out.print(arr[i][j] + " ");
		  }
		  System.out.println("");
	  }
	  System.out.println("");
  }
}
