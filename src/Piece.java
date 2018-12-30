import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public abstract class Piece extends JButton {
  boolean color = true;
  boolean click = false;
  boolean removed = false;
  ImageIcon pic;
  int value;
  // true = white
  public Piece(Boolean color){
    this.color = color;
    setSize(60,60);
    setOpaque(false);
    setContentAreaFilled(false);
    setBorderPainted(false);
   
  }
  public ImageIcon getImage(){
    return pic;
  }
  
  public String toString(){
    return this.getActionCommand();
  }

  public void setClick(boolean c){
    click = c;
  }
  
  public boolean getColor(){
   return color; 
  }
  
  public boolean isRemoved(){
    return removed;
  }
  
  public void remove(){
    removed = true;
    setIcon(null);
  }
  
  public boolean isClicked(){
    return click;
  }
  
  public int getValue(){
    return value;
  }
  public abstract boolean move(int mPX, int mPY, int sPX, int sPY);
  public abstract void addImage();
  
}