package org.itdev.questiongame;
import org.*;
import org.itdev.logic.Diccionary;
import org.itdev.logic.Question;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Diccionary diccionary;
    private TextView textView;
    private ArrayList<Question> questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diccionary = new Diccionary();
       //questions = diccionary.readFile(this);
       //String n =  diccionary.readFromFile(this);
       // textView = findViewById(R.id.pregunta);
       // textView.setTextColor(Color.BLACK);
       // textView.setText(questions.get(0).getQuestion());
    }
}