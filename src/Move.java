public class Move{
  int value;
  int mPX;
  int mPY; 
  int sPX;
  int sPY;
  public Move(int mPX,int mPY,int sPX,int sPY,int v){
    this.mPX = mPX;
    this.mPY = mPY;
    this.sPX = sPX;
    this.sPY = sPY;
    value = v;
  }
  public int getValue(){
    return value;
  }
  public int getmPX(){
    return mPX;
  }
  public int getmPY(){
    return mPY;
  }
  public int getsPX(){
    return sPX;
  }
  public int getsPY(){
    return sPY;
  }
  
  public String toString(){
    return mPX + " " + mPY + " " + sPX + " " + sPY + " " + value;
  }
  
  public void setValue(int v){
    value = v;
  }
  
}