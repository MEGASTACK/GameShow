package ca.ualberta.slevinsk.gameshow;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by john on 15-09-26.
 */
public class BuzzerCounterModel extends GenericModel<BuzzerCounter> {
    public BuzzerCounterModel(Context context, String filename) {
        super(context, filename);
    }


    @Override
    public void initializeStorage() {
        super.initializeStorage();
        getModelData().add(new BuzzerCounter(2));
        getModelData().add(new BuzzerCounter(3));
        getModelData().add(new BuzzerCounter(4));
    }

    @Override
    protected void loadFromFile() {
        super.loadFromFile();
    }

    @Override
    public Type getTypeToken() {
        return new TypeToken<ArrayList<BuzzerCounter>> () {}.getType();
    }


    /**
     * Return the counter for the given mode
     * TODO this is kind of gross, perhaps deserves its own implementation
     * @param n total number of players
     */
    public BuzzerCounter getBuzzerCounter(Integer n){
        return getModelData().get(n-2);
    }
}
