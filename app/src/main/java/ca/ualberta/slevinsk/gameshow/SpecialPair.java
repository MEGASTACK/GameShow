package ca.ualberta.slevinsk.gameshow;

import android.support.v4.util.Pair;

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
 * A Pair, with overridden toString that returns the first element's toString.
 * @param <K> Type of first member of Pair
 * @param <V> Type of second member of Pair
 */
public class SpecialPair<K, V> extends Pair<K, V> {
    /**
     * Constructor for a Pair.
     *
     * @param first  the first object in the Pair
     * @param second the second object in the pair
     */
    public SpecialPair(K first, V second) {
        super(first, second);
    }

    @Override
    public String toString() {
        return first.toString();
    }
}
