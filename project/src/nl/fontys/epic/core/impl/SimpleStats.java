/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.fontys.epic.core.impl;

import nl.fontys.epic.core.Stats;

/**
 *
 * @author Jan Kerkenhoff <jan.kerkenhoff@gmail.com>
 */
public class SimpleStats implements Stats {

    private int power;
    private int defense;

    public SimpleStats(int power, int defense) {
        this.power = power;
        this.defense = defense;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public void addStats(Stats stats) {
     this.defense += stats.getDefense();
     this.power += stats.getPower();
    }

}
