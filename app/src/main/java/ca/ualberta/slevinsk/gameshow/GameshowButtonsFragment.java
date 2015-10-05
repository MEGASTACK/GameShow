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
 * Fragment that manages gameshow buzzer buttons.
 *
 * Loads a layout dependent on the number of players in the game.
 */
public class GameshowButtonsFragment extends Fragment {

    private static final String ARG_PARAM1 = "numberOfPlayers";
    private Integer numberOfPlayers;


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
            getView().findViewById(ids[i]).setOnClickListener(listener);
        }
    }



     public class BuzzerButtonListener implements View.OnClickListener, Listener{

        private int playerId;
        private int numberOfPlayers;

        BuzzerButtonListener(int numberOfPlayers, int playerId){
            this.playerId = playerId;
            this.numberOfPlayers = numberOfPlayers;
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

}
