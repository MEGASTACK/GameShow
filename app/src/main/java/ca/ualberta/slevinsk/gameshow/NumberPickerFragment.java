package ca.ualberta.slevinsk.gameshow;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
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
    public static NumberPickerFragment newInstance(String param1, String param2) {
        NumberPickerFragment fragment = new NumberPickerFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NumberPickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setCancelable(false);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setCancelable(false);
////        NumberPicker numberPicker = (NumberPicker) getView().findViewById(R.id.gsBuzzerNumberPicker);
////        numberPicker.setMaxValue(4);
////        numberPicker.setMinValue(2);
//    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("How many players?");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Snackbar.make(, String.format("%d", numberPicker.getValue()), Snackbar.LENGTH_SHORT);
//            }
//        });
        builder.setCancelable(false);
        numberPicker = new NumberPicker(getContext());
        numberPicker.setMinValue(2);
        numberPicker.setMaxValue(4);
        builder.setView(numberPicker);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

}
