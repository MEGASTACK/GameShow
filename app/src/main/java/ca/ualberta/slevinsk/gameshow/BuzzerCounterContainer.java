package ca.ualberta.slevinsk.gameshow;

import java.io.Serializable;
import java.util.ArrayList;

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

/**
 * Contains BuzzerCounters for 2 player, 3 player, and 4 player GameShow game.
 */
public class BuzzerCounterContainer implements Serializable{
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
    public void clearListeners() {
        listeners = new ArrayList<>();
    }


    BuzzerCounterContainer(){
        twoPlayersGame = new BuzzerCounter(2);
        threePlayersGame = new BuzzerCounter(3);
        fourPlayersGame = new BuzzerCounter(4);
        listeners = new ArrayList<>();
    }

    private BuzzerCounter getBuzzerCounter(Integer playerCount){
        switch (playerCount) {
            case 2:
                return twoPlayersGame;
            case 3:
                return threePlayersGame;
            case 4:
                return fourPlayersGame;
            default:
                throw new RuntimeException(String.format("Invalid number of players: %d", playerCount));
        }
    }

    /**
     * Increment a player's count in a certain game.
     * @param playerCount players in the game
     * @param player the player whose count should be incremented
     */
    public void incrementCounter(Integer playerCount, Integer player){
        getBuzzerCounter(playerCount).increment(player);
    }

    public Integer getCount(Integer playerCount, Integer player){
        return getBuzzerCounter(playerCount).getCount(player);
    }


    public void clearData() {
        twoPlayersGame = new BuzzerCounter(2);
        threePlayersGame = new BuzzerCounter(3);
        fourPlayersGame = new BuzzerCounter(4);
        notifyListeners();
    }

}
