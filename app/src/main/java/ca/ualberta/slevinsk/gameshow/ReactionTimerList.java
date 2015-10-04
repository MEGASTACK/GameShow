package ca.ualberta.slevinsk.gameshow;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by john on 15-10-03.
 */
public class ReactionTimerList implements Serializable {

    //TODO serialid
    protected ArrayList<ReactionTimer> reactionTimers = null;
    protected transient ArrayList<Listener> listeners = null;

    public ReactionTimerList() {
        reactionTimers = new ArrayList<>();
        listeners = new ArrayList<>();
    }



    public ArrayList<Listener> getListeners() {
        return listeners;
    }
    public List<ReactionTimer> getReactionTimers(){
        return reactionTimers;
    }

    public void addReactionTimer(ReactionTimer timer){
        reactionTimers.add(timer);
        notifyListeners();
    }

    public void addListener(Listener listener){
        getListeners().add(listener);
    }

    private void notifyListeners(){
        for (Listener listener:getListeners()){
            listener.update();
        }
    }

    public void removeReactionTimer(ReactionTimer timer){
        getReactionTimers().add(timer);
        notifyListeners();
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
     * Compute the maximum of the last n reaction times
     * @param n number of instances to consider, or -1 for all
     * @return the average reaction time
     */
    public Long max(Integer n){

        return Collections.max(slice(n)).targetDelta();
    }

    /**
     * Compute the minimum of the last n reaction times
     * @param n number of instances to consider, or -1 for all
     * @return the average reaction time
     */
    public Long min(Integer n){
        return Collections.min(slice(n)).targetDelta();
    }


    /**
     * Compute the median of the last n reaction times
     * @param n number of instances to consider, or -1 for all
     * @return the average reaction time
     */
    public Long median(Integer n){
        List<ReactionTimer> l = slice(n);
        Integer realLength = l.size();
        Collections.sort(l);
        return l.get(realLength / 2).targetDelta();
    }

    /**
     * Creates a new list containing the last n timer entries.
     * @param n How many items to choose, or -1 for all
     * @return a new List containing the slice.
     */
    @NonNull
    private List<ReactionTimer> slice(Integer n) {
        if (n < 0) {
            return new ArrayList<>(getReactionTimers());
        } else {
            List<ReactionTimer> r =  getReactionTimers().subList(Math.max(getReactionTimers().size() - n, 0), getReactionTimers().size());
            return new ArrayList<>(r);
        }
    }


}
