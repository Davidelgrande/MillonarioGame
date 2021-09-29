package org.itdev.domain;

import android.content.Context;
import android.graphics.Point;
import androidx.gridlayout.widget.GridLayout;;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.shape.MaterialShapeDrawable;

import org.itdev.logic.GameBoard;
import org.itdev.tetris.R;

public class FrwdPiece {
    GameBoard gameboard;
    Context context;
    public  void Draw(TextView block[][], Context context, GridLayout gridLayout)
    {
        android.graphics.Point p = new Point( );
        MaterialShapeDrawable shapeDrawable = new MaterialShapeDrawable();
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(context, R.color.black));
        shapeDrawable.setStroke(1.0f, ContextCompat.getColor(context, R.color.white));
        for (int y = 0; y <20 ; y++)
        {
            for (int x = 0; x <10 ; x++) {
                block[x][y] = new TextView(context);
                ViewCompat.setBackground(block[x][y],shapeDrawable);
                block[x][y].setLayoutParams(new LinearLayout.LayoutParams(200/4,200/4));
                gridLayout.addView(block[x][y]);
            }
        }
    }

}
