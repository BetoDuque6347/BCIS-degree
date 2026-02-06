package main.building;

import java.io.File;
import java.util.Scanner;

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
        int colNumber = 0;
        int rowNumber = 0;
        String diceStack = "";
        String dieString;

        while (scanFile.hasNextLine()) {
            String currentLine = scanFile.nextLine();

            // Get the die from each row and add them to building
            if (currentLine.contains("row.")) {
                //The worst line of code I've ever made
                rowNumber = Integer.parseInt(currentLine.substring(currentLine.indexOf(".") + 1, currentLine.length() - 1));
            }
            else if (!currentLine.isEmpty()) {
                //This is equally bad.
                colNumber = Integer.parseInt(currentLine.substring(currentLine.indexOf(".") + 1, currentLine.indexOf(" ")));
                diceStack = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.length() - 1);

                //Add each die to the building
                for (int i = 0; i < diceStack.length(); i += 3) {
                    dieString = diceStack.substring(i, i + 2);
                    Die die = new Die(dieString);

                    building.add(die, rowNumber, colNumber);
                }
            }
        }

        scanFile.close();

        return building;
    }
}
