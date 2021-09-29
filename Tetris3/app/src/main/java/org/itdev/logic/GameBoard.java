package org.itdev.logic;

import android.graphics.Color;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private boolean blockBoolean[][] = new boolean[10][20];
    private TextView block[][] = new TextView[10][20];
    private final Random random = new Random();
    private static final int NUMBER_PIECES = 7;
    private ArrayList<Piece> blocksList = new ArrayList<>();
    int width =9;
    int height= 19;
    boolean status = true ;

    public GameBoard()
    {
        blocksList.add(new Piece(random.nextInt(NUMBER_PIECES)+1)); // pieza actual
        blocksList.add(new Piece(random.nextInt(NUMBER_PIECES)+1)); // pieza siguiente
      //  getCurrentPiece().initShapes();
    }
    public ArrayList<Piece> getBlocksList()
    {
        return blocksList;
    }
    public Piece getCurrentPiece()
    {
        return blocksList.get(blocksList.size()-2);
    }
    public Piece getForwardPiece()
    {
        return blocksList.get(blocksList.size()-1);
    }
    public boolean pieceMovement(TextView blocke [][], Piece currentPiece, int x, int y)
    {
        for (int row = 0; row <currentPiece.getWidth() ; row++) {
            for (int col = 0; col < currentPiece.getHeight(); col++) {
                if (currentPiece.getShape()[col][row] == 1) {
                     x = col + getCurrentPiece().getX();
                      y = row + getCurrentPiece().getY()+1;


                    if (y == this.height || block[x][y].getHint().equals("1")) {
                        return false;

                    }
                }
            }

        }
            return true;

    }
    public boolean canMoveDown(Piece currentPiece, TextView block[][])
    {

        if(pieceMovement(block, currentPiece, currentPiece.getX(), currentPiece.getY()+1)== true)
        {
            return true;
        }
        return false;
    }
    public boolean canMoveRight(Piece currentPiece, TextView block[][])
    {

        if(pieceMovement(block, currentPiece, currentPiece.getX()+1, currentPiece.getY())== true)
        {
            return true;
        }
        return false;
    }

    public boolean[][] getBlockBoolean() {
        return blockBoolean;
    }

    public void setBlockBoolean(boolean[][] blockBoolean) {
        this.blockBoolean = blockBoolean;
    }

    public  boolean checkBotton(TextView block [][])
    {
        if(getCurrentPiece().getBottonEdge() == 18)
        {
            return false;
        }
        int[][] shape = getCurrentPiece().getShape();
        int w = getCurrentPiece().getWidth();
        int h = getCurrentPiece().getHeight();
        for (int col = 0; col <w ; col++) {
            for (int row = h-1; row >=0; row--) {
               if(shape[row][col] != 0)
               {
                   int x = col + getCurrentPiece().getX();
                   int y = row + getCurrentPiece().getY()+1;
                   if(y<0) break;

                   if(block[x][y].getBackground() != null )return false;
                   break;
               }
            }
        }
        return true;
    }
   public boolean checkLeft()
   {
    if(getCurrentPiece().getLeftEdge() == 0)return false;
     return true;
   }
   public  boolean checkRight()
   {
    if(getCurrentPiece().getRightEdge() == 8)return false;

    return true;
   }

}
