/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.fontys.epic.util;

import nl.fontys.epic.core.AdventureEvent;

/**
 *
 * @author root
 */
public class AdventureEventAdapter implements AdventureEvent {
    
    private CommandResponse response;
    
    public AdventureEventAdapter(CommandResponse response) {
        this.response = response;
    }

    @Override
    public String getText() {
        return response.getMessage();
    }
    
}
