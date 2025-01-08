import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private Hero[] deck;
    private List<Hero> heroList;
    private double hp;

    public Team(Hero[] deck) {
        this.deck = deck;
        this.heroList = new ArrayList<>();
        this.hp = 0;
    }

    public List<Hero> getHeroList() {
        return heroList;
    }

    public double getHp() {
        return hp;
    }

    public void formTeam() {
        heroList.clear();
        List<Hero> deckList = new ArrayList<>();
        Collections.addAll(deckList, deck);
        Collections.shuffle(deckList);
        for (int i = 0; i < 4; i++) {
            heroList.add(deckList.get(i));
        }
        calculateTeamHp();
    }

    private void calculateTeamHp() {
        hp = 0;
        for (Hero hero : heroList) {
            hp += hero.getHp();
        }
    }

    public void getDamaged(double damage) {
        hp -= damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void resetTeamHp() {
        calculateTeamHp();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team's HP: ").append(hp).append("\n\n");
        for (int i = 0; i < heroList.size(); i++) {
            sb.append("Hero ").append(i + 1).append("\n");
            sb.append(heroList.get(i)).append("\n\n");
        }
        return sb.toString();
    }
}