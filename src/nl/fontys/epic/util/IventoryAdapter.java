/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.epic.util;

import nl.fontys.epic.core.impl.SimpleInventory;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class IventoryAdapter extends SimpleInventory implements Indexable {

    private int x, y;
    

    public IventoryAdapter(int x, int y) {
        this.x = x;
        this.y = y;
    }
    

    @Override
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    

    @Override
    public int getY() {
        return this.y;
    }

}
