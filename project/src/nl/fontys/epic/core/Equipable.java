/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.fontys.epic.core;

/**
 * Represents an equip
 *
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface Equipable {
    
    /**
     * 
     * @return 
     */
    Stats getStats();
    
    EquipType getEquipType();
}
