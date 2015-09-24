package ca.ualberta.slevinsk.gameshow;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by john on 15-09-24.
 */
public class GenericModel<T> {
    ArrayList<T> modelData;

    public String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public GenericModel(String filename){
        setFilename(filename);
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
            FileWriter f = new FileWriter(getFilename());
            BufferedWriter w = new BufferedWriter(f);
            gson.toJson(getModelData(), w);

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
