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
