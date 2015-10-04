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
 * Created by john on 15-10-03.
 */
public class ReactionTimersController {
    private static ReactionTimerList reactionTimerList = null;

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
