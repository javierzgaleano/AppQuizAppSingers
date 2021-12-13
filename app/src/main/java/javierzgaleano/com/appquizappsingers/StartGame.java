package javierzgaleano.com.appquizappsingers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGame extends AppCompatActivity {
    TextView tvTimer;
    TextView tvResult;
    ImageView ivShowImage;
    HashMap<String, Integer> map = new HashMap<>();
    ArrayList<String> artistas = new ArrayList<>();
    int index;
    Button btn1, btn2, btn3, btn4;
    TextView tvPoints;
    int points;
    CountDownTimer countDownTimer;
    long millisUntilFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);

        tvTimer = findViewById(R.id.tvTimer);
        // Get the handles for the Views
        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.tvPoints);
        // Initialize index with 0
        index = 0;
        // Aca vamos a agregar a los camtantes
        artistas.add("angus");
        artistas.add("axl");
        artistas.add("bruce");
        artistas.add("chester");
        artistas.add("dave");
        artistas.add("dee");
        artistas.add("fred");
        artistas.add("gene");
        artistas.add("james");
        artistas.add("jhon");
        artistas.add("kour");
        artistas.add("lemmy");
        artistas.add("marito");
        artistas.add("ozzy");
        artistas.add("rob");
        artistas.add("simone");

        //aca vamos a poner nuestro arreglo que contendra las imagenes
        map.put(artistas.get(0), R.drawable.angus);
        map.put(artistas.get(1), R.drawable.axl);
        map.put(artistas.get(2), R.drawable.bruce);
        map.put(artistas.get(3), R.drawable.chester);
        map.put(artistas.get(4), R.drawable.dave);
        map.put(artistas.get(5), R.drawable.dee);
        map.put(artistas.get(6), R.drawable.fred);
        map.put(artistas.get(7), R.drawable.gene);
        map.put(artistas.get(8), R.drawable.james);
        map.put(artistas.get(9), R.drawable.jhon);
        map.put(artistas.get(10), R.drawable.kour);
        map.put(artistas.get(11), R.drawable.lemmy);
        map.put(artistas.get(12), R.drawable.marito);
        map.put(artistas.get(13), R.drawable.ozzy);
        map.put(artistas.get(14), R.drawable.rob);
        map.put(artistas.get(15), R.drawable.simone);

        Collections.shuffle(artistas);
        // aca seteamos para que inicialice
        millisUntilFinished = 10000;
        // todos empiezan con 0 puntops
        points = 0;
        // aca llamamops a iniciar el juego
        startGame();

    }
//aqui empieza la nueva guia
    private void startGame() {
        millisUntilFinished = 10000;
        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
        tvPoints.setText(points + " / " + artistas.size());
        // index aca se genera este parametro.
        generateQuestions(index);

        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // se actualiza tvTimer cada segundo para mostrar el número de segundos restantes.
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                index++;
                if (index > artistas.size() - 1){
                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    Intent intent = new Intent(StartGame.this, GameOver.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    // Finalizael  StartGame Activity
                    finish();
                } else {

                    countDownTimer = null;
                    startGame();
                }
            }
        }.start(); // Llamar al método start () para iniciar el cronometro.
    }

    private void generateQuestions(int index) {
        ArrayList<String> artistListTemp = (ArrayList<String>) artistas.clone();
        // Obtener la respuesta correcta para la pregunta actual de artistList usando index.
        String correctAnswer = artistas.get(index);
        // Necesitas encontrar tres respuestas incorrectas no repetidas al azar.
        // Entonces, elimine la respuesta correcta de artistListTemp.
        // Mezclarlo y obtener los primeros tres elementos de él.
        artistListTemp.remove(correctAnswer);
        Collections.shuffle(artistListTemp);
       // Cree una ArrayList temporal para almacenar tres respuestas aleatorias no repetidas
        // de tartistyas y una respuesta correcta.
        ArrayList<String> newList = new ArrayList<>();
        // Obtenga los primeros tres elementos de artistas y agréguelos a newList.
        newList.add(artistListTemp.get(0));
        newList.add(artistListTemp.get(1));
        newList.add(artistListTemp.get(2));
        // También agrega la respuesta correcta en newList
        newList.add(correctAnswer);
        // Mezcla newList para que la respuesta correcta se pueda colocar en uno de los cuatro botones aleatorios
        Collections.shuffle(newList);
        // Una vez que rota newList, configura el texto de los cuatro botones con los elementos
        // de newList.
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        // Además, configura ImageView con la imagen actual
        ivShowImage.setImageResource(map.get(artistas.get(index)));
    }

    public void nextQuestion(View view) {
        // Se llama a este método porque el usuario ha pulsado el botón Siguiente,
        // entonces, establece el color de fondo de todos los botones en el color que usamos en start_game.xml.
        btn1.setBackgroundColor(Color.parseColor("#2196f3"));
        btn2.setBackgroundColor(Color.parseColor("#2196f3"));
        btn3.setBackgroundColor(Color.parseColor("#2196f3"));
        btn4.setBackgroundColor(Color.parseColor("#2196f3"));
        // Habilita los botones
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        // Cancelas el contador
        countDownTimer.cancel();
        index++;
        // Verifica que todas las preguntas fueron contestadas
        if (index > artistas.size() - 1){
            // Si es verdadero, oculte ImageView y los botones.
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            // Ir a la pantalla de GameOver
            Intent intent = new Intent(StartGame.this, GameOver.class);
            intent.putExtra("points", points);
            startActivity(intent);
            // Finaliza StartGame Activity
            finish();
        } else {
            // Hasta que quede al menos una pregunta, inicializa el countDownTimer con null y
            // invocas a startGame ()
            countDownTimer = null;
            startGame();
        }
    }

    public void answerSelected(View view) {
        // aca puedes elegir el color de fondo del botón donde hace click
        view.setBackgroundColor(Color.parseColor("#17243e"));
        // Deshabilitar los cuatro botones
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        // El usuario ha seleccionado una respuesta, por lo tanto, se cancela el cronometro.
        countDownTimer.cancel();
        // Obtienes el texto del botón para hacer clic en la respuesta del usuario
        String answer = ((Button) view).getText().toString().trim();
        // obtendras la respuesta correcta para la pregunta actual de artistas usando index como posición.
        String correctAnswer = artistas.get(index);
        // Compare answer y correctAnswer, es decir, la respuesta seleccionada por el usuario
        // y la respuesta correcta para esta pregunta.
        if(answer.equals(correctAnswer)){
            // Si es verdadero, el usuario ha seleccionado la respuesta correcta. Entonces, incremente los puntos.
            points++;
            // Aquí estamos incrementando puntos en 1 aquí, pero puedes incrementar en cualquier número
           // Actualiza TextViews para puntos y resultado
            tvPoints.setText(points + " / " + artistas.size());
            tvResult.setText("Correcto");
        } else {
            // En else, es decir, cuando la respuesta del usuario es incorrecta, muestra "Wrong" en tvResult.
            tvResult.setText("Nambe Chele");
        }
    }
}
