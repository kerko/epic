/* The MIT License (MIT)
 * 
 * Copyright (c) 2013 Jan Kerkenhoff, Miguel Gonzalez
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package nl.fontys.epic.core.impl;

import nl.fontys.epic.impl.SimpleTextAdventure;
import nl.fontys.epic.core.Equip;
import nl.fontys.epic.core.Inventory;
import nl.fontys.epic.core.Player;
import nl.fontys.epic.core.Stats;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimplePlayer extends SimpleCreature implements Player{
    private Equip equip;

    public SimplePlayer(Equip equip, int life, int maxLife, String name, Inventory inv, Stats base, SimpleTextAdventure adventure, int posix, int posiy, String currentRoomID, String ID) {
        super(life, maxLife, name, inv, base, adventure, posix, posiy, currentRoomID, ID);
        this.equip = equip;
    }
  
   

    @Override
    public Equip getEquip() {
        return this.equip;
    }

   

    @Override
    public Stats getStats() {
       Stats stats =super.getStats();
       stats.addStats(equip.getStats());
       return stats;
    }

    @Override
    public void damage(int damage) {
        super.damage(damage); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxLife() {
       return getMaxLife()+ equip.getLifeBonus();
    }

    
    

    

    
    
}
