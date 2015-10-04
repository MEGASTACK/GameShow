package ca.ualberta.slevinsk.gameshow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 15-10-03.
 */
public class BuzzerCounterController {
    private static BuzzerCounterList buzzerCounterList = null;

    static public BuzzerCounterList getBuzzerCounterList(){
        if (buzzerCounterList == null){
            try {
                buzzerCounterList = BuzzerCounterManager.getManager().loadBuzzerCounterList();
                buzzerCounterList.addListener(new Listener() {
                    @Override
                    public void update() {

                    }
                });
            } catch (ClassCastException e){
                e.printStackTrace();
            }
        }
        return buzzerCounterList;
    }

    static public void saveBuzzerCounterList() {
        BuzzerCounterManager.getManager().saveBuzzerCounterList(getBuzzerCounterList());
    }

    public static BuzzerCounter getBuzzerCounter(Integer nplayers){
        return getBuzzerCounterList().getBuzzerCounter(nplayers);
    }

    public static void clearData() {
        getBuzzerCounterList().clearData();

        saveBuzzerCounterList();
    }

    public static  List<String> generateStatsData(Integer n){
        List<String> statsData = new ArrayList<>();

        for (int i=1; i<=n; i++){
            statsData.add(String.format("Player %d Buzz Count: %d", i, getBuzzerCounter(n).getCount(i)));
        }
        return statsData;
    }
}
