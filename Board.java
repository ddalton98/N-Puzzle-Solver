public class Board implements Comparable<Board>{
  private int g;
  private int value;
  private int[] state;
  private int posZ;
  private Board parent;

  public Board(int state[], int g, int posZ, Board parent){
    this.g = g;
    this.state = state;
    this.posZ = posZ;
    value = this.getH() + g;
    this.parent = parent;
  }
  public Board(int state[], int g){
    this.g = g;
    this.state = state;
    this.posZ = this.getPosZ(state);
    value = this.getH() + g;
  }	
  public Board(Board b, Board parent){
    this.g = b.getG();
    this.state = b.getState();
    this.posZ = this.getPosZ(state);
    value = this.getH() + g;
    this.parent = parent;
  }
	
  private int getH(){
    int count = 0;
	  
    for(int i = 0; i < state.length; i++){
      if(state[i] != Main.goal[i]){
        count++;
      }
    }
    return count;
  }
private int getPosZ(int[] arr){
    for(int i = 0; i < arr.length; i++){
      if(arr[i] == 0) return i;
    }
    return -1;
  }

  public boolean equals(int[] check){
    return Arrays.equals(state, check);
  }

  @Override
  public int compareTo(Board board){
    return this.getVal() - board.getVal();
  }

  public String toString(){
    String res = "";
    for(int i = 0; i < state.length; i++){
      if(state[i] != -1){
        res += state[i] + " ";
      } else {
        res += "\n";
      }
    }
    return res;
  }

  public int getVal(){
    return value;
  }

  public int getZ(){
    return posZ;
  }

  public int getG(){
    return g;
  }

  public int[] getState(){
    return Arrays.copyOf(state, state.length);
  }
}
