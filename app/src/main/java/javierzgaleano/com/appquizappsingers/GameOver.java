package javierzgaleano.com.appquizappsingers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity{
    // Declararemos las referencias de objetos TextView para mostrar puntos y mejor mejor puntuacion.
    TextView tvPoints, tvPersonalBest;
    // Declararemos una referencia de objeto SharedPreferences ya que vamos a almacenar
    // la puntuación más alta o la mejor marca personal usando la clase de SharedPreferences de Android.
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        // Recibira los datos de Intent que se envían desde StartGame Activity.
        int points = getIntent().getExtras().getInt("points");
        tvPoints = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);
        // Establecer tvPoints con el valor de puntos
        tvPoints.setText("" + points);
        // Crear una instancia de la referencia del objeto SharedPreferences
        sharedPreferences = getSharedPreferences("pref", 0);
        // Aquí pref es el nombre del archivo y 0 para el modo privado.
        // Para leer valores de SharedPreferences, usaremos el método getInt () en
        // objeto sharedPreferences.
        int pointsSP = sharedPreferences.getInt("pointsSP", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Verifica, si los puntos son mayores que los puntosSP
        if(points > pointsSP){
            // Si es verdadero, almacena puntos en pointsSP
            pointsSP = points;
            editor.putInt("pointsSP", pointsSP);
            // Esto sobrescribirá el valor existente de la clave "pointsSP".
            // Para guardar los cambios, llame a commit () en el editor.
            editor.commit();
        }
        // Establecer tvPersonalBest con el valor de pointsSP
        tvPersonalBest.setText("" + pointsSP);
    }

    public void restart(View view) {
        // Crea un objeto Intent para iniciar la actividad StartGame
        Intent intent = new Intent(GameOver.this, StartGame.class);
        startActivity(intent);
        // Finaliza GameOver Activity
        finish();
    }

    public void exit(View view) {
        // Llamar al método finish () para finalizar la actividad de GameOver
        finish();
    }
}