package main;

public class Main {

    private static final String BUILDING_PATH = "res/building.txt";
    private static final String BLUEPRINT_PATH = "res/blueprint.txt";
    private static final String RESULTS_PATH = "res/scoring-results.txt";

    public static void main(String[] args) throws Exception {
        BlueprintsScoringApp app = new BlueprintsScoringApp(BUILDING_PATH, BLUEPRINT_PATH, RESULTS_PATH);
        app.run();
    }
}