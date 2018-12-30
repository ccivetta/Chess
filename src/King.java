import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
public class King extends Piece{
  public King(boolean color){
    super(color);
    addImage();
  }
  
  public boolean move(int mPX, int mPY, int sPX, int sPY){
    if((mPX == sPX+1 && mPY == sPY) || (mPX == sPX-1 && mPY == sPY) || (mPX == sPX && mPY == sPY+1) || (mPX == sPX && mPY == sPY-1) || (mPX == sPX+1 && mPY == sPY+1) || (mPX == sPX+1 && mPY == sPY-1) || (mPX == sPX-1 && mPY == sPY+1) || (mPX == sPX-1 && mPY == sPY-1)){
      return true;
    }
    return false;
  }
  
  public void remove(){
    super.remove();
    JFrame  win= new JFrame("Winner");
    JPanel pane = new JPanel();
    pane.setLocation(0,0);
    pane.setSize(200,200);
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    win.setLayout(null);
    win.setLocation(200,200);
    win.setSize(200,200);
    JLabel winlbl = new JLabel();
    winlbl.setLocation(0, 0);
    winlbl.setSize(200,200);
    winlbl.setText("YOU WIN!");
    winlbl.setFont(new Font("Serif", Font.BOLD, 25));
    pane.setBackground(Color.GREEN);
    win.setVisible(true);
    win.add(pane);
    pane.add(winlbl);
  }
  
    public void addImage(){
       pic = new ImageIcon();
    try{
      if (!color){
        BufferedImage image = ImageIO.read(new File("res/Black King.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\Black King.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        setActionCommand("Black King");
        value = -900;
      } else {
        BufferedImage image = ImageIO.read(new File("res/White King.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\White King.png"));
        pic = new ImageIcon(image);
        setIcon(pic);
        setActionCommand("White King");
        value = 900;
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}
