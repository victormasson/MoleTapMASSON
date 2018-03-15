package org.diiage.masson.moletap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String extra_message = "session";
    public static final int extra_int = 0;
    private Session sessionGame;
    private static Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionGame = new Session();
        final Button btnPageNewGame = findViewById(R.id.btnNewGame);
        final Button btnPageScore = findViewById(R.id.btnScores);
        final TextView txtName = findViewById(R.id.txtName);

        btnPageNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
//                intent.putExtra(extra_message, Session);
                startActivityForResult(intent, MainActivity.extra_int);
            }
        });

        btnPageScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
//                sessionGame.addScore();
                intent.putExtra(extra_message, sessionGame);
                startActivityForResult(intent, MainActivity.extra_int);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MainActivity.extra_int) {

            Bundle extras = data.getExtras();
            int s = (int) extras.get("score");
            int r = (int) extras.get("rate");
            Score sc = new Score();
            sc.setNombrePoint(s);
            sc.setNombreRate(r);
            sessionGame.addScore(sc);

            Toast toast = Toast.makeText(getApplicationContext(), "Score: " + String.valueOf(sc.getNombrePoint()) + " | Rate: " + String.valueOf(sc.getNombreRate()) , Toast.LENGTH_SHORT);
            toast.show();

//            Score sc = new Score();
//            String strg = String.valueOf("score");
//            int a = (int)data.getSerializableExtra(strg);
//            sc.setNombrePoint(a);
//            sessionGame.addScore(sc);
//            Toast toast = Toast.makeText(getApplicationContext(), "Score: " + String.valueOf(sc.getNombrePoint()), Toast.LENGTH_SHORT);
//            toast.show();
        }
    }
}
