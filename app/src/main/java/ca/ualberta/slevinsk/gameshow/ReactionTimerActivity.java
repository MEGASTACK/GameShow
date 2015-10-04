package ca.ualberta.slevinsk.gameshow;
/**
 * Copyright 2015 John Slevinsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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

        ReactionTimersManager.initManager(getApplicationContext());

        showDialog("Welcome to reaction timer mode.\nPlease wait for the button to say \"Press me!\", and then " +
                "press it as quickly as you can. But don't press the button too early!");

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
        handler.postDelayed(reactRunnable, currentTimer.getTargetTime());
    }

    @NonNull
    private Runnable reactRunnable = new Runnable() {
        @Override
        public void run() {
            button.setText("Press me!");

        }
    };

    private void showDialog(String message){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage(message);
        b.setTitle("Welcome!");
        b.setCancelable(false);
        b.setPositiveButton("OK", onDismissDialog);
        b.show();
    }

    private DialogInterface.OnClickListener onDismissDialog = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            startReactionTimer();
        }
    };

    public void onReactionTimerButtonSelected(View view) {
        currentTimer.stop();
        Long timeDelta = currentTimer.targetDelta();

        String result;

        if (timeDelta <= 0) {
            result = "You pressed the button too soon!";
            handler.removeCallbacks(reactRunnable);
        } else {
            result = String.format("Reaction time: %dms", timeDelta);
            ReactionTimersController.add(currentTimer);
        }

        Snackbar.make(view, result, Snackbar.LENGTH_SHORT).show();
        button.setText(result);
        button.setOnClickListener(null);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Welcome to reaction timer mode.\nPlease wait for the button to say \"Press me!\", and then " +
                        "press it as quickly as you can. But don't press the button too early!");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onReactionTimerButtonSelected(v);
                    }
                });
            }
        }, 1000);

    }
}
