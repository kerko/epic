package nl.fontys.epic.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple implementation of {@see Observer}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleObserver<Type> implements Observer<Type> {
    
    private Set<Type> listeners;
    
    public SimpleObserver() {
        listeners = new HashSet<>();
    }
    
    @Override
    public void addListener(Type t) {
        listeners.add(t);
    }

    @Override
    public void removeListener(Type t) {
        listeners.remove(t);
    }

    @Override
    public boolean hasListener(Type t) {
        return listeners.contains(t);
    }

    @Override
    public Collection<Type> getListeners() {
        return listeners;
    }
    
}
