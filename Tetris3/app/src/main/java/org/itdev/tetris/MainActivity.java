package org.itdev.tetris;
import org.itdev.domain.*;
import org.itdev.logic.GameBoard;
import org.itdev.logic.Piece;
import org.w3c.dom.Text;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView block[][] = new TextView[10][20];
    private TextView nextpieceView[][] = new TextView[4][4];
    int width =0;
    int height=0;
    private GridLayout gridLayout1;
    private GameBoard gameBoard = new GameBoard();
    private Random random = new Random();
    private float x1, x2;
    private float y1, y2;
    private ArrayList<Piece> pieceList;
    boolean status = true ;
   private Piece piece;
    private GridLayout gridLayout;
    private Timer timer = new Timer();
    private final int TIMER = 1000;
    private int cont = 0;
    private boolean Status = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pieceList =  gameBoard.getBlocksList();
        setContentView(R.layout.activity_main);
        View pantalla = getWindow().getDecorView();
        pantalla.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    touch(event.getX(), event.getY());
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    soltarPantalla(event.getX(), event.getY());
                    movimiento();
                }
                return false;
            }
            });
      /*  Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);*/
        this.gridLayout = findViewById(R.id.Grid);
        this.gridLayout1 = findViewById(R.id.grid1);
        width = 900;
        height = 1900;

        FrwdPiece drawNext = new FrwdPiece();

        Draw();
     drawNextPiece();
//        gameBoard.getCurrentPiece().ableRotate();
        drawPiece(gameBoard.getBlockBoolean(), block,gameBoard.getCurrentPiece());

        gameloop();
        }

    private void movimiento() {
        float dx = Math.abs(this.x2 - this.x1);
        float dy = Math.abs(this.y2 - this.y1);
        String dir = "";
        if(dx > dy){
            if(this.x2 > this.x1){
                moveRight(block, gameBoard.getCurrentPiece());
            }else{
                moveLeft(block, gameBoard.getCurrentPiece());
            }
        }else{
            if(this.y2 > this.y1){
                dropDown(block, gameBoard.getCurrentPiece());
                dir = "Abajo";
            }else{
               rotate(block, gameBoard.getCurrentPiece());
                dir = "Arriba";
            }
        }
        Toast.makeText(getApplicationContext(),dir, Toast.LENGTH_SHORT).show();
    }


    private void soltarPantalla(float x, float y) {
        this.x2 = x;
        this.y2 = y;
    }

    private void touch(float x, float y) {

        this.x1 = x;
        this.y1 = y;
    }


    public void gameloop()
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                if(Status)
                {
                    moveDown(block, gameBoard.getCurrentPiece());
                    if(gameBoard.canMoveDown( gameBoard.getCurrentPiece(), block )== false)
                    {
                         setValues(gameBoard.getBlockBoolean(), gameBoard.getCurrentPiece());
                        spawn();
                    }

                    gameloop();
                }
            }
        });
    }
    },1000,250000);
    }
// Hilo la ficha tiene que bajar sola sleep  1 segundo
        // score si toca el pizo pues que sume 100 puntos
        // Niveles
        // Eliminar fila
        // detectar colisiones
        //  f
    

public void spawn()
{

   setValues(gameBoard.getBlockBoolean(), gameBoard.getCurrentPiece());
    deleteForwardPiece(nextpieceView, gameBoard.getForwardPiece());
    pieceList.remove(gameBoard.getCurrentPiece());
    pieceList.add(new Piece(random.nextInt(7)+1));

    drawfrwdPiece(nextpieceView, gameBoard.getForwardPiece());
}

public void moveLeft(TextView block[][], Piece piece )
{
    if(gameBoard.checkLeft()== false)return;
    deleteCurrentPiece(gameBoard.getBlockBoolean(), block,piece);
    piece.moveLeft();
    drawPiece(gameBoard.getBlockBoolean(), block,piece);
}
public void moveRight(TextView block[][], Piece piece )
{
    if(gameBoard.checkRight()==false)return;

        deleteCurrentPiece(gameBoard.getBlockBoolean(), block,piece);
        piece.moveRight();
        drawPiece(gameBoard.getBlockBoolean(), block,piece);




}

