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
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.NumberPicker;
import android.widget.Toast;


/**
 * Fragment that implements a number picker dialog.
 *
 * Used to choose the number of players in the gameshow buzzer game.
 */
public class NumberPickerFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "gameshowBuzzer";

    private NumberPicker numberPicker;


    public static NumberPickerFragment newInstance(GameshowBuzzerActivity gameshowBuzzer) {
        NumberPickerFragment fragment = new NumberPickerFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public NumberPickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("How many players?");

        builder.setCancelable(false);
        numberPicker = new NumberPicker(getContext());
        numberPicker.setMinValue(2);
        numberPicker.setMaxValue(4);
        numberPicker.setOnValueChangedListener(onPlayerCountChangeListener);

        builder.setView(numberPicker);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }


    NumberPicker.OnValueChangeListener onPlayerCountChangeListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onPlayerCountChangeListener = (NumberPicker.OnValueChangeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnValueChangListener");
        }
    }
}
