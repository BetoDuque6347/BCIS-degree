package main.scorers;

import java.util.ArrayList;

import main.building.Building;
import main.building.Die;
import main.building.Material;

public class GlassScorer 
{
    private Building building;
    
    public GlassScorer(Building building)
    {
        this.building = building;
    }

    public int score()
    {
        if (building.isValid()) {
            int score = 0;
            ArrayList<Die> glassDice = building.all(Material.GLASS);

            for (Die die : glassDice) {
                score += die.getFace();
            }

            return score;
        }

        return 0;
    }
}
