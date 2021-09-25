package org.itdev.logic;
import org.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Diccionary {

    private Question question;

    private ArrayList<Question> questions;
    private File file;

    public Diccionary()
    {
     questions = new ArrayList<>();

    }
    public void readFile(File file)
    {
        file = new File("arhcivo.txt");
        FileInputStream fileInputStream;

    }
}
