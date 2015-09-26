package ca.ualberta.slevinsk.gameshow;


import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.NumberPicker;

public class GameshowBuzzerActivity extends AppCompatActivity implements GameShowTwoPlayers.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow_buzzer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NumberPickerFragment n = new NumberPickerFragment();
        n.show(getSupportFragmentManager(), "numberPicker");


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("myFragmentTag");


        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            fragment = new GameShowTwoPlayers();
            ft.add(R.id.fgContainer,fragment,"myFragmentTag");
            ft.commit();
        }


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
