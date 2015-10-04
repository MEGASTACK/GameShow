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
 * {@link BuzzerStatsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuzzerStatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuzzerStatsFragment extends ListFragment {

    private static final String ARG_PAGE = "ARG_PAGE";


//    private OnFragmentInteractionListener mListener;


    public static BuzzerStatsFragment newInstance( ) {
        BuzzerStatsFragment fragment = new BuzzerStatsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public BuzzerStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public List<String> generateStatsData(BuzzerCounter b, Integer n){
        List<String> test = new ArrayList<>();

        for (int i=1; i<=n; i++){

            test.add(String.format("Player %d Buzz Count: %d", i, b.getCount(i)));
        }
        return test;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final List<String> test = generateStatsData(BuzzerCounterController.getBuzzerCounter(2), 2);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item, test);
        setListAdapter(adapter);

        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);


        ArrayList<SpecialPair<String, Integer>> test2 = new ArrayList<>();
        test2.add(new SpecialPair<String, Integer>("2 Player Mode", 2));
        test2.add(new SpecialPair<String, Integer>("3 Player Mode", 3));
        test2.add(new SpecialPair<String, Integer>("4 Player Mode", 4));
        ArrayAdapter<SpecialPair<String, Integer>> adapter2 = new ArrayAdapter<>(getContext(), R.layout.list_item, test2);


        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Pair<String, Integer> selected = (Pair<String, Integer>) spinner.getItemAtPosition(position);

                Toast.makeText(getContext(), String.format("You selected %s", selected), Toast.LENGTH_SHORT).show();
                test.clear();
                test.addAll(generateStatsData(BuzzerCounterController.getBuzzerCounter(selected.second), selected.second));
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
