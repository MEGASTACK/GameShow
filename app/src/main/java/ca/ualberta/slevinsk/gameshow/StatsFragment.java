package ca.ualberta.slevinsk.gameshow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends ListFragment {

    private static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

//    private OnFragmentInteractionListener mListener;


    public static StatsFragment newInstance( ) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public StatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }



    }


//    public List<String> generateStatsData(ReactionTimersManager r, Integer n){
//        List<String> test = new ArrayList<>();
//        test.add(String.format("Max time: %d", r.max(n)));
//        test.add(String.format("Min time: %d", r.min(n)));
//        test.add(String.format("Average time: %d", r.average(n)));
//        test.add(String.format("Median time: %d", r.median(n)));
//        return test;
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ReactionTimersController.getReactionTimerList().clearListeners();


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final List<String> test = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item, test);
        setListAdapter(adapter);


        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);


        ArrayList<SpecialPair<String, Integer>> test2 = new ArrayList<>();
        test2.add(new SpecialPair<String, Integer>("Last 10", 10));
        test2.add(new SpecialPair<String, Integer>("Last 100", 100));
        test2.add(new SpecialPair<String, Integer>("All", -1));
        ArrayAdapter<SpecialPair<String, Integer>> adapter2 = new ArrayAdapter<>(getContext(), R.layout.list_item, test2);


        Generator<List, Integer> dataGenerator = new Generator<List, Integer>() {
            @Override
            public List generate(Integer arg) {
                return ReactionTimersController.generateStatsData(arg);
            }
        };

        SpinListener listener = new SpinListener(spinner, test, adapter, dataGenerator);

        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(listener);
        ReactionTimersController.getReactionTimerList().addListener(listener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        return view;
    }

}
