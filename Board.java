public class Board{
  private int g;
  private int value;
  private int [][] arr;
  private int [][] goal;


  public Board(int arr[][], int goal[][], int g){
    this.g = g;
    this.arr = arr;
	this.goal = goal;
    this.value = g + getH();
  }

  private int getH(){
    int count = 0;
	
	for(int x = 0; x < goal.length; x++){
		for(int y = 0; y < goal[x].length; y++){
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
}
