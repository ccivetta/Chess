import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Pawn extends Piece{
  public Pawn(boolean color){
    super(color);
    addImage();
  }
  
  public boolean move(int mPX, int mPY, int sPX, int sPY){
    if(color){
      if(mPX == sPX+1 && mPY == sPY){
         return true;
      } else if(mPX == 6 && mPX == sPX+2 && mPY == sPY){
       return true; 
      }
    } else {
       if(mPX == sPX-1 && mPY == sPY){
         return true;
      } else if(mPX == 1 && mPX == sPX-2 && mPY == sPY){
       return true; 
      }
    }
       return false;
  }
  
  
    public void addImage(){
       pic = new ImageIcon();
    try{
      if (!color){
        BufferedImage image = ImageIO.read(new File("res/Black Pawn.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\Black Pawn.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = -10;
      } else {
        BufferedImage image = ImageIO.read(new File("res/White Pawn.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\White Pawn.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = 10;
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}