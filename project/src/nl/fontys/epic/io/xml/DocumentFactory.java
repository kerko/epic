package nl.fontys.epic.io.xml;

import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.Document;

/**
 * Provides simple DOM document creation
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface DocumentFactory {
    
    /**
     * Creates a new DOM document from the given InputStream
     * 
     * @param inputStream input stream of the DOM file
     * @return new document instance
     * @throws IOException Is thrown when something bad happened in the IO
     */
    Document create(InputStream inputStream) throws IOException;
    Document create();
}
