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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragment to display reaction time stats
 */
public class ReactionStatsFragment extends ListFragment {

    public static ReactionStatsFragment newInstance( ) {
        ReactionStatsFragment fragment = new ReactionStatsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ReactionStatsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
