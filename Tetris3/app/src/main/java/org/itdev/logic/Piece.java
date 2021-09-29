package org.itdev.logic;

import android.graphics.Color;

import java.util.Currency;

public class Piece {

 private int[][] shape;
 private int color;
 private int x, y;
 private int[][][]shapes;
 private int currentRotation;

 public Piece( int opc){

  x= 4;
  y= 0;
    switch(opc)
    {
     case 1: //J
      this.shape= new int[][]{{ 0, 1}, {0, 1}, {1, 1}};
      this.color = Color.YELLOW;
      //initShapes();
      break;
     case 2: //O
      this.shape = new int[][]{{1, 1 }, {1, 1 }, {0,0}};
      this.color = Color.MAGENTA;
      //initShapes();
      break;
     case 3: // L
      this.shape = new int[][]{{1,0},{1,0},{1,1}};
      this.color = Color.CYAN;
      //initShapes();
      break;
     case 4:// T
      this.shape = new int[][]{{1, 0},{1, 1},{1 ,0 }};
      this.color = Color.RED;
      //initShapes();
      break;
     case 5: // I
      this.shape = new int[][]{{1,0},{1,0},{1,0}, {1,0}};
      this.color = Color.GREEN;
      //initShapes();
      break;
     case 6: // S
      this.shape = new int[][]{{0,1},{1,1},{1,0}};
      this.color = Color.LTGRAY;
      //initShapes();
      break;
     case 7: // Z
      this.shape = new int[][]{{1,0 },{1,1},{0,1 }};
      this.color = Color.BLUE;
      //initShapes();
      break;
    }
 }
 public int[][] getShape() {
  return shape;
 }

 public void setShape(int[][] shape) {
  this.shape = shape;
 }

 public int getColor() {
  return color;
 }

 public void setColor(int color) {
  this.color = color;
 }
 public int getHeight(){
  return shape.length;
 }

 public int getWidth(){
  return shape[0].length;
 }
 public int getX(){
  return x;
 }
 public int getY(){
  return y;
 }
 public void moveDown()
 {
  y++;
 }
 public void moveLeft()
 {
  x--;
 }
 public void moveRight()
 {
  x++;
 }
 public void initShapes()
 {
  shapes  = new int [4][][];
  for (int i = 0; i < 4; i++)
  {
     int row = shape[0].length;
     int col = shape.length;
     shapes[i] = new int[row][col];
   for (int y= 0; y < row; y++)
   {
    for (int x = 0; x <col ; x++)
    {

      shapes[i][x][y]=shape[col-x-1][y];
    }
   }

   shape = shapes[i];
  }

 }
 public void ableRotate()
 {
  currentRotation = 0;
  shape = shapes[currentRotation];
 }
public void  rotate()
{
 currentRotation++;
 if(currentRotation>3)currentRotation = 0;
 shape = shapes[currentRotation];
}
public int getBottonEdge()
{
 return y + getHeight();
}
public int getLeftEdge()
{
 return x;
}
public int getRightEdge()
{
 return x+8;
}
}




