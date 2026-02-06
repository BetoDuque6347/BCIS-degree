package main.scorers;

import java.util.ArrayList;

import main.building.Building;
import main.building.Die;
import main.building.Material;

public class RecycledScorer {
    private Building building;

    public RecycledScorer(Building building) {
        this.building = building;
    }

    public int score() {
        if (building.isValid()) {
            int score = 0;
            ArrayList<Die> recycledDice = building.all(Material.RECYCLED);
            int numberOfDice = recycledDice.size();

            switch (numberOfDice) {
                case 1:
                    score = 2;
                    break;
                case 2:
                    score = 5;
                    break;
                case 3:
                    score = 10;
                    break;
                case 4:
                    score = 15;
                    break;
                case 5:
                    score = 20;
                    break;
                case 6:
                    score = 30;
                    break;
            }

            return score;
        }

        return 0;
    }
}
