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

public class GameshowBuzzerActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, GameShowTwoPlayers.OnFragmentInteractionListener {

    private Integer numberOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow_buzzer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NumberPickerFragment n = new NumberPickerFragment();
        n.show(getSupportFragmentManager(), "numberPicker");

        setNumberOfPlayers(2);

    }





    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag("myFragmentTag");


        if (true) {
            FragmentTransaction ft = fm.beginTransaction();
            fragment = new GameShowTwoPlayers();
            Bundle b = new Bundle();
            b.putInt("numberOfPlayers", numberOfPlayers);

            fragment.setArguments(b);

            ft.replace(R.id.fgContainer, fragment, "myFragmentTag");
            ft.commit();
        }

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        setNumberOfPlayers(newVal);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
//http://stackoverflow.com/questions/14439941/passing-data-between-fragments-to-activity
