/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.fontys.epic.io.xml;

import nl.fontys.epic.io.ConvertException;
import nl.fontys.epic.io.IOConverter;
import org.w3c.dom.Node;

/**
 *
 * @author Miguel Gonzalez
 */
public class XMLConverter implements IOConverter<Node> {

    @Override
    public <T> Node toOutput(T source) throws ConvertException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T toInput(Node source, Class<T> classType) throws ConvertException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
