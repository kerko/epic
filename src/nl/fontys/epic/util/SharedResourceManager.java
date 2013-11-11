package nl.fontys.epic.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton implementation of {@see ResourceManager}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SharedResourceManager implements ResourceManager {
    
    private static final Map<String, ResourceManager> managers;
    
    private final Map<String, Object> resources;
    
    static {
        managers = new HashMap<>();
    }
    
    private SharedResourceManager() { 
        resources = new HashMap<>();
    }
    
    public static ResourceManager getInstance(final String ID) {
        
        ResourceManager manager = managers.get(ID);
        
        if (manager == null) {
            manager = new SharedResourceManager();
            managers.put(ID, manager);
        }
        
        return manager;
    }

    @Override
    public <Type> void add(String ID, Type element) {
        resources.put(ID, element);
    }

    @Override
    public <Type> void remove(String ID) {
        resources.remove(ID);
    }

    @Override
    public <Type> Type get(String ID, Class<Type> typeClass) {
        return (Type) resources.get(ID);
    }
    
}
