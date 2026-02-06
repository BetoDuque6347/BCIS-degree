package main;

import main.blueprint.Blueprint;
import main.blueprint.BlueprintFileReader;
import main.building.Building;
import main.building.BuildingFileReader;
import main.scoringresult.ScoringResult;
import main.scoringresult.ScoringResultFileWriter;

public class BlueprintsScoringApp {

    private final String buildingPath;
    private final String blueprintPath;
    private final String resultPath;

    public BlueprintsScoringApp(String buildingPath, String blueprintPath, String resultPath) {
        this.buildingPath = buildingPath;
        this.blueprintPath = blueprintPath;
        this.resultPath = resultPath;
    }


    public void run() throws Exception {
        Blueprint blueprint = BlueprintFileReader.load(blueprintPath);
        Building building = BuildingFileReader.load(buildingPath);
        ScoringResult result = new ScoringResult(blueprint, building);
        ScoringResultFileWriter resultWriter = new ScoringResultFileWriter(result);
        resultWriter.write(resultPath);
    }

}
