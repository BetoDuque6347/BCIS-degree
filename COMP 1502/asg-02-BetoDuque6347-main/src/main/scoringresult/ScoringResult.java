package main.scoringresult;

import main.blueprint.Blueprint;
import main.building.Building;
import main.scorers.GlassScorer;
import main.scorers.RecycledScorer;
import main.violations.ViolationList;

public class ScoringResult {
    private final Blueprint blueprint;
    private final Building building;

    public ScoringResult(Blueprint blueprint, Building building) {
        this.blueprint = blueprint;
        this.building = building;
    }

    public ViolationList getViolations() {
        return building.getViolations();
    }

    public Building getBuilding() {
        return building;
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public int getBonusScore() {
        return 0;
    }

    public int getGlassScore() {
        GlassScorer glassScore = new GlassScorer(building);
        int score = glassScore.score();
        return score;
    }

    public int getRecycledScore() {
        RecycledScorer recycledScore = new RecycledScorer(building);
        int score = recycledScore.score();
        return score;
    }

    public int getStoneScore() {
        return 0;
    }

    public int getWoodScore() {
        return 0;
    }
}
