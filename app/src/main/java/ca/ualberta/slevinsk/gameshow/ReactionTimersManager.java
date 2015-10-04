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
import android.content.Context;
import android.support.annotation.NonNull;

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
import java.util.Collections;
import java.util.List;

/**
 * Created by john on 15-09-24.
 */
public class ReactionTimersManager {

    public String getFilename() {
        return filename;
    }

    private String filename = "filert";
    private Context context;

    public Context getContext() {
        return context;
    }

    private void setContext(Context ctx) {
        this.context = ctx;
    }

    static private ReactionTimersManager reactionTimersManager = null;

    static public void initManager(Context context){
        if (reactionTimersManager == null){
            if (context == null){
                throw new RuntimeException("missing context for reactionTimersManager");
            }
            reactionTimersManager = new ReactionTimersManager(context);
        }
    }


    public static ReactionTimersManager getManager(){
        if (reactionTimersManager == null){
            throw new RuntimeException("did not init manager");
        }
        return reactionTimersManager;
    }


    public ReactionTimersManager(Context context){
        setContext(context);
    }



    public static Type getTypeToken() {
        return new TypeToken<ReactionTimerList> () {}.getType();
    }


    private void setFilename(String filename) {
        this.filename = filename;
    }


    public ReactionTimerList loadReactionTimerList(){
        return loadFromFile();
    }

    public void saveReactionTimerList(ReactionTimerList r){
        saveToFile(r);
    }


    public ReactionTimerList loadFromFile() {
        FileInputStream f = null;
        Gson gson = new Gson();

        ReactionTimerList l=null;

        Type ta = getTypeToken();
        try {
            f = getContext().openFileInput(getFilename());
            InputStreamReader is = new InputStreamReader(f);
            l = gson.fromJson(is, ta);
            is.close();


        } catch (FileNotFoundException e) {
            return new ReactionTimerList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return l;
    }

    public void saveToFile(ReactionTimerList r) {
        Gson gson = new Gson();
        try {
            FileOutputStream f = context.openFileOutput(getFilename(), Context.MODE_PRIVATE);
            OutputStreamWriter w = new OutputStreamWriter(f);

            gson.toJson(r,w);
            w.flush();
            f.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