public void rotate(TextView block[][], Piece piece )
{
    deleteCurrentPiece(gameBoard.getBlockBoolean(), block, piece);
    piece.rotate();
    drawPiece(gameBoard.getBlockBoolean(), block,piece);
}
public void dropDown(TextView block[][], Piece piece)
{
    while(!gameBoard.canMoveDown(gameBoard.getCurrentPiece(), block))
    {
        moveDown(block, gameBoard.getCurrentPiece());
    }
}
public void moveDown(TextView block[][], Piece piece)
{

    deleteCurrentPiece(gameBoard.getBlockBoolean(), block, piece);
    piece.moveDown();
    drawPiece(gameBoard.getBlockBoolean(), block,piece);

}
private void deleteCurrentPiece(boolean block1[][], TextView block[][], Piece piece )
{

    MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
    shapeDrawable.setFillColor(ColorStateList.valueOf(Color.BLACK));
    shapeDrawable.setStroke(1.0f, ContextCompat.getColor(this,  R.color.white));
      for (int row = 0; row < piece.getWidth(); row++) {
          for (int col = 0; col < piece.getHeight(); col++) {

              if (piece.getShape()[col][row] == 1) {
                  int x = piece.getX() + row;
                  int y = piece.getY()+ col;
                  ViewCompat.setBackground(block[x][y], shapeDrawable);
                  block[x][y].setHintTextColor(Color.TRANSPARENT);
                 // block[x][y].setHint("0");
              }


          }
      }

    }
    private void drawPiece(boolean block1[][], TextView block[][], Piece piece ) {
        // clase el pieceL, pieceT, pieceI, implements de piece
        // rotate()

        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ColorStateList.valueOf(piece.getColor()));
        shapeDrawable.setStroke(1.0f, ContextCompat.getColor(this, R.color.black));

        for (int row = 0; row < piece.getWidth(); row++) {
            for (int col = 0; col < piece.getHeight(); col++) {

              if (piece.getShape()[col][row] == 1 )
                {
                   int x = piece.getX()+row;
                    int y = piece.getY()+col;
                    ViewCompat.setBackground(block[x][y],shapeDrawable);
                    block[x][y].setHintTextColor(Color.TRANSPARENT);
                    //block[x][y].setHint("1");
                }

            }
        }

    }
    private void setValues(boolean block[][], Piece piece ) {
        // clase el pieceL, pieceT, pieceI, implements de piece
        // rotate()

        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ColorStateList.valueOf(Color.WHITE));
        shapeDrawable.setStroke(1.0f, ContextCompat.getColor(this, R.color.black));

        for (int row = 0; row < piece.getWidth(); row++) {
            for (int col = 0; col < piece.getHeight(); col++) {

                if (piece.getShape()[col][row] == 1 )
                {
                    int x = piece.getX()+row;
                    int y = piece.getY()+col;
                    block[x][y] = true;
                }

            }
        }

    }


    private void Draw()
    {
        Point p = new Point( );
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(this, R.color.black));
       shapeDrawable.setStroke(1.0f, ContextCompat.getColor(this, R.color.white));
        for (int y = 0; y <20 ; y++)
        {
            for (int x = 0; x <10 ; x++) {
                this.block[x][y] = new TextView(this);
                ViewCompat.setBackground(this.block[x][y],shapeDrawable);
                this.block[x][y].setLayoutParams(new LinearLayout.LayoutParams(width/10,height/20));
                this.gridLayout.addView(block[x][y]);
            }
        }
    }

    private void drawNextPiece()
    {
        Point p = new Point( );
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(this, R.color.black));
        shapeDrawable.setStroke(1.0f, ContextCompat.getColor(this, R.color.white));
        for (int y = 0; y <4 ; y++)
        {
            for (int x = 0; x <4 ; x++) {
                this.nextpieceView[x][y] = new TextView(this);
                ViewCompat.setBackground(this.nextpieceView[x][y],shapeDrawable);
                this.nextpieceView[x][y].setLayoutParams(new LinearLayout.LayoutParams(300/4,300/4));
                this.gridLayout1.addView(nextpieceView[x][y]);
            }
        }
        drawfrwdPiece(nextpieceView, gameBoard.getForwardPiece());
    }
    private void drawfrwdPiece(TextView nextpieceView[][], Piece piece)
    {

        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ColorStateList.valueOf(piece.getColor()));
        shapeDrawable.setStroke(1.0f, ContextCompat.getColor(this, R.color.black));

        for (int row = 0; row < piece.getWidth(); row++) {
            for (int col = 0; col < piece.getHeight(); col++) {

                if (piece.getShape()[col][row] == 1 )
                {
                    int x =1+ row;
                    int y = col;
                    ViewCompat.setBackground(nextpieceView[x][y],shapeDrawable);

                }

            }
        }

    }
    private void deleteForwardPiece( TextView nextpieceView[][], Piece piece )
    {

        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ColorStateList.valueOf(Color.BLACK));
        shapeDrawable.setStroke(1.0f, ContextCompat.getColor(this,  R.color.white));
        for (int row = 0; row < piece.getWidth(); row++) {
            for (int col = 0; col < piece.getHeight(); col++) {

                if (piece.getShape()[col][row] == 1) {
                    int x = 1 + row;
                    int y = col;
                    ViewCompat.setBackground(nextpieceView[x][y], shapeDrawable);

                }


            }
        }

    }

}