package ca.ualberta.slevinsk.gameshow;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by john on 15-10-03.
 */
public class BuzzerCounterList implements Serializable{
    protected BuzzerCounter twoPlayersGame;
    protected BuzzerCounter threePlayersGame;
    protected BuzzerCounter fourPlayersGame;

    private transient ArrayList<Listener> listeners;

    public ArrayList<Listener> getListeners() {
        return listeners;
    }

    public void addListener(Listener listener){
        getListeners().add(listener);
    }

    private void notifyListeners() {
        for (Listener listener : getListeners()) {
            listener.update();
        }
    }


    BuzzerCounterList(){
        twoPlayersGame = new BuzzerCounter(2);
        threePlayersGame = new BuzzerCounter(3);
        fourPlayersGame = new BuzzerCounter(4);
        listeners = new ArrayList<>();
    }

    public BuzzerCounter getBuzzerCounter(Integer i){
        switch (i) {
            case 2:
                return twoPlayersGame;
            case 3:
                return threePlayersGame;
            case 4:
                return fourPlayersGame;
            default:
                throw new RuntimeException(String.format("Invalid number of players: %d", i));
        }
    }

    public void clearData() {
        twoPlayersGame = new BuzzerCounter(2);
        threePlayersGame = new BuzzerCounter(3);
        fourPlayersGame = new BuzzerCounter(4);
        notifyListeners();
    }

    public void clearListeners() {
        listeners = new ArrayList<>();
    }
}
