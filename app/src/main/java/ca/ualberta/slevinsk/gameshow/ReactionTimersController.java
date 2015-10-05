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
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for controlling the ReactionTimer game.
 *
 * Records new reaction times and adds them to the ReactionTimerList, if valid.
 *
 * Provides access to aggregate data, and also
 * generates data for statistical display.
 */
public class ReactionTimersController {
    private static ReactionTimerList reactionTimerList = null;

    private static ReactionTimer currentTimer = null;

    static public ReactionTimerList getReactionTimerList(){
        if(reactionTimerList == null) {
            reactionTimerList = ReactionTimersManager.getManager().loadReactionTimerList();
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

    /**
     * Starts a new Reaction Timer.
     */
    static public void startNewTimer(){
        currentTimer = new ReactionTimer();
        currentTimer.randomizeTargetTime();
        currentTimer.start();
    }

    /**
     * Stops the current reaction timer. If the time is valid, the result is saved
     * in the reactionTimerList.
     * @return Long elapsed time (in millis)
     * @throws RuntimeException if timer has not been started.
     */
    static public Long stopTimer(){
        if (currentTimer == null){
            throw new RuntimeException("Error: Timer was not started!");
        } else {
            currentTimer.stop();
            Long elapsedTime = currentTimer.getTimeDelta();
            if (currentTimer.getTimeDelta() >= 0){
                getReactionTimerList().addReactionTimer(currentTimer);
            }
            currentTimer = null;
            return elapsedTime;
        }
    }

    /**
     * Returns the current target reaction time
     * @return Long target time (millis)
     * @throws RuntimeException if timer has not been started.
     */
    static public Long getCurrentTargetTime(){
        if (currentTimer == null){
            throw new RuntimeException("Error: Timer was not started!");
        } else{
            return currentTimer.getTargetTime();
        }
    }

    /**
     * Generates stats data to hook into a ListView's ArrayAdapter.
     * @param n number of ReactionTimer instances to consider.
     * @return List of Strings of each line to be displayed.
     */
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

    /**
     * Generates email data to be sent, based on Reaction Time data.
     * @return A String containing the body of the email.
     */
    public static String generateEmailData(){
        StringBuilder b = new StringBuilder();

        Integer modes[] = {10, 100, -1};

        b.append("Reaction time data:\n");
        for(Integer i:modes){
            if (i>0){
                b.append(String.format("Last %d Reaction Times\n", i));
            } else {
                b.append("All Time Reaction Stats\n");
            }
            for (String line:generateStatsData(-1)) {
                b.append(String.format("%s\n", line));
            }
        }

        b.append("\n");
        return b.toString();
    }
}
