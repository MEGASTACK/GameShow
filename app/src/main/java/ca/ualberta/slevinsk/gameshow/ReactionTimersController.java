package ca.ualberta.slevinsk.gameshow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 15-10-03.
 */
public class ReactionTimersController {
    private static ReactionTimerList reactionTimerList = null;

    static public ReactionTimerList getReactionTimerList(){
        if(reactionTimerList == null) {
            try {
                reactionTimerList = ReactionTimersManager.getManager().loadReactionTimerList();
                reactionTimerList.addListener(new Listener() {
                    @Override
                    public void update() {

                    }
                });
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        return reactionTimerList;
    }

    static public void saveReactionTimerList() {
        ReactionTimersManager.getManager().saveReactionTimerList(getReactionTimerList());
    }

    static public Long getAverage(Integer n){
        return getReactionTimerList().average(n);
    }

    static public Long getMedian(Integer n){
        return getReactionTimerList().median(n);
    }

    static public Long getMax(Integer n){
        return getReactionTimerList().max(n);
    }

    static public Long getMin(Integer n){
        return getReactionTimerList().min(n);
    }

    static public void add(ReactionTimer r){
        getReactionTimerList().addReactionTimer(r);
    }


    static public List<String> generateStatsData(Integer n){
        List<String> statsList = new ArrayList<>();
        statsList.add(String.format("Max time: %d", getMax(n)));
        statsList.add(String.format("Min time: %d", getMin(n)));
        statsList.add(String.format("Average time: %d", getAverage(n)));
        statsList.add(String.format("Median time: %d", getMedian(n)));
        return statsList;
    }

    public static void clearData() {
        getReactionTimerList().clearData();
        saveReactionTimerList();
    }
}
