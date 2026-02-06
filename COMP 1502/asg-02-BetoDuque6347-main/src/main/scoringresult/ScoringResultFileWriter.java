package main.scoringresult;

import java.io.PrintWriter;

public class ScoringResultFileWriter {
    private final ScoringResult result;

    public ScoringResultFileWriter(ScoringResult result) {
        this.result = result;
    }

    public void write(String filePath) throws Exception {
        PrintWriter file = new PrintWriter(filePath);

        int glassScore = result.getGlassScore();
        int recycledScore = result.getRecycledScore();
        int stoneScore = result.getStoneScore();
        int woodScore = result.getWoodScore();
        int bonusScore = result.getBonusScore();

        int totalScore = glassScore + recycledScore + stoneScore + woodScore + bonusScore;

        String formattedResults = "";
        formattedResults += "+-----------+----+\n";
        formattedResults += "| glass     | " + String.format("%2s", glassScore) + " |\n";
        formattedResults += "| recycled  | " + String.format("%2s", recycledScore) + " |\n";
        formattedResults += "| stone     | " + String.format("%2s", stoneScore) + " |\n";
        formattedResults += "| wood      | " + String.format("%2s", woodScore) + " |\n";
        formattedResults += "| **bonus** | " + String.format("%2s", bonusScore) + " |\n";
        formattedResults += "+===========+====+\n";
        formattedResults += "| total     | " + String.format("%2s", totalScore) + " |\n";
        formattedResults += "+-----------+----+";

        file.println(result.getBlueprint() + "\n");

        // Building doesnt have a toString() method so I made one
        file.println(result.getBuilding() + "\n");

        file.println(formattedResults + "\n");
        file.println("Rule violations: " + result.getViolations());

        file.close();
    }
}
