package ca.ualberta.slevinsk.gameshow;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
public class BuzzerCounterManager {


    public String filename = "filebz";
    private Context context;

    public String getFilename() {
        return filename;
    }

    public Context getContext() {
        return context;
    }

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

    public Type getTypeToken() {
        return new TypeToken<BuzzerCounterList> () {}.getType();
    }

    public void saveBuzzerCounterList(BuzzerCounterList b){
        saveToFile(b);
    }

    public BuzzerCounterList loadBuzzerCounterList(){
        return loadFromFile();
    }


    public void saveToFile(BuzzerCounterList b) {
        Gson gson = new Gson();
        try {
            FileOutputStream f = context.openFileOutput(getFilename(), Context.MODE_PRIVATE);
            OutputStreamWriter w = new OutputStreamWriter(f);

            gson.toJson(b,w);
            w.flush();
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BuzzerCounterList loadFromFile() {
        FileInputStream f = null;
        Gson gson = new Gson();

        BuzzerCounterList b=null;

        Type ta = getTypeToken();
        try {
            f = getContext().openFileInput(getFilename());
            InputStreamReader is = new InputStreamReader(f);
            b = gson.fromJson(is, ta);
            is.close();


        } catch (FileNotFoundException e) {
            return new BuzzerCounterList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

}
