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

package ca.ualberta.slevinsk.gameshow;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for controlling the Gameshow Buzzer game.
 *
 * Records new buzzes and adds them to the BuzzerCounterContainer, if valid.
 *
 * Generates data for statistical display.
 */
public class BuzzerCounterController {
    private static BuzzerCounterContainer buzzerCounterContainer = null;

    static public BuzzerCounterContainer getBuzzerCounterContainer(){
        if (buzzerCounterContainer == null){
            buzzerCounterContainer = BuzzerCounterManager.getManager().loadBuzzerCounterList();
        }
        return buzzerCounterContainer;
    }

    static public void saveBuzzerCounterList() {
        BuzzerCounterManager.getManager().saveBuzzerCounterList(getBuzzerCounterContainer());
    }

    public static void incrementCounter(Integer gamePlayers, Integer player){
        getBuzzerCounterContainer().incrementCounter(gamePlayers, player);
    }

    public static Integer getCount(Integer nplayers, Integer player){
        return getBuzzerCounterContainer().getCount(nplayers, player);
    }

    public static void clearData() {
        getBuzzerCounterContainer().clearData();

        saveBuzzerCounterList();
    }

    /**
     * Generates stats data to hook into a ListView's ArrayAdapter.
     * @param n the game to consider, ie an n player game.
     * @return List of Strings of each line to be displayed.
     */
    public static  List<String> generateStatsData(Integer n){
        List<String> statsData = new ArrayList<>();

        for (int i=1; i<=n; i++){
            statsData.add(String.format("Player %d Buzz Count: %d", i, getCount(n, i)));
        }
        return statsData;
    }

    /**
     * Generates email data to be sent, based on Buzzer Count data.
     * @return A String containing the body of the email.
     */
    public static String generateEmailData(){
        StringBuilder b = new StringBuilder();

        b.append("Gameshow Buzzer Data\n");
        for (int i=2; i<=4; i++){
            b.append(String.format("%d Player Game Stats:\n",i));
            for (String line:generateStatsData(i)){
                b.append(String.format("%s\n",line));
            }
        }
        b.append("\n");
        return b.toString();
    }


}
