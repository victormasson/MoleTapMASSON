package org.diiage.masson.moletap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static Timer timer;
    private static int monChrono = 0;
    private static TextView txtChrono;
    private static TextView txtScore;
    private static Score score;
    private static ArrayList<ImageButton> buttons;
    private static int isDisplayCurrent;
    private static Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score = new Score();
        monChrono = 0;
        isDisplayCurrent = 0;
        final Button btnPageScore = findViewById(R.id.btnScores);
        txtChrono = findViewById(R.id.txtChrono);
        txtScore = findViewById(R.id.txtScore);

        final ImageButton mole1 = findViewById(R.id.mole1);
        final ImageButton mole2 = findViewById(R.id.mole2);
        final ImageButton mole3 = findViewById(R.id.mole3);
        final ImageButton mole4 = findViewById(R.id.mole4);
        final ImageButton mole5 = findViewById(R.id.mole5);
        final ImageButton mole6 = findViewById(R.id.mole6);
        final ImageButton mole7 = findViewById(R.id.mole7);
        final ImageButton mole8 = findViewById(R.id.mole8);
        final ImageButton mole9 = findViewById(R.id.mole9);

        mole1.setOnClickListener(this);
        mole2.setOnClickListener(this);
        mole3.setOnClickListener(this);
        mole4.setOnClickListener(this);
        mole5.setOnClickListener(this);
        mole6.setOnClickListener(this);
        mole6.setOnClickListener(this);
        mole7.setOnClickListener(this);
        mole8.setOnClickListener(this);
        mole9.setOnClickListener(this);

        buttons = new ArrayList<ImageButton>(){{
            add(mole1);
            add(mole2);
            add(mole3);
            add(mole4);
            add(mole5);
            add(mole6);
            add(mole7);
            add(mole8);
            add(mole9);
        }};

//        Intent intent = getIntent();
//        session = (Session) getIntent().getExtras().getParcelable(MainActivity.extra_message);

        displayNoneAll();
        startTimer();
    }


    /**
     * Sert à savoir quel bouton a été appuié.
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mole1:
                checkAddScoreButtonPressed(R.id.mole1, isDisplayCurrent);
                break;
            case R.id.mole2:
                checkAddScoreButtonPressed(R.id.mole2, isDisplayCurrent);
                break;
            case R.id.mole3:
                checkAddScoreButtonPressed(R.id.mole3, isDisplayCurrent);
                break;
            case R.id.mole4:
                checkAddScoreButtonPressed(R.id.mole4, isDisplayCurrent);
                break;
            case R.id.mole5:
                checkAddScoreButtonPressed(R.id.mole5, isDisplayCurrent);
                break;
            case R.id.mole6:
                checkAddScoreButtonPressed(R.id.mole6, isDisplayCurrent);
                break;
            case R.id.mole7:
                checkAddScoreButtonPressed(R.id.mole7, isDisplayCurrent);
                break;
            case R.id.mole8:
                checkAddScoreButtonPressed(R.id.mole8, isDisplayCurrent);
                break;
            case R.id.mole9:
                checkAddScoreButtonPressed(R.id.mole9, isDisplayCurrent);
                break;
            default:
                break;
        }
        displayNoneAll();
    }

    /**
     * Sert à ajouter le score et le nombre de rate.
     * @param idToCheck
     * @param idCurrent
     * @return
     */
    public boolean checkAddScoreButtonPressed(int idToCheck, int idCurrent){
        ImageButton imToCheck = findViewById(idToCheck);
        if (imToCheck.getId() == idCurrent){
            score.addToScore();
            isDisplayCurrent = 0;
            return true;
        }
        else{
            score.addToRate();
            return false;
        }
    }

    /**
     * Sert à démarer le timer.
     */
    protected void startTimer() {
        boolean isTimerRunning = true;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                monChrono = Integer.parseInt(txtChrono.getText().toString());
                monChrono -= 1;
                mHandler.obtainMessage(1).sendToTarget();

                if (monChrono <= 0){
                    timer.cancel();
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    intent.putExtra("score", score.getNombrePoint());
                    intent.putExtra("rate", score.getNombreRate());
                    setResult(MainActivity.extra_int, intent);
                    finish();
                }
            }
        }, 0, 1000);
    }

    /**
     * Sert à faire afficher pour le timer les infos dans les textviews et à changer l'état des taupes.
     */
    @SuppressLint("HandlerLeak")
    public static Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            txtChrono.setText(String.valueOf(monChrono));
            txtScore.setText(String.valueOf(score.getNombrePoint()));
            displayNoneAll();
            changeTaupe();
        }
    };

    /**
     * Change l'état d'une taupe aléatoire.
     */
    public static void changeTaupe(){
        int i = nextInt(0, buttons.size()-1);
        ImageButton b = (ImageButton) buttons.get(i);
        b.setImageResource(R.drawable.lilmole);
        isDisplayCurrent = b.getId();
    }

    /**
     * Aléatoire.
     * @param origin
     * @param bound
     * @return
     */
    static int nextInt(int origin, int bound) {
        Random rand = new Random();
        return rand.nextInt((bound - origin) + 1) + origin;
    }

    /**
     * Sert à cacher tous les boutons.
     */
    static void displayNoneAll(){
        for (ImageButton ib : buttons){
            ib.setImageDrawable(null);
        }
    }
}