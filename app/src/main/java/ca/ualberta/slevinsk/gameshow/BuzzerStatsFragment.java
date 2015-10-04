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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BuzzerCounterController.getBuzzerCounterList().clearListeners();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        final List<String> test = BuzzerCounterController.generateStatsData(2);

        final List<String> test = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.list_item, test);
        setListAdapter(adapter);

        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);



        ArrayList<SpecialPair<String, Integer>> test2 = new ArrayList<>();
        test2.add(new SpecialPair<String, Integer>("2 Player Mode", 2));
        test2.add(new SpecialPair<String, Integer>("3 Player Mode", 3));
        test2.add(new SpecialPair<String, Integer>("4 Player Mode", 4));
        ArrayAdapter<SpecialPair<String, Integer>> adapter2 = new ArrayAdapter<>(getContext(), R.layout.list_item, test2);



        Generator<List, Integer> dataGenerator = new Generator<List, Integer>() {
            @Override
            public List generate(Integer arg) {
                return BuzzerCounterController.generateStatsData(arg);
            }
        };

        SpinListener listener = new SpinListener(spinner, test, adapter, dataGenerator);


        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(listener);
        BuzzerCounterController.getBuzzerCounterList().addListener(listener);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_stats, container, false);
        return view;
    }
}
