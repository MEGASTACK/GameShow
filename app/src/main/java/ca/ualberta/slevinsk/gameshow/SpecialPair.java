package ca.ualberta.slevinsk.gameshow;

import android.support.v4.util.Pair;

/**
 * Created by john on 15-09-26.
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
