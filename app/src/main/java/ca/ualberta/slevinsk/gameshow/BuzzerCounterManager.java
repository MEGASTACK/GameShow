package ca.ualberta.slevinsk.gameshow;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by john on 15-09-26.
 */
public class BuzzerCounterManager {
    ArrayList<BuzzerCounter> modelData;

    private Context context;
    static private BuzzerCounterManager buzzerCounterManager = null;

    public static void initManager(Context context){
        if(buzzerCounterManager == null){
            if(context==null){
                throw new RuntimeException("missing context for BuzzerCounterManager");
            }
            buzzerCounterManager = new BuzzerCounterManager(context);
        }
    }

    public static BuzzerCounterManager getManager(){
        if (buzzerCounterManager == null){
            throw new RuntimeException("Did not initialize manager");
        }
        return buzzerCounterManager;
    }

    private BuzzerCounterManager(Context context){
        this.context = context;
    }



    public void initializeStorage() {
        getModelData().add(new BuzzerCounter(2));
        getModelData().add(new BuzzerCounter(3));
        getModelData().add(new BuzzerCounter(4));
    }

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

    public Context getContext() {
        return context;
    }

    private void setContext(Context ctx){
        this.context = ctx;
    }

    private void setFilename(String filename) {
        this.filename = filename;
    }

    public void saveToFile() {
        Gson gson = new Gson();
        try {
            FileOutputStream f = context.openFileOutput(getFilename(), Context.MODE_PRIVATE);
            OutputStreamWriter w = new OutputStreamWriter(f);

            gson.toJson(getModelData(),w);
            w.flush();
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BuzzerCounter> getModelData(){
        return modelData;
    }

    public void resetModelData() {
        initializeStorage();
    }

    public void add(BuzzerCounter timer){
        modelData.add(timer);
    }

    public String getFilename() {
        return filename;
    }

    public void clearData(){
        modelData = new ArrayList<BuzzerCounter>();
    }
}
