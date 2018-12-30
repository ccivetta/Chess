
public class NullPiece extends Piece{
  public NullPiece(){
    super(false);
    removed = true;
    value = 0;
  }

  public boolean move(int mPX, int mPY, int sPX, int sPY){
    return false;
  }
  public void addImage(){
    
  }
  
}