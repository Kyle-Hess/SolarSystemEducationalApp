package com.example.kyle.solarsystemeducationalapp;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class GameActivity extends AppCompatActivity {
    private int score;

    TextView currentScore;
    ImageView mercuryTarget,venusTarget,earthTarget,marsTarget,jupiterTarget, saturnTarget, uranusTarget, neptuneTarget,
            mercury, venus, earth, mars, jupiter, saturn, uranus, neptune;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        currentScore = (TextView) findViewById(R.id.currentScore);

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

            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    if (view.getId() == R.id.mercury && v.getId() == R.id.mercuryBlank){
                        //((ImageView)v).setImageDrawable(getResources().getDrawable(R.drawable.mercury));
                        //((ImageView)view).setImageDrawable(null);
                        mercuryTarget.setImageResource(R.drawable.mercury);
                        mercury.setVisibility(View.GONE);
                        score +=10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else if (view.getId() == R.id.venus && v.getId() == R.id.venusBlank){
                        //((ImageView)v).setImageDrawable(getResources().getDrawable(R.drawable.venus));
                        //((ImageView)view).setImageDrawable(null);
                        venusTarget.setImageResource(R.drawable.venus);
                        venus.setVisibility(View.GONE);
                        score +=10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else if (view.getId() == R.id.earth && v.getId() == R.id.earthBlank){
                        //((ImageView)v).setImageDrawable(getResources().getDrawable(R.drawable.earth));
                        //((ImageView)view).setImageDrawable(null);
                        earthTarget.setImageResource(R.drawable.earth);
                        earth.setVisibility(View.GONE);
                        score +=10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else if (view.getId() == R.id.mars && v.getId() == R.id.marsBlank){
                        //((ImageView)v).setImageDrawable(getResources().getDrawable(R.drawable.mars));
                        //((ImageView)view).setImageDrawable(null);
                        mars.setImageResource(R.drawable.mars);
                        mars.setVisibility(View.GONE);
                        score +=10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else if (view.getId() == R.id.jupiter && v.getId() == R.id.jupiterBlank){
                        //((ImageView)v).setImageDrawable(getResources().getDrawable(R.drawable.jupiter));
                        //((ImageView)view).setImageDrawable(null);
                        jupiterTarget.setImageResource(R.drawable.jupiter);
                        jupiter.setVisibility(View.GONE);
                        score +=10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else if (view.getId() == R.id.saturn && v.getId() == R.id.saturnBlank){
                        //((ImageView)v).setImageDrawable(getResources().getDrawable(R.drawable.saturn));
                        //((ImageView)view).setImageDrawable(null);
                        saturnTarget.setImageResource(R.drawable.saturn);
                        saturn.setVisibility(View.GONE);
                        score +=10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else if (view.getId() == R.id.uranus && v.getId() == R.id.uranusBlank){
                        //((ImageView)v).setImageDrawable(getResources().getDrawable(R.drawable.uranus));
                        //((ImageView)view).setImageDrawable(null);
                        uranusTarget.setImageResource(R.drawable.uranus);
                        uranus.setVisibility(View.GONE);
                        score +=10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else if (view.getId() == R.id.neptune && v.getId() == R.id.neptuneBlank) {
                        //((ImageView) v).setImageDrawable(getResources().getDrawable(R.drawable.neptune));
                        //((ImageView) view).setImageDrawable(null);
                        neptuneTarget.setImageResource(R.drawable.neptune);
                        neptune.setVisibility(View.GONE);

                        score += 10;
                        Toast.makeText(GameActivity.this, "dropped", Toast.LENGTH_SHORT).show();
                        currentScore.setText(String.valueOf(score));

                    }else{
                        score -= 5;
                        currentScore.setText(String.valueOf(score));

                    }
                    break;
            }
            return true;
        }
    };

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
                Toast.makeText(this, "Home selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_game:
                Intent gIntent = new Intent(this, GameActivity.class);
                startActivity(gIntent);
                Toast.makeText(this, "Game selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_high_scores:
                Intent hsIntent = new Intent(this, HighScoresActivity.class);
                startActivity(hsIntent);
                Toast.makeText(this, "High Scores selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Intent sIntent = new Intent(this, SettingsActivity.class);
                startActivity(sIntent);
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
