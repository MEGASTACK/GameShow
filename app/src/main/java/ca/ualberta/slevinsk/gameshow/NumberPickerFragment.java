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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NumberPickerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumberPickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumberPickerFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "gameshowBuzzer";

    private GameshowBuzzerActivity gameshowBuzzer;
    private NumberPicker numberPicker;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumberPickerFragment.
     */
    // TODO: Rename and change types and number of parameters
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
