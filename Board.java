public class Board{
  private static int[][] goal;
  private int g;
  private int value;
  private int [][] arr;


  public Board(int arr[][], int g){
    this.g = g;
    this.arr = arr;
    this.value = g + getH(this.arr);
  }

  private int getH(int[][] arr){
    int count;

    for(int x = 0; x < arr.length; x++){
      if(arr[x] != goal[x]){
        count++;
      }
    }

    return count;
  }

  public boolean equals(int[][] check){
    int count;

    for(int x = 0; x < check.length; x++){
      if(arr[x] != check[x]){
        count++;
      }
    }

    if(count == 0){
      return true;
    } else {
      return false;
    }
  }

  public int[][] getBoard(){
    reutrn arr;
  }

  public int getValue(){
    return value;
  }

  public int getG(){
    return g;
  }
}
