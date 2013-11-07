package nl.fontys.epic.util;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 * @param <Type>
 */
public interface Observer<Type> {

    public void addListener(Type t);

    public void removeListener(Type t);

    public boolean hasListener(Type t);

    public Type[] getListeners();
}
