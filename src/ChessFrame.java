
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ChessFrame extends JFrame implements ActionListener{
  
  JButton reset;
  JLabel score;
  JLabel black;
  JLabel white;
  Container con;
  JPanel board;
  JLabel turnlbl;
  JPanel buttonPanel;
  Piece [][] x;
  int sPX;
  int sPY;
  int mPX;
  int whiteScore = 0;
  int blackScore = 0;
  int mPY;
  int single = 1;
  //true = white turn
  boolean turn = true;
  public ChessFrame(){
   super("Chess Board");
  }
  
  public void run(){
   
   reset();
   setVisible(true);
  
  }
  public void actionPerformed(ActionEvent e){
    System.out.println(e.getActionCommand());
    if (e.getSource() == reset){
     reset();
    } else{
      for(int i = 0; i < x.length; i++){
        for(int j = 0; j < x[i].length; j++){
          if (x[i][j].getActionCommand().equals(e.getActionCommand())){
            x[i][j].setClick(true);
          }
        }
      }
    if(check2Clicked()){
      System.out.println("Clicked 2");
      boolean flag = false;
      boolean flag2 = false;
      for(int i = 0; i < x.length; i++){
        for(int j = 0; j < x[i].length; j++){
          //System.out.println(x[i][j].getColor());
          if((x[i][j].isClicked() && x[i][j].getColor() == turn) && !(x[i][j] instanceof NullPiece) && !(x[i][j].isRemoved())){
            mPX = i;
            mPY = j;
            flag = true;
          } else if (x[i][j].isClicked()){
            sPX = i;
            sPY = j;
            flag2 = true;
          }
        }
      }
      if (flag && flag2 && canMove(mPX, mPY, sPX, sPY)){
          check();
          swap(mPX, mPY, sPX, sPY, true);
          System.out.println("swap");
      }
      for(int i = 0; i < x.length; i++){
        for(int j = 0; j < x[i].length; j++){
          x[i][j].setClick(false);
        }
      }
      flag = false;
      flag2 = false;
      if (!(turn)){
      if(single == 1){
        genMoves();
     }

    }
    
  }
    }
  }
  
  public void addButton(){
   buttonPanel = new JPanel();
   buttonPanel.setLayout(null);
   buttonPanel.setLocation(0, 600);
   buttonPanel.setSize(600, 117);
   reset = new JButton("Reset");
   reset.setLocation(170, 18);
   reset.setSize(260, 70);
   reset.addActionListener(this);
   buttonPanel.add(reset);
   buttonPanel.setBackground(Color.LIGHT_GRAY);
   con.add(buttonPanel);
   turnlbl = new JLabel();
   turnlbl.setLocation(65,0);
   turnlbl.setSize(150,90);
   turnlbl.setText("Turn: White");
   score = new JLabel();
   score.setLocation(470,0);
   score.setSize(150,90);
   score.setText("Pieces taken:");
   white = new JLabel();
   white.setLocation(430,25);
   white.setSize(150,90);
   white.setText("White: 0");
   black = new JLabel();
   black.setLocation(543,25);
   black.setSize(150,90);
   black.setText("Black: 0");
   buttonPanel.add(score);
   buttonPanel.add(turnlbl);
   buttonPanel.add(black);
   buttonPanel.add(white);
  }
  
  public void createBoard(){
    Object[] options = {"Multiplayer", "Single Player"};
    try{
    single = JOptionPane.showOptionDialog(this,
   "how would you like to play?",
   "Getting Started",
   JOptionPane.YES_NO_OPTION,
   JOptionPane.QUESTION_MESSAGE,
   new ImageIcon(ImageIO.read(new File("res/Black Pawn.png"))),     //do not use a custom Icon
   options,  //the titles of buttons
   options[0]);
    } catch (IOException e){
      e.printStackTrace();
    }
    System.out.println(single);
   setBounds(50, 50, 600, 717);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   con = this.getContentPane();
   con.setLayout(null);
   board = new JPanel();
   board.setLayout(null);
   board.setLocation(0,0);
   board.setSize(600,600);
   con.add(board);
  }
  
  public void addImage(){
    try{
    BufferedImage image = ImageIO.read(new File("res/Chess.png"));//"C:\\Users\\80010108\\Downloads\\Chess\\res\\Chess.png"));
    ImageIcon img = new ImageIcon(image);
    JLabel picLabel = new JLabel();
    picLabel.setSize(600,600);
    picLabel.setLocation(0,0);
    picLabel.setIcon(img);
    board.add(picLabel);
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  
  public void reset(){
    setVisible(false);
    getContentPane().removeAll();
    createBoard();
    addButton();
    //con.add(board);
    x = new Piece[][]{
      {new Rook(false), new Horse(false), new Bishop(false), new Queen(false), new King(false), new Bishop(false), new Horse(false), new Rook(false)},
      {new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false), new Pawn(false)},
      {new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece()},
      {new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece()},
      {new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece()},
      {new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece(),new NullPiece()},
      {new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true), new Pawn(true)},
      {new Rook(true), new Horse(true), new Bishop(true), new Queen(true), new King(true), new Bishop(true), new Horse(true), new Rook(true)}};
      
        for(int i = 0; i < x.length; i++){
          for(int j = 0; j < x[i].length; j++){
            if(x[i][j] instanceof Rook){
              if(x[i][j].getColor()){
                if(j > 4){
                  x[i][j].setActionCommand("White Rook 1");
                } else {
                  x[i][j].setActionCommand("White Rook 2");
                }
              } else {
                if(j > 4){
                  x[i][j].setActionCommand("Black Rook 1");
                } else {
                  x[i][j].setActionCommand("Black Rook 2");
                }
              }
            } else if(x[i][j] instanceof Pawn){
              if(x[i][j].getColor()){
                  x[i][j].setActionCommand("White Pawn " + j);
              } else {
                  x[i][j].setActionCommand("Black Pawn " + j);
              }
            } else if(x[i][j] instanceof Bishop){
              if(x[i][j].getColor()){
                if(j > 4){
                  x[i][j].setActionCommand("White Bishop 1");
                } else {
                  x[i][j].setActionCommand("White Bishop 2");
                }
              } else {
                if(j > 4){
                  x[i][j].setActionCommand("Black Bishop 1");
                } else {
                  x[i][j].setActionCommand("Black Bishop 2");
                }
              }
            } else if(x[i][j] instanceof Horse){
              if(x[i][j].getColor()){
                if(j > 4){
                  x[i][j].setActionCommand("White Horse 1");
                } else {
                  x[i][j].setActionCommand("White Horse 2");
                }
              } else {
                if(j > 4){
                  x[i][j].setActionCommand("Black Horse 1");
                } else {
                  x[i][j].setActionCommand("Black Horse 2");
                }
              }
            } else{
              x[i][j].setActionCommand(i + " " + j);
            }
            Point p = new Point();
            p = getCoordinates(i, j);
            board.add(x[i][j]);
            x[i][j].setLocation(p.x, p.y); 
            x[i][j].addActionListener(this);
            
          }
        }
        
        addImage();
        turn = true;
        setVisible(true);
        whiteScore = 0;
        blackScore = 0;
      }
  
  public void check(){
    for(int i = 0; i < x.length; i++){
      for(int j = 0; j < x[i].length; j++){
          Point p = new Point();
          p = getCoordinates(i, j);
          x[i][j].setLocation(p.x, p.y); 
      }
    }
  }
  
  public Point getCoordinates(int x, int y){
    Point p = new Point();
    p.x = (int)75*x;
    p.y = (int)75*y;
    return p;
  }
  
  public boolean check2Clicked(){
    int c = 0;
    for(int i = 0; i < x.length; i++){
      for(int j = 0; j < x[i].length; j++){
        if(x[i][j].isClicked()){
          c++;
        }
      }
    }
    if (c == 2){
      return true;
    }
    
   return false;
  }
  
  public void swap(int x1, int y1, int x2, int y2, boolean real){
          if(!(x[x2][y2] instanceof NullPiece) && !(x[x2][y2].isRemoved())){
            if(turn){
              whiteScore++;
            }else{
              blackScore++;
            }
            white.setText("White: " + whiteScore);
            black.setText("Black: " + blackScore);
          }
          Piece hold = x[x1][y1];
          x[x1][y1] = x[x2][y2];
          x[x2][y2] = hold;

          //System.out.println("Check");
          if (real){
           x[x1][y1].remove();
          check();
          turn = !turn;
          if(turn){
            turnlbl.setText("Turn: White");
          }else {
            turnlbl.setText("Turn: Black");
          }
          }
 
  }
  
  public void genMoves(){
    Piece [][] xTemp = x;
    ArrayList<Move> moves = new ArrayList<Move>();
    for(int i = 0; i < x.length; i++){
      for(int j = 0; j < x[i].length; j++){
        for(int g = 0; g < x.length; g++){
          for(int h = 0; h < x[g].length; h++){
            if(x[i][j].getColor() == turn && canMove(i, j, g, h) && x[i][j].isRemoved() == false){
              int value = 0;
              if(!(x[g][h].isRemoved())){
                value += x[g][h].getValue();
              }
              Move m = new Move(i, j, g, h, value);
              moves.add(m);
            }
          }
        }
      }
    }
    //System.out.println("moves");
    for(int z = 0; z < moves.size(); z++){
      //System.out.println(moves.get(i));
      swap(moves.get(z).getmPX(), moves.get(z).getmPY(), moves.get(z).getsPX(), moves.get(z).getsPY(), false);
      int [] vals= new int[49];
      int c = 0;
      for(int i = 0; i < x.length; i++){
      for(int j = 0; j < x[i].length; j++){
        for(int g = 0; g < x.length; g++){
          for(int h = 0; h < x[g].length; h++){
            if(x[i][j].getColor() == !turn && canMove(i, j, g, h) && x[i][j].isRemoved() == false){
              if(!(x[g][h].isRemoved())){
                vals[c] = x[g][h].getValue();
              }
              c++;
            }
          }
        }
      }
    }
      int min = 1;
      for(int i = 0; i < vals.length; i++){
        if(vals[i] < min){
         min = vals[i]; 
        }
      }
      moves.get(z).setValue(moves.get(z).getValue() + min);
      swap(moves.get(z).getsPX(), moves.get(z).getsPY(), moves.get(z).getmPX(), moves.get(z).getmPY(), false);
    }
    Move max = new Move(0,0,0,0,-1000);
    for(int m = 0; m < moves.size(); m++){
      if(moves.get(m).getValue() > max.getValue()){
        max = moves.get(m);
      }
    }
    System.out.println(max);
    ArrayList<Move> s = new ArrayList<Move>();
    for(int m = 0; m < moves.size(); m++){
      if(moves.get(m).getValue() == max.getValue()){
        s.add(moves.get(m));
      }
    }
    int num = (int) (Math.random() * s.size());
    System.out.println(s.size());
    System.out.println(num);
    max = s.get(num);
    System.out.println(max);
    swap(max.getmPX(), max.getmPY(), max.getsPX(), max.getsPY(), true);
    
  }
  
  public boolean canMove(int mPX, int mPY, int sPX, int sPY){
//    System.out.println(mPX);
//    System.out.println(mPY);
//    System.out.println(sPX);
//    System.out.println(sPY);
    if(x[sPX][sPY].getColor() != x[mPX][mPY].getColor() || x[sPX][sPY].isRemoved()){
    if (x[mPX][mPY] instanceof Bishop && x[mPX][mPY].move(mPX, mPY, sPX, sPY)){
          boolean inTheWay = false;
          if(mPX > sPX && mPY < sPY){
            for(int i = 1; i <= mPX-sPX-1; i++){
              if(sPX + i > -1 && sPX + i < 8 && sPY - i < 8 & sPY - i> -1 && !(x[sPX+i][sPY-i] instanceof NullPiece) && !(x[sPX+i][sPY-i].isRemoved())){
                return false;
              }
            }
          } else if(mPX < sPX && mPY > sPY){
            for(int i = 1; i <= sPX-mPX-1; i++){
              if(sPX - i > -1 && sPX - i < 8 && sPY + i < 8 & sPY + i> -1 && !(x[sPX-i][sPY+i] instanceof NullPiece) && !(x[sPX-i][sPY+i].isRemoved())){
                return false;
              }
            }
          } else if(mPX < sPX && mPY < sPY){
            for(int i = 1; i <= sPX-mPX-1; i++){
              if(sPX - i > -1 && sPX - i < 8 && sPY - i < 8 & sPY - i> -1 && !(x[sPX-i][sPY-i] instanceof NullPiece) && !(x[sPX-i][sPY-i].isRemoved())){
                return false;
              }
            }
          } else if(mPX > sPX && mPY > sPY){
            for(int i = 1; i <= mPX-sPX-1; i++){
              if(sPX + i > -1 && sPX + i < 8 && sPY + i < 8 & sPY + i> -1 && !(x[sPX+i][sPY+i] instanceof NullPiece) && !(x[sPX+i][sPY+i].isRemoved())){
                return false;
              }
            }
          }
    } else if (x[mPX][mPY] instanceof Rook && x[mPX][mPY].move(mPX, mPY, sPX, sPY)){
          boolean inTheWay = false;
          if(mPY < sPY){
            for(int i = 1; i <= sPY-mPY-1; i++){
              if(!(x[sPX][sPY-i] instanceof NullPiece) && !(x[sPX][sPY-i].isRemoved())){
                return false;
              }
            }
          } else if(mPX < sPX){
            for(int i = 1; i <= sPX-mPX-1; i++){
              if(!(x[sPX-i][sPY] instanceof NullPiece) && !(x[sPX-i][sPY].isRemoved())){
                return false;
              }
            }
          } else if(mPX > sPX){
            for(int i = 1; i <= mPX-sPX-1; i++){
              if(!(x[sPX+i][sPY] instanceof NullPiece) && !(x[sPX+i][sPY].isRemoved())){
                return false;
              }
            }
          } else if(mPY > sPY){
            for(int i = 1; i <= mPY-sPY-1; i++){
              if(!(x[sPX][sPY+i] instanceof NullPiece) && !(x[sPX][sPY+i].isRemoved())){
                return false;
              }
            }
          }
        } else if (x[mPX][mPY] instanceof Queen && x[mPX][mPY].move(mPX, mPY, sPX, sPY)){
          boolean inTheWay = false;
          if(mPX > sPX && mPY < sPY){
            for(int i = 1; i <= mPX-sPX-1; i++){
              if(!(x[sPX+i][sPY-i] instanceof NullPiece) && !(x[sPX+i][sPY-i].isRemoved())){
                return false;
              }
            }
          } else if(mPX < sPX && mPY > sPY){
            for(int i = 1; i <= sPX-mPX-1; i++){
              if(!(x[sPX-i][sPY+i] instanceof NullPiece) && !(x[sPX-i][sPY+i].isRemoved())){
                return false;
              }
            }
          } else if(mPX < sPX && mPY < sPY){
            for(int i = 1; i <= sPX-mPX-1; i++){
              if(sPX - i > -1 && sPX - i < 8 && sPY - i < 8 & sPY - i> -1 && !(x[sPX-i][sPY-i] instanceof NullPiece) && !(x[sPX-i][sPY-i].isRemoved())){
                return false;
              }
            }
          } else if(mPX > sPX && mPY > sPY){
            for(int i = 1; i <= mPX-sPX-1; i++){
              if(!(x[sPX+i][sPY+i] instanceof NullPiece) && !(x[sPX+i][sPY+i].isRemoved())){
                return false;
              }
            }
          } else if(mPY < sPY){
            for(int i = 1; i <= sPY-mPY-1; i++){
              if(!(x[sPX][sPY-i] instanceof NullPiece) && !(x[sPX][sPY-i].isRemoved())){
                return false;
              }
            }
          } else if(mPX < sPX){
            for(int i = 1; i <= sPX-mPX-1; i++){
              if(!(x[sPX-i][sPY] instanceof NullPiece) && !(x[sPX-i][sPY].isRemoved())){
                return false;
              }
            }
          } else if(mPX > sPX){
            for(int i = 1; i <= mPX-sPX-1; i++){
              if(!(x[sPX+i][sPY] instanceof NullPiece) && !(x[sPX+i][sPY].isRemoved())){
                return false;
              }
            }
          } else if(mPY > sPY){
            for(int i = 1; i <= mPY-sPY-1; i++){
              if(!(x[sPX][sPY+i] instanceof NullPiece) && !(x[sPX][sPY+i].isRemoved())){
                return false;
              }
            }
          }
        }else if (x[mPX][mPY] instanceof Pawn){
          if(x[mPX][mPY].move(mPX,mPY,sPX,sPY) && (x[sPX][sPY] instanceof NullPiece || x[sPX][sPY].isRemoved())){
            return true;
          } else if(x[mPX][mPY].getColor() && (mPX == sPX+1 && (mPY == sPY+1 || mPY == sPY-1)) && (!(x[sPX][sPY] instanceof NullPiece) || (!(x[sPX][sPY].isRemoved())))){
            return true;
          } else if(x[mPX][mPY].getColor() == false && (mPX == sPX-1 && (mPY == sPY+1 || mPY == sPY-1)) && (!(x[sPX][sPY] instanceof NullPiece) || !(x[sPX][sPY].isRemoved()))){
            return true;
          } else{
            return false;
          }
        }else if(!(x[mPX][mPY].move(mPX,mPY,sPX,sPY))){
          return false;
        }
     return true;
  }
    return false;
  }
   public static void main(String[] args){
    ChessFrame x = new ChessFrame();
    x.run();
  }   
  }