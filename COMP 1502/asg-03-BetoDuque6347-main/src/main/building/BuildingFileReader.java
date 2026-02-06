package main.building;

import java.io.File;
import java.util.Scanner;

import main.space.Col;
import main.space.Row;
import main.space.Space;

/**
 * If I didnt clean up this code then I ran out of time
 */
public class BuildingFileReader {
    public BuildingFileReader() {

    }

    public static Building load(String filePath) throws Exception {
        Building building = new Building();
        File file = new File(filePath);
        Scanner scanFile = new Scanner(file);
        Row row = null;
        Col col = null;
        Space space = null;

        int colNum = 0;
        int rowNum = 0;
        String diceStack;
        String currentDie;

        while (scanFile.hasNextLine()) {
            String currentLine = scanFile.nextLine();

            if (currentLine.contains("row")) {
                rowNum = Character.getNumericValue(currentLine.charAt(5));
                row = row.at(rowNum);
            }
            
            //Find the column and add the diceStack onto the building
            if (currentLine.contains("col")) {
                colNum = Character.getNumericValue(currentLine.charAt(4));
                col = col.at(colNum);

                space = space.from(row, col);
                diceStack = currentLine.substring(9, currentLine.length() - 1);

                //Add each die to the building
                for (int i = 0; i < diceStack.length(); i += 3) {
                    currentDie = diceStack.substring(i, i + 2);
                    Die die = new Die(currentDie);

                    building.add(die, space);
                }
            }
        }

        scanFile.close();

        return building;
    }
}
