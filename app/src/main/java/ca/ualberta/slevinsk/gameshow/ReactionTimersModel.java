package ca.ualberta.slevinsk.gameshow;

import android.content.Context;
import android.support.annotation.NonNull;

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
        for (ReactionTimer t: slice(n)){
            sum += t.targetDelta();
        }
        return sum / n;
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
        return getModelData().subList(getModelData().size() - n, getModelData().size());
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
        Collections.sort(l);
        return l.get(n/2).targetDelta();
    }
}
