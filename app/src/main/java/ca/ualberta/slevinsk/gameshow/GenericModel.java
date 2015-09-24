package ca.ualberta.slevinsk.gameshow;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by john on 15-09-24.
 */
public class GenericModel<T> {
    ArrayList<T> modelData;

    private String filename;
    private Context context;

    private void setContext(Context ctx){
        this.context = ctx;
    }

    private void setFilename(String filename) {
        this.filename = filename;
    }

    public GenericModel(Context context, String filename){
        setFilename(filename);
        setContext(context);
        loadFromFile();
    }

    private void loadFromFile() {
        FileReader f = null;
        Gson gson = new Gson();

    Type ta = new TypeToken<ArrayList<T>> () {}.getType();
        try {
            f = new FileReader(getFilename());
            BufferedReader r = new BufferedReader(f);
            modelData = gson.fromJson(r, ta);
            r.close();


        } catch (FileNotFoundException e) {
            modelData = new ArrayList<T>();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public ArrayList<T> getModelData(){
        return modelData;
    }



    public void add(T timer){
        modelData.add(timer);
    }


    public String getFilename() {
        return filename;
    }
}
