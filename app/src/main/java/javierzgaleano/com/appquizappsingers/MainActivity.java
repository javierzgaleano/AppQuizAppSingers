package javierzgaleano.com.appquizappsingers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        // Empieza la app() metodo, creara el Activity que iniciara la app
        Intent intent = new Intent(MainActivity.this, StartGame.class);
        startActivity(intent);
        // Finalizara nuestro MainActivity
        finish();
    }
}