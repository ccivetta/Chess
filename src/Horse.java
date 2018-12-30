import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Horse extends Piece{
  public Horse(boolean color){
    super(color);
    addImage();
  }
  
  public boolean move(int mPX, int mPY, int sPX, int sPY){
    if((mPX == sPX+2 && mPY == sPY+1) || (mPX == sPX+2 && mPY == sPY-1) || (mPX == sPX-2 && mPY == sPY+1) || (mPX == sPX-2 && mPY == sPY-1) || (mPX == sPX+1 && mPY == sPY+2) || (mPX == sPX+1 && mPY == sPY-2) || (mPX == sPX-1 && mPY == sPY+2) || (mPX == sPX-1 && mPY == sPY-2)){
      return true;
    }
    return false;
  }

  
    public void addImage(){
       pic = new ImageIcon();
    try{
      if (!color){
        BufferedImage image = ImageIO.read(new File("res/Black Horse.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\Black Horse.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = -40;
      } else {
        BufferedImage image = ImageIO.read(new File("res/White Horse.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\White Horse.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = 40;
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}