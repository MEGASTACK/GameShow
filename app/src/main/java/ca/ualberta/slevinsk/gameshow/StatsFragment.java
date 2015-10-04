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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final List<String> test = ReactionTimersController.generateStatsData(10);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item, test);
        setListAdapter(adapter);

        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);


        ArrayList<SpecialPair<String, Integer>> test2 = new ArrayList<>();
        test2.add(new SpecialPair<String, Integer>("Last 10", 10));
        test2.add(new SpecialPair<String, Integer>("Last 100", 100));
        test2.add(new SpecialPair<String, Integer>("All", -1));
        ArrayAdapter<SpecialPair<String, Integer>> adapter2 = new ArrayAdapter<>(getContext(), R.layout.list_item, test2);


        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Pair<String, Integer> selected = (Pair<String, Integer>) spinner.getItemAtPosition(position);

                Toast.makeText(getContext(), String.format("You selected %s", selected), Toast.LENGTH_SHORT).show();
                test.clear();
                test.addAll(ReactionTimersController.generateStatsData(selected.second));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(listener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stats, container, false);
        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
