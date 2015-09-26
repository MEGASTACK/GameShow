package ca.ualberta.slevinsk.gameshow;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by john on 15-09-24.
 */
public class ReactionTimersModel extends GenericModel<ReactionTimer> {

    public ReactionTimersModel(Context ctx, String filename) {
        super(ctx, filename);
    }

    /**
     * Compute average of last n times
     * @param n number of instances to consider, or -1 for all
     * @return the average reaction time
     */
    public long average(Integer n){
        Long sum = 0L;
        List<ReactionTimer> l = slice(n);
        for (ReactionTimer t: l){
            sum += t.targetDelta();
        }
        return sum / l.size();
    }

    /**
     * Compute average of all reaction times
     * @return the average reaction time
     */
    public long average(){
        return average(-1);
    }

    /**
     * Compute the maximum of the last n reaction times
     * @param n number of instances to consider, or -1 for all
     * @return the average reaction time
     */
    public Long max(Integer n){

        return Collections.max(slice(n)).targetDelta();
    }

    @NonNull
    private List<ReactionTimer> slice(Integer n) {

        if (n < 0) {
            return getModelData();
        } else {
            List<ReactionTimer> r =  getModelData().subList(Math.max(getModelData().size() - n, 0), getModelData().size());
            return r;
        }
    }

    /**
     * Compute the maximum of all reaction times
     * @return
     */
    public Long max(){
        return max(getModelData().size());
    }

    public Long min(Integer n){
        return Collections.min(slice(n)).targetDelta();
    }

    public Long median(Integer n){
        List<ReactionTimer> l = slice(n);
        Integer realLength = l.size();
        Collections.sort(l);
        return l.get(realLength/2).targetDelta();
    }

    protected void loadFromFile() {
        FileInputStream f = null;
        Gson gson = new Gson();

        Type ta = new TypeToken<ArrayList<ReactionTimer>>() {}.getType();
        try {
//            f = new FileReader(getFilename());

            f = getContext().openFileInput(getFilename());
            InputStreamReader is = new InputStreamReader(f);

//            BufferedReader r = new BufferedReader(f);
            modelData = gson.fromJson(is, ta);
            is.close();


        } catch (FileNotFoundException e) {
            modelData = new ArrayList<ReactionTimer>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
