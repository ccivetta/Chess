import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Queen extends Piece{
  public Queen(boolean color){
    super(color);
    addImage();
  }
  
  public boolean move(int mPX, int mPY, int sPX, int sPY){
   for(int i = 0; i < 8; i++){
     if(((mPX == sPX+i && mPY == sPY) || (mPX == sPX-i && mPY == sPY) || (mPY == sPY-i && mPX == sPX) || (mPY == sPY+i && mPX == sPX)) || ((mPX == sPX+i || mPX == sPX-i) && (mPY == sPY+i || mPY == sPY-i))){
       return true;
     }
    }
   return false;
  }
  
    public void addImage(){
       pic = new ImageIcon();
    try{
      if (!color){
        BufferedImage image = ImageIO.read(new File("res/Black Queen.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\Black Queen.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        setActionCommand("Black Queen");
        value = -400;
      } else {
        BufferedImage image = ImageIO.read(new File("res/White Queen.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\White Queen.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        setActionCommand("White Queen");
        value = 400;
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}