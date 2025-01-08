package com.mycompany.viva3q6;

public class Villain {
    private String villainName;
    private String element;
    private double maxHp;
    private double hp;
    private double attack;
    private double defense;
    private int initialCd;
    private int currentCd;

    public Villain(String name, String element, double maxHp, double attack, double defense, int initialCd) {
        this.villainName = name;
        this.element = element;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.initialCd = initialCd;
        this.currentCd = initialCd;
    }

    public String getElement() {
        return element;
    }

    public double getDefense() {
        return defense;
    }

    public void getDamaged(double damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void resetHp() {
        this.hp = this.maxHp;
    }

    public void decreaseCd() {
        if (this.currentCd > 0) {
            this.currentCd--;
        }
    }

    public void resetCd() {
        this.currentCd = this.initialCd;
    }

    public int getInitialCd() {
        return initialCd;
    }

    public int getCurrentCd() {
        return currentCd;
    }

    public double getHp() {
        return hp;
    }
    
    public double getAttack() {
        return attack;
    }

    public String getName() {
        return villainName;
    }

    @Override
    public String toString() {
        return "Name: " + villainName + "\nElement: " + element + "\nHP: " + hp + "/" + maxHp + "\nAttack: " + attack + "\nDefense: " + defense + "\nCurrent CD: " + currentCd;
    }
}
