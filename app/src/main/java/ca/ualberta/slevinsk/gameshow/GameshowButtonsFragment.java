package ca.ualberta.slevinsk.gameshow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameshowButtonsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameshowButtonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameshowButtonsFragment extends Fragment {

    private static final String ARG_PARAM1 = "numberOfPlayers";

    private Integer numberOfPlayers;

    private OnFragmentInteractionListener mListener;


    public static GameshowButtonsFragment newInstance(Integer numPlayers) {
        GameshowButtonsFragment fragment = new GameshowButtonsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, numPlayers);
        fragment.setArguments(args);
        return fragment;
    }

    public GameshowButtonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            numberOfPlayers = getArguments().getInt(ARG_PARAM1);
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        int ids[] = {R.id.buttonP1, R.id.buttonP2, R.id.buttonP3, R.id.buttonP4};


        for(int i=0; i<numberOfPlayers; i++){

            BuzzerButtonListener listener = new BuzzerButtonListener(numberOfPlayers, i+1);

//            BuzzerCounterController.getBuzzerCounterList().addListener(listener);
            getView().findViewById(ids[i]).setOnClickListener(listener);
        }

    }



     public class BuzzerButtonListener implements View.OnClickListener, Listener{

        private int playerId;
        private int numberOfPlayers;

        BuzzerButtonListener(int numberOfPlayers, int playerId){
            this.playerId = playerId;
            this.numberOfPlayers = numberOfPlayers;
//            getBuzzerCounterList().addListener(this);
        }

        @Override
        public void onClick(View v) {
            BuzzerCounterController.incrementCounter(numberOfPlayers, playerId);
            createDialog();
        }

        public void createDialog(){
            AlertDialog.Builder b = new AlertDialog.Builder(getContext());

            b.setTitle("Gameshow Buzzer");
            b.setMessage(String.format("Player %d buzzed", playerId));
            b.setPositiveButton("OK", null);
            AlertDialog bs =b.create();
            bs.show();
        }

        @Override
        public void update() {

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BuzzerCounterController.saveBuzzerCounterList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game_show_two_players, container, false);

        int layout;

        switch (numberOfPlayers) {
            case 2:
                layout = R.layout.gameshow_2_players;
                break;
            case 3:
                layout = R.layout.gameshow_3_players;
                break;
            case 4:
                layout = R.layout.gameshow_4_players;
                break;
            default:
                throw new RuntimeException("Invalid number of players!");
        }
        return inflater.inflate(layout, container, false);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
