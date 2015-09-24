package ca.ualberta.slevinsk.gameshow;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by john on 15-09-24.
 */
public class GenericModel<T> {
    ArrayList<T> timers;

    public static String FILENAME = "rt_data.json";



    public GenericModel(){
        loadFromFile();
    }

    private void loadFromFile() {
        FileReader f = null;
        Gson gson = new Gson();

    Type ta = new TypeToken<ArrayList<T>> () {}.getType();
        try {
            f = new FileReader(FILENAME);
            BufferedReader r = new BufferedReader(f);
            timers = gson.fromJson(r, ta);
            r.close();


        } catch (FileNotFoundException e) {
            timers = new ArrayList<T>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveToFile() {
        Gson gson = new Gson();
        try {
            FileWriter f = new FileWriter(FILENAME);
            BufferedWriter w = new BufferedWriter(f);
            gson.toJson(getTimers(), w);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<T> getTimers(){
        return timers;
    }



    public void add(T timer){
        timers.add(timer);
    }


}
