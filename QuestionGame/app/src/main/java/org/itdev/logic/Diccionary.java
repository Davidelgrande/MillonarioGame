package org.itdev.logic;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Diccionary {

    private Question question;
    private Context context;
    private ArrayList<Question> questions;
    private File file;
    private String linea1[];
    private String data = "1, 1, A quien se le atribuye la frase: Yo soy yo  y mis circunstancias, Pipe Pelaez, Pablo Bozodelulo, Ricardo Arjona, Ortega y Gasset, Ortega y Gasset\n" +
            "1, 2, Quien interpreta la famosa cancion \"Procura\", Marie Cure, Hector Lavoe, Chichi Peralta, Afaz Natural, Chichi Peralta\n" +
            "1, 3, Cuanto es el seno 90 grados, 1, 0, -1, 0.5, 1\n" +
            "1, 4, Cuanto suman los ángulos de un triangulo, 180, 60, 360, 90, 180\n" +
            "1, 5, Cual es el ave insignia de los juegos del hambre, fenix, el penyair, sinsajo, mirlo, sinsajo\n" +
            "2, 6, Cual es el significado de \"Peronia seculá seculorum\", Cuchara cucharona,Por los siglos de los siglos, Hagale que yo se lo plagio, Si te vi no me acuerdo,Por los siglos de los siglos,\n" +
            "2, 7, Quien descubrio la selectividad de las especies, Charles Darwin, Harry Potter, Pato Donald, Dua Lipa, Charles Darwin \n" +
            "2, 8, Quien descubrio América, Colombo, Cristobal Colon, Camilla Cabello, Marilyn Monrrou, Cristobal Colon \n" +
            "2, 9, Promotor de la moral como ley universal, y conocido por su escrito de la Ilustracion, Emmanuel Kant, Shaggy, Sean Paul, Pikachu, Emmanuel Kant\n" +
            "2, 10, Con que método se puede consumir una api de preguntas, REST, POST, GET, DELETE, POST\n";
    public Diccionary() {
        questions = new ArrayList<>();
       Xd();
    }

    private void Xd(){
        String[] vec =data.split("\n");
        System.out.println(data.split("\n").length+"  <-----");

        for(int i=0;i< vec.length;i++){
            String lin[] = vec[i].split(",");
            System.out.println(lin[0]+"  "+lin[6]+"  <-------");
        }


    }

    /*
    public String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("preguntas.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    } */
    public String[] split ()
    {


        return linea1;
    }
    public ArrayList<Question> readFile(Context context) {

        /*
        try {
            InputStream inputStream = context.openFileInput("C:/Users/david.martinez/AndroidStudioProjects/QuestionGame/app/src/main/java/org/itdev/logic/preguntas.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(inputStreamReader); */
    linea1 = split();


        String linea[] = data.split("\n");
        for (int i = 0; i < linea.length; i++) {
            linea1= linea[i].split(",");
            int level = Integer.parseInt(linea1[0]);
            int  id= Integer.parseInt(linea1[1]);
            this.question = new Question();
            question.setId(id);
            question.setQuestion(linea1[2]);

            questions.add(question);
        }


 /*
        while ((linea = br.readLine())!= null) {



                }
                inputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

  */
        return questions;
    }
}
