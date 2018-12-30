import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
public class Bishop extends Piece{
  public Bishop(boolean color){
    super(color);
    addImage();
    
  }
  
  public boolean move(int mPX, int mPY, int sPX, int sPY){
    for(int i = 0; i < 8; i++){
       if((mPX == sPX+i || mPX == sPX-i) && (mPY == sPY+i || mPY == sPY-i)){
         return true;
       }
    }
    return false;
  }
  public void addImage(){
     pic = new ImageIcon();
    try{
      if (!color){
        BufferedImage image = ImageIO.read(new File("res/Black Bishop.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\Black Bishop.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = -40;
      } else {
        BufferedImage image = ImageIO.read(new File("res/White Bishop.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\White Bishop.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = 40;
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}