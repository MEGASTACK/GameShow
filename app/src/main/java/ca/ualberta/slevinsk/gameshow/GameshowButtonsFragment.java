package ca.ualberta.slevinsk.gameshow;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;


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
    private BuzzerCounterModel buzzerCounterModel;
    private BuzzerCounter buzzerCounter;


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

        try {
            getView().findViewById(R.id.buttonP1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buzzerCounter.increment(1);
                    Toast.makeText(getContext(), String.format("%d", buzzerCounter.getCount(1)), Toast.LENGTH_SHORT).show();
                }
            });
            getView().findViewById(R.id.buttonP2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buzzerCounter.increment(2);
                    Toast.makeText(getContext(), String.format("%d", buzzerCounter.getCount(2)), Toast.LENGTH_SHORT).show();
                }
            });
            getView().findViewById(R.id.buttonP3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buzzerCounter.increment(3);
                    Toast.makeText(getContext(), String.format("%d", buzzerCounter.getCount(3)), Toast.LENGTH_SHORT).show();
                }
            });
            getView().findViewById(R.id.buttonP4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buzzerCounter.increment(4);
                    Toast.makeText(getContext(), String.format("%d", buzzerCounter.getCount(4)), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        buzzerCounterModel.saveToFile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game_show_two_players, container, false);

        int layout;
        buzzerCounterModel = new BuzzerCounterModel(getContext(), "buzz.file");

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
        buzzerCounter = buzzerCounterModel.getBuzzerCounter(numberOfPlayers);
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
