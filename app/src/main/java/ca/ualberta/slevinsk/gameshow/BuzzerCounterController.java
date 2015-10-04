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

import android.app.AlertDialog;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 15-10-03.
 */
public class BuzzerCounterController {
    private static BuzzerCounterList buzzerCounterList = null;

    static public BuzzerCounterList getBuzzerCounterList(){
        if (buzzerCounterList == null){
            buzzerCounterList = BuzzerCounterManager.getManager().loadBuzzerCounterList();
        }
        return buzzerCounterList;
    }

    static public void saveBuzzerCounterList() {
        BuzzerCounterManager.getManager().saveBuzzerCounterList(getBuzzerCounterList());
    }

    public static void incrementCounter(Integer gamePlayers, Integer player){
        getBuzzerCounterList().incrementCounter(gamePlayers, player);
    }

    public static Integer getCount(Integer nplayers, Integer player){
        return getBuzzerCounterList().getCount(nplayers, player);
    }

    public static void clearData() {
        getBuzzerCounterList().clearData();

        saveBuzzerCounterList();
    }

    public static  List<String> generateStatsData(Integer n){
        List<String> statsData = new ArrayList<>();

        for (int i=1; i<=n; i++){
            statsData.add(String.format("Player %d Buzz Count: %d", i, getCount(n, i)));
        }
        return statsData;
    }

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
