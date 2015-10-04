package ca.ualberta.slevinsk.gameshow;

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
}
