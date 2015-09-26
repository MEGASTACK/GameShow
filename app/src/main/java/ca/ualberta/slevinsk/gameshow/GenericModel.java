package ca.ualberta.slevinsk.gameshow;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by john on 15-09-24.
 */
public abstract class GenericModel<T> {
    ArrayList<T> modelData;

    private String filename;

    public Context getContext() {
        return context;
    }

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

    public abstract Type getTypeToken();

    protected void loadFromFile() {
        FileInputStream f = null;
        Gson gson = new Gson();

        Type ta = getTypeToken();
        try {
//            f = new FileReader(getFilename());

            f = getContext().openFileInput(getFilename());
            InputStreamReader is = new InputStreamReader(f);

//            BufferedReader r = new BufferedReader(f);
            modelData = gson.fromJson(is, ta);
            is.close();


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


    public void clearData(){
        modelData = new ArrayList<T>();
    }
}
