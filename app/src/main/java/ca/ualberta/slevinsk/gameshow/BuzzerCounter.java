package ca.ualberta.slevinsk.gameshow; /**
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

import java.util.HashMap;
import java.util.Map;


/**
 * Simulates a set of gameshow buzzers for a certain number of players
 */
public class BuzzerCounter {
    public Map<Integer, Integer> getCounts() {
        return counts;
    }

    private Map<Integer, Integer> counts;
    private static final Integer MIN_PLAYERS = 2;
    private static final Integer MAX_PLAYERS = 4;

    /**
     * Create a BuzzerCounter with "players" buzzers
     * @param players the number of buzzers
     * @throws RuntimeException if players is not in [MIN_PLAYERS, MAX_PLAYERS]
     */
    public BuzzerCounter(Integer players) {

        counts = new HashMap<>();
        if (players < MIN_PLAYERS || players > MAX_PLAYERS) {
            throw new RuntimeException(String.format("Invalid player count: %d", players));
        }

        for (Integer i=1; i<=players; i++){
            getCounts().put(i, 0);
        }
    }

    /**
     * Increment a player's score
     * @param n the player to increment
     */
    public void increment(Integer n) {
        Integer newCount = getCount(n) + 1;
        setCount(n, newCount);
    }

    public Integer getCount(Integer n){
        return getCounts().get(n);
    }

    public void setCount(Integer n, Integer count){
        getCounts().put(n, count);
    }


}
