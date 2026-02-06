package main;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BlueprintsScoringApp {
    private static final String BUILDING_PATH = "res/building.txt";
    private static final String RESULTS_PATH = "res/scoring-results.txt";

    /**
     * Reads a Blueprints "building" from res/building.txt, scores the
     * materials in that building, and writes the result to res/scoring-results.txt.
     * 
     * @param args any command line arguments
     */
    public void run() throws Exception {
        ArrayList<ArrayList<String>> building = getBuildingFromFile(BUILDING_PATH);
        writeResultsToFile(building, RESULTS_PATH);
    }

    /**
     * <p>
     * Returns a formatted version of a building where each row is an ArrayList of
     * Strings and the entire building is an 2-dimensional ArrayList.
     * </p>
     * 
     * <ul>
     * <li>The outer ArrayList contains the 'rows' of the building. The elements are
     * ordered such that:
     * <ul>
     * <li>The 0th element represents the top of the ArrayList</li>
     * <li>The final element represents the botttom of the building</li>
     * </ul>
     * 
     * <li>The inner ArrayList contains the individual dice of each row</li>
     * </ul>
     * 
     * <p>
     * If the building file is empty, then this will return an empty ArrayList.
     * 
     * @param buildingFilePath the file path of the building
     * @return the building as a 2-dimensional ArrayList
     * @throws Exception
     */
    public ArrayList<ArrayList<String>> getBuildingFromFile(String buildingFilePath) throws Exception {
        // Objects
        File file = new File(buildingFilePath);
        Scanner scanFile = new Scanner(file);

        ArrayList<String> lines = new ArrayList<>();
        ArrayList<ArrayList<String>> building = new ArrayList<>();

        // Check if the file still has content to be scanned
        while (scanFile.hasNextLine()) {
            // Add the entire line to the lines ArrayList
            lines.add(scanFile.nextLine());
        }

        scanFile.close();

        // Iterate through each element in line
        for (String row : lines) {
            // Create a new ArrayList to hold the dice in the current row
            ArrayList<String> dice = new ArrayList<>();

            // Iterate through row to get the individual dice within
            for (String die : row.split(" ")) {
                // Add the current die to the dice ArrayList
                dice.add(die);
            }

            // Add the row of dice represented
            // as an ArrayList to the current building
            building.add(dice);
        }

        return building;
    }

    /**
     * Writes the building to score and that building's score details to
     * res/scoring-results.txt.
     * 
     * The details are as follows:
     * <ul>
     * <li>If the incoming building is empty, the output file contains the text "No
     * building present."</li>
     * <li>If the incoming building is not empty, the output file contains:</li>
     * <ol>
     * <li>The text version of the building being scored.</li>
     * <li>A blank line.</li>
     * <li>A formatted scoring table, as specified in the assignment.
     * </ol>
     * </ul>
     * 
     * @param building    the building to score
     * @param resultsPath the path to the results file
     * @throws Exception
     */
    public void writeResultsToFile(ArrayList<ArrayList<String>> building, String resultsPath) throws Exception {
        // Constants
        final int BUILDING_WIDTH = 3;
        final int RIGHTMOST_DIE = 2;

        // Object used to write to file
        PrintWriter file = new PrintWriter(resultsPath);

        // Get scores needed to post to file
        int glassScore = getGlassScore(building);
        int recycledScore = getRecycledScore(building);
        int stoneScore = getStoneScore(building);
        int woodScore = getWoodScore(building);

        // Get the formatted results
        String formattedResults = formattedScoringReport(glassScore, recycledScore, stoneScore, woodScore);

        // Check if the building is NOT empty
        if (!(building.size() == 0)) // I would use .isEmpty() but I don't think we covered it in class.
        {
            // Print out the building

            // Iterate through the rows of the building
            for (ArrayList<String> rows : building) {
                // Grab each die within the current row (I used a standard for loop to check if
                // it was the rightmost die)
                for (int die = 0; die < BUILDING_WIDTH; die++) {
                    if (die != RIGHTMOST_DIE) {
                        // Add an extra space
                        file.print(rows.get(die) + " ");
                    } else // This is the rightmost die
                    {
                        // Don't add an extra space and create a new line.
                        file.println(rows.get(die));
                    }
                }
            }

            // Print out the formatted results
            file.print("\n" + formattedResults);
        } else // The file is blank
        {
            file.print("No building present.");
        }

        file.close();
    }

    /**
     * Returns the score for the glass dice in the provided building.
     * 
     * From page 4 of the rulebook: "Score each clear die individually;
     * the clear die being scored is worth the value of its top face."
     * 
     * @param building the building to score
     * @return the glass materials score
     */
    public int getGlassScore(ArrayList<ArrayList<String>> building) {
        // Variables
        int glassScore = 0;

        // Iterate through the rows of the building
        for (ArrayList<String> row : building) {
            // Iterate through the individual dice of the building (based on current row)
            for (String dice : row) {
                // Find which dice are glass
                if (dice.contains("G")) {
                    // Find the value on the top face of the die
                    int diceValue = Character.getNumericValue(dice.charAt(1));

                    // Add this value to the total glass score
                    glassScore += diceValue;
                }
            }
        }

        return glassScore;
    }

    /**
     * Returns the score for the recycled dice in the provided building.
     * 
     * From page 4 of the rulebook: "Score a total of 2/5/10/15/20/30 points
     * for using a total of 1/2/3/4/5/6 green dice."
     * 
     * @param building the building to score
     * @return the recycled materials score
     */
    public int getRecycledScore(ArrayList<ArrayList<String>> building) {
        // Variables
        int recycledScore;
        int numberOfRecycledDice = 0;

        // Iterate through the rows of the building
        for (ArrayList<String> row : building) {
            // Iterate through the individual dice of the building (based on current row)
            for (String die : row) {
                // Find which die are recycled
                if (die.contains("R")) {
                    // Increase the counter that checks how many recycled dice are present
                    numberOfRecycledDice++;
                }
            }
        }

        // Find the recycled score of the building
        // based on the number of recycled dice present
        switch (numberOfRecycledDice) {
            case 0:
                recycledScore = 0;
                break;
            case 1:
                recycledScore = 2;
                break;
            case 2:
                recycledScore = 5;
                break;
            case 3:
                recycledScore = 10;
                break;
            case 4:
                recycledScore = 15;
                break;
            case 5:
                recycledScore = 20;
                break;
            default: // There is more than 5 recycled dice present
                recycledScore = 30;
        }

        return recycledScore;
    }

    /**
     * Returns the score for the stone dice in the provided building.
     * 
     * From page 4 of the rulebook: "Score each black die individually;
     * the black die being scored is worth 2/3/5/8 points for being on
     * the 1st/2nd/3rd/4th (or higher) level of a building."
     * 
     * @param building the building to score
     * @return the stone materials score
     */
    public int getStoneScore(ArrayList<ArrayList<String>> building) {
        // Variables
        int selectedRowHeight = building.size(); // Scanning through the building begins at the very top level
        int stoneScore = 0;

        // Iterate through the rows of the building
        for (ArrayList<String> row : building) {
            // The enhanced for loop scans the building from top to bottom,
            // so we decrement selectedRowHeight to keep its value accurate.
            selectedRowHeight--;

            // Iterate through the individual dice of the building (based on current row)
            for (String dice : row) {
                // Find which die are stone
                if (dice.contains("S")) {
                    // Score the selected die based on its current height
                    switch (selectedRowHeight) {
                        // Cases start at 0 because of zero-based indexing
                        case 0:
                            stoneScore += 2;
                            break;
                        case 1:
                            stoneScore += 3;
                            break;
                        case 2:
                            stoneScore += 5;
                            break;
                        default: // Current die's elevation is >= 4
                            stoneScore += 8;
                    }
                }
            }
        }

        return stoneScore;
    }

    /**
     * Returns the score for the wood dice in the provided building.
     * 
     * From page 4 of the rulebook: "Score each orange die individually;
     * score 2 points per die adjacent to the orange die being scored.
     * Dice are adjacent if they share a face."
     * 
     * @param building the building to score
     * @return the wood materials score
     */
    public int getWoodScore(ArrayList<ArrayList<String>> building) {
        // Constants
        final int BUILDING_WIDTH = 3; // The "true" width of the building is offset by 1 due to zero-based indexing
        final int OUT_OF_BOUNDS = -1;
        final int SCORE_PER_NEIGHBOUR = 2;

        // Variables
        int buildingHeight = building.size(); // The "true" height of the building is offset by 1 due to zero-based
                                              // indexing
        int neighbourCount = 0;
        int woodScore = 0;

        // Iterate through the rows of the building (I'm using a normal for loop here to
        // get specific coordinates)
        for (int x = 0; x < BUILDING_WIDTH; x++) {
            // Iterate through the individual dice of the building (based on current row)
            // (I'm using a normal for loop here to get specific coordinates)
            for (int y = 0; y < buildingHeight; y++) {
                // Variables within the scope of the embeded for loop
                int aboveDieCoordinate = y - 1;
                int belowDieCoordinate = y + 1;
                int leftDieCoordinate = x - 1;
                int rightdieCoordinate = x + 1;

                String selectedDie = building.get(y).get(x); // The die currently selected by both for loops (btw this
                                                             // is in (y, x) format)

                if (selectedDie.contains("W")) {
                    // First check if the die above goes out of bounds of the array
                    // then check if it's occupied (ORDER MATTERS)
                    if (aboveDieCoordinate != OUT_OF_BOUNDS && !building.get(aboveDieCoordinate).get(x).equals("--")) {
                        neighbourCount++; // Increase neighbour count if the space above is NOT "--"
                    }

                    // Check if the die below goes out of bounds of the array
                    // then check if it's occupied (ORDER MATTERS)
                    if (belowDieCoordinate != buildingHeight && !building.get(belowDieCoordinate).get(x).equals("--")) {
                        neighbourCount++; // Increase neighbour count if the space below is NOT "--"
                    }

                    // Check if the die on the left goes out of bounds of the array
                    // then check if it's occupied (ORDER MATTERS)
                    if (leftDieCoordinate != OUT_OF_BOUNDS && !building.get(y).get(leftDieCoordinate).equals("--")) {
                        neighbourCount++; // Increase neighbour count if the space to the left is NOT "--"
                    }

                    // Check if the die on the right goes out of bounds of the array
                    // then check if it's occupied (ORDER MATTERS)
                    if (rightdieCoordinate != BUILDING_WIDTH && !building.get(y).get(rightdieCoordinate).equals("--")) {
                        neighbourCount++; // Increase neighbour count if the space to the left is NOT "--"
                    }
                }
            }
        }

        woodScore = neighbourCount * SCORE_PER_NEIGHBOUR;

        return woodScore;
    }

    /**
     * Returns a formatted table of material scores, as well as a total score.
     * 
     * Assumption: No individual score, or total score, will have more than two
     * digits.
     * 
     * @param glassScore    the glass score to display
     * @param recycledScore the recycled score to display
     * @param stoneScore    the stone score to display
     * @param woodScore     the wood score to display
     * @return the formatted table of scores
     */
    public String formattedScoringReport(int glassScore, int recycledScore, int stoneScore, int woodScore) {
        // Variables (self explanatory)
        int totalScore = glassScore + recycledScore + stoneScore + woodScore;
        String formattedScore = "";

        // Space inside the cells containing the names of the materials
        // are hard coded because the order will not change.
        formattedScore += "+----------+----+\n";
        formattedScore += "| glass    | " + String.format("%2s", glassScore) + " |\n";
        formattedScore += "| recycled | " + String.format("%2s", recycledScore) + " |\n";
        formattedScore += "| stone    | " + String.format("%2s", stoneScore) + " |\n";
        formattedScore += "| wood     | " + String.format("%2s", woodScore) + " |\n";
        formattedScore += "+==========+====+\n";
        formattedScore += "| total    | " + String.format("%2s", totalScore) + " |\n";
        formattedScore += "+----------+----+";

        return formattedScore;
    }
}