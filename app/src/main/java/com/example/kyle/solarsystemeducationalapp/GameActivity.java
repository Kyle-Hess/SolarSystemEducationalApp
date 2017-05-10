package com.example.kyle.solarsystemeducationalapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements SensorEventListener {
    private int score;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    public String textX, textY, textZ;

    float x, y;

    RelativeLayout relativeGame;
    TextView currentScore, timer, tfQuizText;
    ImageView mercuryTarget, venusTarget, earthTarget, marsTarget, jupiterTarget, saturnTarget, uranusTarget, neptuneTarget,
            mercury, venus, earth, mars, jupiter, saturn, uranus, neptune;

    boolean pressed = false;
    private boolean tfAnswer;
    private boolean userChoice;
    private SoundPool sp;

    SharedPreferences prefs;
    private int setTime = 30000;
    private int idxDifficulty;
    private CountDownTimer countDownTimer;

//// TODO: 10/05/2017 implement high scores database.
//// TODO: 10/05/2017 implement sound
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        relativeGame = (RelativeLayout) findViewById(R.id.relativeGame);

        currentScore = (TextView) findViewById(R.id.currentScore);
        timer = (TextView) findViewById(R.id.timer);
        tfQuizText = (TextView) findViewById(R.id.tfQuizText);

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        idxDifficulty = prefs.getInt("prefRadio", 0);

        mercuryTarget = (ImageView) findViewById(R.id.mercuryBlank);
        venusTarget = (ImageView) findViewById(R.id.venusBlank);
        earthTarget = (ImageView) findViewById(R.id.earthBlank);
        marsTarget = (ImageView) findViewById(R.id.marsBlank);
        jupiterTarget = (ImageView) findViewById(R.id.jupiterBlank);
        saturnTarget = (ImageView) findViewById(R.id.saturnBlank);
        uranusTarget = (ImageView) findViewById(R.id.uranusBlank);
        neptuneTarget = (ImageView) findViewById(R.id.neptuneBlank);

        mercury = (ImageView) findViewById(R.id.mercury);
        venus = (ImageView) findViewById(R.id.venus);
        earth = (ImageView) findViewById(R.id.earth);
        mars = (ImageView) findViewById(R.id.mars);
        jupiter = (ImageView) findViewById(R.id.jupiter);
        saturn = (ImageView) findViewById(R.id.saturn);
        uranus = (ImageView) findViewById(R.id.uranus);
        neptune = (ImageView) findViewById(R.id.neptune);

        SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        //listeners
        mercuryTarget.setOnDragListener(dragListener);
        venusTarget.setOnDragListener(dragListener);
        earthTarget.setOnDragListener(dragListener);
        marsTarget.setOnDragListener(dragListener);
        jupiterTarget.setOnDragListener(dragListener);
        saturnTarget.setOnDragListener(dragListener);
        uranusTarget.setOnDragListener(dragListener);
        neptuneTarget.setOnDragListener(dragListener);

        //mercury.setOnLongClickListener(longClickListener);
        mercury.setOnTouchListener(new ChoiceTouchListener());
        venus.setOnTouchListener(new ChoiceTouchListener());
        earth.setOnTouchListener(new ChoiceTouchListener());
        mars.setOnTouchListener(new ChoiceTouchListener());
        jupiter.setOnTouchListener(new ChoiceTouchListener());
        saturn.setOnTouchListener(new ChoiceTouchListener());
        uranus.setOnTouchListener(new ChoiceTouchListener());
        neptune.setOnTouchListener(new ChoiceTouchListener());

        getRandomQuestion();

        confirmDifficulty();
        startTimer();
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    private void confirmDifficulty() {
        switch (idxDifficulty) {
            case 0:
                setTime = 60000;
                break;
            case 1:
                setTime = 30000;
                break;
            case 2:
                setTime = 15000;
                break;
        }
    }

    //updates question
    private void getRandomQuestion() {

        Random random = new Random();
        int randIndex = random.nextInt(5);
        tfQuizText.setText(tfQuiz.questions[randIndex]);
        tfAnswer = tfQuiz.answers[randIndex];
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;


        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            //float z = sensorEvent.values[2];

            if (y > 3 && y < 3.4) {
                checkAnswerFalse();
                //textViewY.setTextColor(Color.GREEN);

            } else if (y < -3 && y > -3.4) {
                checkAnswerTrue();
                //textViewY.setTextColor(Color.RED);

            } else {
                tfQuizText.setTextColor(Color.BLUE);
            }
        }
    }

    private void checkAnswerTrue() {
        if (tfAnswer) {
            score += 5;
            updateScore(score);
            getRandomQuestion();

        } else {
            score -= 5;
            updateScore(score);
            getRandomQuestion();
        }
    }

    private void checkAnswerFalse() {
        if (!tfAnswer) {
            score += 5;
            updateScore(score);
            getRandomQuestion();

        } else {
            score -= 5;
            updateScore(score);
            getRandomQuestion();
        }
    }

    //// TODO: 5/05/2017 add mini T/F quiz game for bonus points. Use accelerometer left true, right false.
    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if ((event.getAction() == MotionEvent.ACTION_DOWN) && (((ImageView) v).getDrawable() != null)) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();

            //testTime = true;

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (view.getId() == R.id.mercury && v.getId() == R.id.mercuryBlank) {
                        mercuryTarget.setImageResource(R.drawable.mercury);
                        mercury.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else if (view.getId() == R.id.venus && v.getId() == R.id.venusBlank) {
                        venusTarget.setImageResource(R.drawable.venus);
                        venus.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else if (view.getId() == R.id.earth && v.getId() == R.id.earthBlank) {
                        earthTarget.setImageResource(R.drawable.earth);
                        earth.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else if (view.getId() == R.id.mars && v.getId() == R.id.marsBlank) {
                        marsTarget.setImageResource(R.drawable.mars);
                        mars.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else if (view.getId() == R.id.jupiter && v.getId() == R.id.jupiterBlank) {
                        jupiterTarget.setImageResource(R.drawable.jupiter);
                        jupiter.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else if (view.getId() == R.id.saturn && v.getId() == R.id.saturnBlank) {
                        saturnTarget.setImageResource(R.drawable.saturn);
                        saturn.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else if (view.getId() == R.id.uranus && v.getId() == R.id.uranusBlank) {
                        uranusTarget.setImageResource(R.drawable.uranus);
                        uranus.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else if (view.getId() == R.id.neptune && v.getId() == R.id.neptuneBlank) {
                        neptuneTarget.setImageResource(R.drawable.neptune);
                        neptune.setVisibility(View.GONE);
                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        updateScore(score);

                    } else {
                        score -= 5;
                        updateScore(score);

                    }
                    break;
            }
            return true;
        }
    };

    private void updateScore(int score) {
        currentScore.setText(String.valueOf(score));
    }

    private void startTimer() {

        countDownTimer = new CountDownTimer(setTime, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {

                timer.setText("done!");
                Intent intent = new Intent(GameActivity.this, GameOver.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        }.start();
    }


//    View.OnLongClickListener longClickListener = new View.OnLongClickListener(){
//        @Override
//        public boolean onLongClick(View v) {
//
//            ClipData data = ClipData.newPlainText("","");
//            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder();
//            v.startDrag(data,myShadowBuilder,v,0);
//            //v.startDragAndDrop(data,myShadowBuilder,v,0);
//            return true;
//        }
//    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent mIntent = new Intent(this, MainActivity.class);
                startActivity(mIntent);
                countDownTimer.cancel();
                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_game:
                Intent gIntent = new Intent(this, GameActivity.class);
                startActivity(gIntent);
                countDownTimer.cancel();
                Toast.makeText(this, "Game selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_high_scores:
                Intent hsIntent = new Intent(this, HighScoresActivity.class);
                startActivity(hsIntent);
                countDownTimer.cancel();
                Toast.makeText(this, "High Scores selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Intent sIntent = new Intent(this, SettingsActivity.class);
                startActivity(sIntent);
                countDownTimer.cancel();
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
