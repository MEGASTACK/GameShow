package ca.ualberta.slevinsk.gameshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ReactionTimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showDialog("This is a dialog box");
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
        }
    };

}
