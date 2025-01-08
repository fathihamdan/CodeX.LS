package com.mycompany.viva3q6;

import java.util.Random;

public class Game {

    public void battle(Team team, Villain enemy) {
        team.resetTeamHp();
        enemy.resetHp();
        enemy.resetCd();

        Random random = new Random();
        String[] elements = {"Water", "Fire", "Earth", "Light", "Dark"};

        while (team.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("Round " + (enemy.getInitialCd() - enemy.getCurrentCd() + 1));
            System.out.println("Enemy's current CD: " + enemy.getCurrentCd());

            // Randomly choose 3 runestones
            String[] runestones = new String[3];
            for (int i = 0; i < 3; i++) {
                runestones[i] = elements[random.nextInt(elements.length)];
            }
            System.out.println("Runestones dissolved: ");
            for (String runestone : runestones) {
                System.out.println(" - " + runestone);
            }

            // Calculate damage dealt by heroes
            for (Hero hero : team.getHeroList()) {
                int rsMultiplier = 0;
                for (String runestone : runestones) {
                    if (hero.getElement().equals(runestone)) {
                        rsMultiplier++;
                    }
                }
                if (rsMultiplier > 0) {
                    double damage = hero.calculateDamage(enemy, rsMultiplier);
                    enemy.getDamaged(damage);
                    System.out.println(hero.getName() + " dealt " + damage + " damage to " + enemy.getName());
                }
            }

            // Check if enemy is defeated
            if (enemy.getHp() <= 0) {
                System.out.println("The team won!");
                return;
            }

            // Enemy attacks if currentCd is 1
            if (enemy.getCurrentCd() == 1) {
                team.getDamaged(enemy.getAttack());
                System.out.println(enemy.getName() + " dealt " + enemy.getAttack() + " damage to the team");
                enemy.resetCd();
            } else {
                enemy.decreaseCd();
            }

            // Display remaining HP and CD
            System.out.println("Team's remaining HP: " + team.getHp());
            System.out.println("Enemy's remaining HP: " + enemy.getHp());
            System.out.println("Enemy's current CD: " + enemy.getCurrentCd());

            // Check if team is defeated
            if (team.getHp() <= 0) {
                System.out.println("The team lost.");
                return;
            }
        }
    }
}
