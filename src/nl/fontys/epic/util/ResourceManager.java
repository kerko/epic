package nl.fontys.epic.util;

/**
 * Resource manager which stores different resources
 * 
 * @author Miguel Gonzalez
 * @since 1.0
 * @version 1.0
 */
public interface ResourceManager {
    
    /**
     * Adds a new element of the given type
     * 
     * @param <Type>
     * @param ID
     * @param element 
     */
    <Type> void add(final String ID, Type element);
    
    /**
     * Removes the element of the given ID
     * 
     * @param <Type>
     * @param ID 
     */
    <Type> void remove(final String ID);
    
    /**
     * Returns the element of the given type
     * 
     * @param <Type>
     * @param ID
     * @param typeClass
     * @return 
     */
    <Type> Type get(final String ID, Class<Type> typeClass);
    
    
    
}
