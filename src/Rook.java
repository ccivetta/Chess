import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Rook extends Piece{
  public Rook(boolean color){
    super(color);
    addImage();
  }
  
  public boolean move(int mPX, int mPY, int sPX, int sPY){
    for(int i = 0; i < 8; i++){
      if((mPX == sPX+i && mPY == sPY) || (mPX == sPX-i && mPY == sPY) || (mPY == sPY-i && mPX == sPX) || (mPY == sPY+i && mPX == sPX)){
        return true;
      }
    }
    return false;
  }

  
    public void addImage(){
      pic = new ImageIcon();
    try{
      if (!color){
        BufferedImage image = ImageIO.read(new File("res/Black Rook.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\Black Rook.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = -40;
      } else {
        BufferedImage image = ImageIO.read(new File("res/White Rook.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\White Rook.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        value = 40;
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}