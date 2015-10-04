package ca.ualberta.slevinsk.gameshow;

/**
 * Created by john on 15-10-03.
 */
public interface Generator<T, U> {
    public T generate(U arg);
}
