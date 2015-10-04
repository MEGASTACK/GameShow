package ca.ualberta.slevinsk.gameshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReactionTimerActivity extends AppCompatActivity {

    private ReactionTimer currentTimer;
    private Handler handler;
    private Button button;
    private ReactionTimerList timers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button = (Button) findViewById(R.id.reactionTimerButton);
        handler = new Handler();
        currentTimer = new ReactionTimer();
        timers = ReactionTimersController.getReactionTimerList();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ReactionTimersController.saveReactionTimerList();
    }

    private void startReactionTimer() {
        currentTimer = new ReactionTimer();
        button.setText("Wait for it...");
        currentTimer.randomizeTargetTime();
        currentTimer.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setText("Press me!");
            }
        }, currentTimer.getTargetTime());
    }


    private void showDialog(String message){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage(message);
        b.setCancelable(false);
        b.setPositiveButton("OK", onDismissDialog);
        b.show();
    }

    private DialogInterface.OnClickListener onDismissDialog = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast t = Toast.makeText(ReactionTimerActivity.this, "Timer should start!", Toast.LENGTH_SHORT);
            t.show();
            startReactionTimer();

        }
    };

    public void onReactionTimerButtonSelected(View view) {
//        Toast.makeText(this, "Button pressed!", Toast.LENGTH_SHORT);

        currentTimer.stop();
        Long timeDelta = currentTimer.targetDelta();

        if (timeDelta <= 0) {
            Snackbar.make(view, "You are a dirty cheater", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(view, String.format("Time delta: %d", timeDelta), Snackbar.LENGTH_SHORT).show();

            ReactionTimersController.add(currentTimer);
        }


        showDialog("This is the reaction currentTimer!\nWait for the button to say \"Press me!\", and then" +
                "press it!");
    }
}
