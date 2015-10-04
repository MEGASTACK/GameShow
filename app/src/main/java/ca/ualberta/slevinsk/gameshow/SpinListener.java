package ca.ualberta.slevinsk.gameshow;

import android.support.v4.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by john on 15-10-03.
 */
class SpinListener implements AdapterView.OnItemSelectedListener, Listener  {

    private Spinner spinner;
    private List list;
    private ArrayAdapter adapter;
    private Generator<List, Integer> generateListData;

    public Integer getLastPosition() {
        return lastPosition;
    }

    private Integer lastPosition;

    SpinListener(Spinner spinner, final List list, final ArrayAdapter adapter, Generator<List, Integer> generateListData){
        this.adapter = adapter;
        this.list = list;
        this.spinner = spinner;
        this.generateListData = generateListData;
        lastPosition = 0;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        update(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        update();
    }

    public void update(){
        update(getLastPosition());
    }

    public void update(int position){
        Pair<String, Integer> selected = (Pair<String, Integer>) spinner.getItemAtPosition(position);
        list.clear();
        list.addAll(generateListData.generate(selected.second));
        adapter.notifyDataSetChanged();
        lastPosition = position;
    }
}